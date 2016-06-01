/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_group.service;

import java.util.List;
import java.util.Map;

import com.sdyy.common.service.IBaseService;
import com.sdyy.common.zTree.ZTreeNode;

/** 
 * @ClassName: IOmGroupService.java
 * @Description: 
 * @author yuqing 
 * @version 2015年9月24日 下午6:02:55 
 */
public interface IOmGroupService extends IBaseService{
	List<ZTreeNode> queryChildrenTreeNodes(String pId);
	
	/**
	 * 验证输入字段是否重复：修改
	 * @author: 于庆
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryUpdate(Map paramMap);
}
