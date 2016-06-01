/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.sys.sys_file.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;

/** 
 * @ClassName: SysFileServiceImpl.java
 * @Description: 
 * @author yuqing 
 * @version 2015年10月19日 下午2:48:14 
 */
@Service
public class SysFileServiceImpl implements ISysFileService{

	@Autowired
	private IBaseDao dao;
	@SuppressWarnings("rawtypes")
	@Override
	public Map get(String id) {
		return (Map) dao.get("sysFileSpace.get", id);  
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
		 List list = dao.queryPage("sysFileSpace.queryPage", pageView, paramMap);
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
    	return dao.insert_("sysFileSpace.insert", record);
    }
    /**
     * 修改	  一条记录
     * @param beanMap
     * @return
     */
    @SuppressWarnings("rawtypes")
	@Override
    public int update(Map record){
    	return dao.update_("sysFileSpace.update", record);
    }
    /**
	 * 删除	一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("sysFileSpace.delete", id);
    }
	/**
	 * @Title: 查询所有的记录（左侧列表）
	 * @author：liuyx
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> query(Map paramMap) {
		List list = (List) dao.query("sysFileSpace.query", paramMap);
		return list;
	}
	
	public int batchInsert(List list) {
		if(!CollectionUtils.isEmpty(list)) {
			//return dao.batchInsert("sysFileSpace.insert", list);
			return dao.insert_("sysFileSpace.batchInsert", list);
		}
		return 0;
		
	}
}
