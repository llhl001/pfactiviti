/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;

/**
 * @ClassName: AcRoleServiceImpl
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class AcRoleServiceImpl implements IAcRoleService {

	private static final String MAPPER_SPACE_NAME = "acRoleSpace";
	
	@Autowired
	private IBaseDao dao;
	/*@Autowired
	private IAcApplicationService acApplicationService;
	public void testTransaction() {
		this.delete("2");
		acApplicationService.delete("2");
		int i = 1/0;
		return;
	}*/
	
	@Override
	public Map get(String id) {
		// TODO Auto-generated method stub
		//return (Map) dao.get("acRoleSpace.get", operator_id);
		return (Map) dao.get("acRoleSpace.get", id);  
	}
	/**
     * 查询	一页记录
     * @param rowSelection
     * @param map
     * @return
     */
	@Override
    public PageView queryPage(PageView pageView,Map paramMap){
		 List list = dao.queryPage("acRoleSpace.queryPage", pageView, paramMap);
		 pageView.setRecords(list);
		 return pageView;
    }


	/**
	 * 插入		一条记录
	 * @param beanMap
	 * @return
	 */    
    @Override
    public int insert(Map record){
    	return dao.insert_("acRoleSpace.insert", record);
    }

    /**
     * 修改		一条记录
     * @param beanMap
     * @return
     */
    @Override
    public int update(Map record){
    	return dao.update_("acRoleSpace.update", record);
    }
    
    /**
	 * 删除		一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("acRoleSpace.delete", id);
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
		List list = (List) dao.query("acRoleSpace.query", paramMap);
		return list;
	}
	/**
	 * @Title: batchDelete
	 * @author：liuyx 
	 * @date：2015年9月25日上午10:13:58
	 * @Description: 批量删除
	 * @return
	 */
	@Override
	public int batchDelete(String ids) {
		Map paramMap = new HashMap();
		paramMap.put("ROLE_IDS", ids);
		dao.delete("omPartyRoleSpace.batchDelete", paramMap);
		dao.delete("acRoleFuncSpace.batchDelete", paramMap);
		dao.delete("acOperatorRoleSpace.batchDelete", paramMap);
		int ret = dao.delete("acRoleSpace.batchDelete", paramMap);
		return ret;
	}
}
