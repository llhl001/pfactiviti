/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_function.model;

import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcFunctionTreeNode
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月28日下午3:32:25
 */
public class AcFunctionTreeNode extends ZTreeNode {

	private String funcAction;
	private String paraInfo;
	public String getFuncAction() {
		return funcAction;
	}
	public void setFuncAction(String funcAction) {
		this.funcAction = funcAction;
	}
	public String getParaInfo() {
		return paraInfo;
	}
	public void setParaInfo(String paraInfo) {
		this.paraInfo = paraInfo;
	}
	
	
}
