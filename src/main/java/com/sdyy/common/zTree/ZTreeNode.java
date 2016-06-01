/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.common.zTree;



/**
 * @ClassName: TreeNode
 * @Description: zTree 节点
 * @author: liuyx 
 * @date: 2015年9月6日下午3:34:45
 */
public class ZTreeNode {
	private String id;
	private String pId;
	private String name;
	private String type;
	private boolean checked=false;
	private String iconSkin = "";
	private boolean isParent;
	private String code;
	private boolean open = false;
	private String extendAttr;//拓展备用字段  组织机构中的机构ID；功能应用中的应用ID；菜单管理中的应用ID
	private String extendAttr1;//拓展备用字段  组织机构中的岗位ID；功能应用中的功能组ID；菜单管理中的菜单全链接（含参数）
	private boolean nocheck = false;
/*	private boolean chkDisabled = false;
	
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}*/
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public String getExtendAttr1() {
		return extendAttr1;
	}
	public void setExtendAttr1(String extendAttr1) {
		this.extendAttr1 = extendAttr1;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public String getExtendAttr() {
		return extendAttr;
	}
	public void setExtendAttr(String extendAttr) {
		this.extendAttr = extendAttr;
	}
	
}
