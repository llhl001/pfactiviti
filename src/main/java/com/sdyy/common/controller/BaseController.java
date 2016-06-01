package com.sdyy.common.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.sdyy.common.spring.StringEscapeEditor;
import com.sdyy.common.utils.StringUtils;

public class BaseController {
	/**
	 * 登录操作员信息
	 */
	public static final String LOGIN_IN_OPERATOR_SESSION = "loginOperator";
	/**
	 * 登录操作员所拥有的权限集
	 */
	public static final String LOGIN_IN_OPERATOR_RESOURCES_SESSION = "urlList";
	/**
	 * 登录操作员所对应的员工信息
	 */
	public static final String LOGIN_IN_EMP_SESSION = "loginEmp";

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(false, false));
	}

	// 日志记录器
	public final Logger logger = Logger.getLogger(this.getClass());
	
	public static String getOperatorIdFromRequest(HttpServletRequest request) {
		HttpSession session=request.getSession();
		//账号登录信息
		Map acOperatorMap = (Map)session.getAttribute(LOGIN_IN_OPERATOR_SESSION);
		String operatorId = acOperatorMap.get("OPERATOR_ID").toString();
		return operatorId;
	}
	public static String getEmpIdFromRequest(HttpServletRequest request) {
		HttpSession session=request.getSession();
		//账号登录信息
		Map omEmployeeMap = (Map)session.getAttribute(LOGIN_IN_EMP_SESSION);
		String empId = null;
		if(omEmployeeMap!=null) {
			empId = omEmployeeMap.get("EMP_ID").toString();
			
		}
		return empId;
	}
	public static String getAttributeFromEmpSession(HttpServletRequest request,String key) {
		HttpSession session=request.getSession();
		//账号登录信息
		Map omEmployeeMap = (Map)session.getAttribute(LOGIN_IN_EMP_SESSION);
		String attribute = null;
		if(omEmployeeMap!=null) {
			attribute = StringUtils.getTrimStr(omEmployeeMap.get(key));
			
		}
		return attribute;
	}
}
