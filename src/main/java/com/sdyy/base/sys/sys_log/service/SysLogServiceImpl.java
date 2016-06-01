/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.sys.sys_log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;

/** 
 * @ClassName: SysLogServiceImpl.java
 * @Description: 
 * @author yuqing 
 * @version 2015年10月19日 下午2:48:14 
 */
@Service
public class SysLogServiceImpl implements ISysLogService{

	@Autowired
	private IBaseDao dao;
	@SuppressWarnings("rawtypes")
	@Override
	public Map get(String id) {
		return (Map) dao.get("sysLogSpace.get", id);  
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
		 List list = dao.queryPage("sysLogSpace.queryPage", pageView, paramMap);
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
    	return dao.insert_("sysLogSpace.insert", record);
    }

   
    /**
     * 修改	  一条记录
     * @param beanMap
     * @return
     */
    @SuppressWarnings("rawtypes")
	@Override
    public int update(Map record){
    	return dao.update_("sysLogSpace.update", record);
    }
    
    /**
	 * 删除	一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("sysLogSpace.delete", id);
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
		List list = (List) dao.query("sysLogSpace.query", paramMap);
		return list;
	}
	
}
