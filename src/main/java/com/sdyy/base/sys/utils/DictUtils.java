package com.sdyy.base.sys.utils;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
/*import org.apache.commons.lang3.StringUtils;
*/
import com.google.common.collect.Lists;
import com.sdyy.base.sys.sys_dict.service.ISysDictService;
import com.sdyy.common.spring.ComponentFactory;
import com.sdyy.common.utils.CacheUtils;

/**
 * 字典工具类
 */
@Service
public class DictUtils {
	private final Logger logger = Logger.getLogger(this.getClass());
	public static final String CACHE_DICT_MAP = "dictMap";
	/**
	 * 是每次将所有字典项    还是   只将对应typeCode类型的字典项 加入缓存
	 */
	private static final String INIT_ALL_DICT_CACHE_SWITCH = "on";
	public static List<Map> getDictList(String typeCode){
		if(INIT_ALL_DICT_CACHE_SWITCH.equals("off")) {
			/*Cache cache = CacheUtils.getCacheManager().getCache(CacheUtils.DICT_CACHE);
			// 获取所有的缓存对象
			for (Object key : cache.getKeys()) {
			    System.out.println(key);
			}*/
			ISysDictService sysDictService = ComponentFactory.getBean(ISysDictService.class);
			return sysDictService.queryDictItemsByTypeCode(typeCode);
		}
		@SuppressWarnings("unchecked")
		Map<String, List<Map>> dictMap = (Map<String, List<Map>>)CacheUtils.get(CacheUtils.DICT_CACHE,CACHE_DICT_MAP);
		//如果缓存中没有东西，则初始化所有字典项到缓存中
		if (dictMap==null){
			dictMap = new HashMap<String, List<Map>>();
			ISysDictService sysDictService = ComponentFactory.getBean(ISysDictService.class);
			List<Map> dictTempList = sysDictService.rightQuery(null);
			for (Map dict : dictTempList){
				List<Map> dictList = dictMap.get(dict.get("TYPE_CODE"));
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.get("TYPE_CODE").toString(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Map> dictList = dictMap.get(typeCode);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	@Scheduled(cron="0 0/10 * * * ? ")
	public void refreshDictCache(){
		logger.info("定时器执行：com.sdyy.base.sys.utils.DictUtils——refreshDictCache");
		@SuppressWarnings("unchecked")
		Map<String, List<Map>> dictMap = (Map<String, List<Map>>)CacheUtils.get(CacheUtils.DICT_CACHE,CACHE_DICT_MAP);
		//如果缓存中没有东西，则初始化所有字典项到缓存中
		if (dictMap==null){
			dictMap = new HashMap<String, List<Map>>();
			ISysDictService sysDictService = ComponentFactory.getBean(ISysDictService.class);
			List<Map> dictTempList = sysDictService.rightQuery(null);
			for (Map dict : dictTempList){
				List<Map> dictList = dictMap.get(dict.get("TYPE_CODE"));
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.get("TYPE_CODE").toString(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JSON.toJSONString(getDictList(type));
	}
	
}
