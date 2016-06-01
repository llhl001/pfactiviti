/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_group.model;

import com.sdyy.common.zTree.ZTreeNode;

/** 
 * @ClassName: OmGroupTreeNode.java
 * @Description: 
 * @author yuqing 
 * @version 2015年9月25日 上午9:21:30 
 */

public class OmGroupTreeNode extends ZTreeNode{

//	private String groupAction;//菜单链接
//	private String groupParam;//菜单参数
//	private String groupLabel;//菜单对用户的显示名
	private boolean isLeaf;//是否叶子菜单
//	private String groupCss;

	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	
}
