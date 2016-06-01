/**
 * RetObj.java
 * @author：flying706 @date：2015年8月11日上午8:50:26
 * Copyright2011-2015 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.common;

import javax.servlet.http.HttpServletRequest;

import com.sdyy.common.consts.GlobMessageKeys;
import com.sdyy.common.utils.StringUtils;

/**
 * @Description：消息对象
 * @author：liuyx @date：2015年8月11日上午8:50:26
 */
public class RetObj {
	private boolean flag = true;
	private String msg;
	private Object obj;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public RetObj() {

	}
	/**
	 * 
	 * @param flag 操作是否成功
	 * @param msg 要返回前台的消息
	 * @param obj 要返回前台的数据对象
	 */
	public RetObj(boolean flag, String msg, Object obj) {
		super();
		this.flag = flag;
		this.msg = msg;
		this.obj = obj;
	}
	/**
	 * 
	 * @param flag 操作是否成功
	 * @param msg 要返回前台的消息
	 */
	public RetObj(boolean flag, String msg) {
		super();
		this.flag = flag;
		this.msg = msg;
	}
	public RetObj(boolean flag) {
		super();
		this.flag = flag;
		if(flag) {
			this.msg = "操作成功";
		}else {
			this.msg = "操作失败";
		}
	}
	/**
	 * 返回操作结果，并且返回自带默认消息
	 * @param flag 操作是否成功
	 * @param request 当前request
	 */
	public RetObj(boolean flag,HttpServletRequest request) {
		super();
		this.flag = flag;
		if(flag) {
			this.msg = StringUtils.getGlobMessage(GlobMessageKeys.COMMON_SUCCESS, request);
		}else {
			this.msg = StringUtils.getGlobMessage(GlobMessageKeys.COMMON_FAILED, request);
		}
	}
	/**
	 * 
	 * @param flag 操作是否成功
	 * @param request 当前request
	 * @param obj 同时要返回到前台的对象
	 */
	public RetObj(boolean flag,HttpServletRequest request, Object obj) {
		super();
		this.flag = flag;
		if(flag) {
			this.msg = StringUtils.getGlobMessage(GlobMessageKeys.COMMON_SUCCESS, request);
		}else {
			this.msg = StringUtils.getGlobMessage(GlobMessageKeys.COMMON_FAILED, request);
		}
		this.obj = obj;
	}
	/**
	 * 带全球化文本的返回结果
	 * @param flag
	 * @param msgKey
	 * @param request
	 * @param obj
	 */
	public RetObj(boolean flag, String msgKey,HttpServletRequest request, Object obj) {
		super();
		this.flag = flag;
		String message = StringUtils.getGlobMessage(msgKey, request);
		this.msg = message;
		this.obj = obj;
	}
	public RetObj(boolean flag, String msgKey,HttpServletRequest request) {
		super();
		this.flag = flag;
		String message = StringUtils.getGlobMessage(msgKey, request);
		this.msg = message;
	}
}
