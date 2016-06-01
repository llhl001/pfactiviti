/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_home_module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcHomeModuleServiceImpl
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class AcHomeModuleServiceImpl implements IAcHomeModuleService {

	private static final String MAPPER_SPACE_NAME = "acHomeModuleSpace";
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
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
		//return (Map) dao.get("acHomeModuleSpace.get", operator_id);
		return (Map) dao.get("acHomeModuleSpace.get", id);  
	}
	/**
     * 查询	一页记录
     * @param rowSelection
     * @param map
     * @return
     */
	@Override
    public PageView queryPage(PageView pageView,Map paramMap){
		List list = dao.queryPage("acHomeModuleSpace.queryPage", pageView, paramMap);
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
    	return dao.insert_("acHomeModuleSpace.insert", record);
    }

    /**
     * 修改		一条记录
     * @param beanMap
     * @return
     */
    @Override
    public int update(Map record){
    	return dao.update_("acHomeModuleSpace.update", record);
    }
    
    /**
	 * 删除		一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("acHomeModuleSpace.delete", id);
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
		paramMap.put("HOME_MODULE_IDS", ids);
		int ret = dao.delete("acHomeModuleSpace.batchDelete", paramMap);
		return ret;
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
		List list = (List) dao.query("acHomeModuleSpace.query", paramMap);
		return list;
	}
	
	/**
	 * @Title: queryChildrenTreeNodes
	 * @author：liuyx 
	 * @date：2015年9月7日下午4:56:36
	 * @Description: 构建 机构 岗位 人员树
	 * @param paramMap
	 * @return
	 */
	public List<ZTreeNode> queryNodesForRole(Map<String,Object> paramMap){
		// TODO Auto-generated method stub
		List<ZTreeNode> list = null;
		list = (List) dao.query("acHomeModuleSpace.queryNodesForRole", paramMap);
		return list;
	}
	
	/**
	 * @Title: batchInsert
	 * @author：liuyx 
	 * @date：2015年12月3日13:58:30
	 * @Description: 批量插入
	 * @param list
	 * @return
	 */
	@Override
	public int batchInsertRoleHomeModule(List<Map> list,Map deleteParam) {
		this.dao.delete("acHomeModuleSpace.deleteRoleHomeModule", deleteParam);
		if(CollectionUtils.isEmpty(list)) {
			return 0;
		}
		return dao.insert_("acHomeModuleSpace.batchInsertRoleHomeModule", list);
	}
	
	/**
	 * @Title: queryOperHomeModule
	 * @author：liuyx 
	 * @date：2015年12月3日15:09:20
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Map> queryOperHomeModule(Map paramMap) {
		List list = (List) dao.query("acHomeModuleSpace.queryOperHomeModule", paramMap);
		return list;
	}
	
	/**
	 * @Title: queryOperRoleHomeModule
	 * @author：liuyx 
	 * @date：2015年12月3日15:09:20
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Map> queryOperRoleHomeModule(Map paramMap) {
		List list = (List) dao.query("acHomeModuleSpace.queryOperRoleHomeModule", paramMap);
		return list;
	}
	
	/**
	 * @Title: batchInsert
	 * @author：liuyx 
	 * @date：2015年12月3日13:58:30
	 * @Description: 批量插入
	 * @param list
	 * @return
	 */
	@Override
	public int batchInsertOperHomeModule(List<Map> list,Map deleteParam) {
		this.dao.delete("acHomeModuleSpace.deleteOperHomeModule", deleteParam);
		if(CollectionUtils.isEmpty(list)) {
			return 0;
		}
		return dao.insert_("acHomeModuleSpace.batchInsertOperHomeModule", list);
	}
}
