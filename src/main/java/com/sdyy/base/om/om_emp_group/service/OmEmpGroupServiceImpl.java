/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_emp_group.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;

/**
 * @ClassName: OmEmpGroupServiceImpl
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class OmEmpGroupServiceImpl implements IOmEmpGroupService {

	@Autowired
	private IBaseDao dao;
	
	@Override
	public Map get(String id) {
		// TODO Auto-generated method stub
		//return (Map) dao.get("omEmpGroupSpace.get", operator_id);
		/*return (Map) dao.get("omEmpGroupSpace.get", id);*/
		return null;
	}
	/**
     * 查询	一页记录
     * @param rowSelection
     * @param map
     * @return
     */
	@Override
    public PageView queryPage(PageView pageView,Map paramMap){
    	return null;//分页待完善
    }


	/**
	 * 插入		一条记录
	 * @param beanMap
	 * @return
	 */    
    @Override
    public int insert(Map record){
    	return dao.insert_("omEmpGroupSpace.insert", record);
    }

    /**
     * 修改		一条记录
     * @param beanMap
     * @return
     */
    @Override
    public int update(Map record){
    	/*return dao.update_("omEmpGroupSpace.update", record);*/
    	return 0;
    }
    
    /**
	 * 删除		一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(Map paramMap){
    	return dao.delete("omEmpGroupSpace.delete", paramMap);
    }
	/**
	 * @Title: query
	 * @author：liuyx 
	 * @date：2015年9月5日下午3:51:13
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Map> query(Map paramMap) {
		List list = (List) dao.query("omEmpGroupSpace.query", paramMap);
		return list;
	}
	/**
	 * @Title: batchInsert
	 * @author：liuyx 
	 * @date：2015年9月27日下午2:06:18
	 * @Description: 批量插入
	 * @param list
	 * @return
	 */
	@Override
	public int batchInsert(List<Map> list,Map deleteParam) {
		if(deleteParam!=null) {
			this.dao.delete("omEmpGroupSpace.delete", deleteParam);
		}
		if(!CollectionUtils.isEmpty(list)) {
			return dao.batchInsert("omEmpGroupSpace.insert", list);
		}
		return 0;
		
	}
	/**
	 * @Title: delete
	 * @author：liuyx 
	 * @date：2015年10月3日下午6:04:47
	 * @Description: TODO
	 * @param id
	 * @return
	 */
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
