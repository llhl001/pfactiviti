/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.sys.sys_dict.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;

/** 
 * @ClassName: SysDictServiceImpl.java
 * @Description: 
 * @author yuqing 
 * @version 2015年10月19日 下午2:48:14 
 */
@Service
public class SysDictServiceImpl implements ISysDictService{

	@Autowired
	private IBaseDao dao;
	@SuppressWarnings("rawtypes")
	@Override
	public Map get(String id) {
		return (Map) dao.get("sysDictSpace.get", id);  
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Map rightGet(String id) {
		return (Map) dao.get("sysDictSpace.rightGet", id);  
	}
	/**
     * 查询	一页记录
     * @param rowSelection
     * @param map
     * @return
     */
	@SuppressWarnings("rawtypes")
	@Override
    public PageView queryPage(PageView pageView,Map paramMap){
		 List list = dao.queryPage("sysDictSpace.queryPage", pageView, paramMap);
		 pageView.setRecords(list);
		 return pageView;
    }
	/**
     * 右侧查询	一页记录
     * @param rowSelection
     * @param map
     * @return
     */
	@SuppressWarnings("rawtypes")
	@Override
    public PageView rightQueryPage(PageView pageView,Map paramMap){
		 List list = dao.queryPage("sysDictSpace.rightQueryPage", pageView, paramMap);
		 pageView.setRecords(list);
		 return pageView;
    }
	
	/**
	 * 插入       一条记录
	 * @param beanMap
	 * @return
	 */    
    @SuppressWarnings("rawtypes")
	@Override
    public int insert(Map record){
    	return dao.insert_("sysDictSpace.insert", record);
    }
    /**
	 * 右侧插入       一条记录
	 * @param beanMap
	 * @return
	 */    
    @SuppressWarnings("rawtypes")
	@Override
    public int rightInsert(Map record){
    	return dao.insert_("sysDictSpace.rightInsert", record);
    }
    /**
     * 修改	  一条记录
     * @param beanMap
     * @return
     */
    @SuppressWarnings("rawtypes")
	@Override
    public int update(Map record){
    	return dao.update_("sysDictSpace.update", record);
    }
    /**
     * 修改	  一条记录
     * @param beanMap
     * @return
     */
    @SuppressWarnings("rawtypes")
	@Override
    public int rightUpdate(Map record){
    	return dao.update_("sysDictSpace.rightUpdate", record);
    }
    /**
	 * 删除	一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("sysDictSpace.delete", id);
    }
	/**
	 * @Title: 查询所有的字典记录（左侧列表）
	 * @author：yuqing
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> query(Map paramMap) {
		List list = (List) dao.query("sysDictSpace.query", paramMap);
		return list;
	}
	/**
	 * 
	 * @Title: queryDictItemsByTypeCode
	 * @author：liuyx 
	 * @date：2015年11月16日下午4:59:47
	 * @Description: TODO
	 * @param typeCode
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Cacheable(value = "dictCache",key="#typeCode")
	public List<Map> queryDictItemsByTypeCode(String typeCode) {
		Map paramMap = new HashMap();
		paramMap.put("TYPE_CODE", typeCode);
		List list = (List) dao.query("sysDictSpace.queryDictItem", paramMap);
		return list;
	}
	/**
	 * @Title: 查询所有的字典记录（右侧列表）
	 * @author：yuqing
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> rightQuery(Map paramMap) {
		List list = (List) dao.query("sysDictSpace.rightQuery", paramMap);
		return list;
	}
	/**
	 * @Title: 字典项列表
	 * @author：yuqing 
	 * @date：2015年10月20日上午10:13:58
	 * @Description: 批量删除
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int rightBatchDelete(String ids) {
		Map paramMap = new HashMap();
		paramMap.put("DIC_IDS", ids);
		int ret = dao.delete("sysDictSpace.rightBatchDelete", paramMap);
		return ret;
	}
	/**
	 * @Title: 字典类型列表
	 * @author：yuqing 
	 * @date：2015年10月20日上午10:13:58
	 * @Description: 批量删除
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	public int batchDelete(String ids) {
		Map paramMap = new HashMap();
		paramMap.put("DIC_IDS", ids);
		int item = dao.delete("sysDictSpace.deleteItems", paramMap);
		int ret = dao.delete("sysDictSpace.batchDelete", paramMap);
		return ret;
	}
}
