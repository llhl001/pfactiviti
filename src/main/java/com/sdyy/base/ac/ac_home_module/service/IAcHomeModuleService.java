/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_home_module.service;

import java.util.List;
import java.util.Map;

import com.sdyy.common.service.IBaseService;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: IAcHomeModuleService
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午3:39:36
 */
public interface IAcHomeModuleService extends IBaseService {
	
    /**
     * 
     * @Title: batchDelete
     * @author：liuyx 
     * @date：2015年12月4日下午2:20:39
     * @Description: 批量删除
     * @param ids
     * @return
     */
	public int batchDelete(String ids);
	/**
	 * 
	 * @Title: queryNodesForRole
	 * @author：flying706 
	 * @date：2015年12月3日下午3:06:54
	 * @Description: 获取角色和首页模块选择列表，包含已选项目的初始化
	 * @param paramMap
	 * @return
	 */
	List<ZTreeNode> queryNodesForRole(Map<String,Object> paramMap);
	
	/**
	 * 
	 * @Title: batchInsertRoleHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:06:29
	 * @Description: 批量插入角色和首页模块的关系
	 * @param list
	 * @param deleteParam
	 * @return
	 */
	int batchInsertRoleHomeModule(List<Map> list,Map deleteParam);
	
	//------------------------------------------------------------------
	
	/**
	 * 
	 * @Title: queryOperHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:05:40
	 * @Description: 获取操作员自定义主页模块列表
	 * @param paramMap
	 * @return
	 */
	List<Map> queryOperHomeModule(Map<String,Object> paramMap);
	
	/**
	 * 
	 * @Title: queryOperHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:05:40
	 * @Description: 获取操作员拥有角色的所有模块列表
	 * @param paramMap
	 * @return
	 */
	List<Map> queryOperRoleHomeModule(Map<String,Object> paramMap);
	
	/**
	 * 
	 * @Title: batchInsertOperHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日下午3:06:29
	 * @Description: 批量插入角色和首页模块的关系
	 * @param list
	 * @param deleteParam
	 * @return
	 */
	int batchInsertOperHomeModule(List<Map> list,Map deleteParam);
}
