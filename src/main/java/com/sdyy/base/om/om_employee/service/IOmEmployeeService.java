/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_employee.service;

import java.util.List;
import java.util.Map;

import com.sdyy.common.service.IBaseService;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: IOmEmployeeService
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午3:39:36
 */
public interface IOmEmployeeService extends IBaseService {
	List<ZTreeNode> queryTreeNodes(Map paramMap);
	/**
	 * 
	 * @Title: queryOrgEmpTreeNodes
	 * @author：liuyx 
	 * @date：2015年11月19日上午8:37:28
	 * @Description: 仅查询机构人员树，无岗位
	 * @param paramMap
	 * @return
	 */
	List<ZTreeNode> queryOrgEmpTreeNodes(Map paramMap);
}
