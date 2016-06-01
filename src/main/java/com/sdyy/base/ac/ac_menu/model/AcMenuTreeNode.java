/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_menu.model;

import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcMenuTreeNode
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月23日上午9:37:00
 */
public class AcMenuTreeNode extends ZTreeNode {

	private String menuAction;//菜单链接
	private String menuParam;//菜单参数
	private String menuLabel;//菜单对用户的显示名
	private boolean isLeaf;//是否叶子菜单
	private String menuCss;
	
	public String getMenuCss() {
		return menuCss;
	}
	public void setMenuCss(String menuCss) {
		this.menuCss = menuCss;
	}
	public String getMenuAction() {
		return menuAction;
	}
	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}
	public String getMenuParam() {
		return menuParam;
	}
	public void setMenuParam(String menuParam) {
		this.menuParam = menuParam;
	}
	public boolean getIsLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getMenuLabel() {
		return menuLabel;
	}
	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}
	
	
}
