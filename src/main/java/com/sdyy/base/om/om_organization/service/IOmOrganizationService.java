/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_organization.service;

import java.util.List;
import java.util.Map;

import com.sdyy.common.service.IBaseService;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: IOmOrganizationService
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午3:39:36
 */
public interface IOmOrganizationService extends IBaseService {

	List<ZTreeNode> queryChildrenTreeNodes(Map<String,Object> paramMap);
	
	int adjust(String id,String type,String fromId,
			String fromType,String toId,String toType,String isCopy);
}
