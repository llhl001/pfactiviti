/**   
 * @Title: LoginControler.java
 * @Package org.sdyy.yee.ac.ac_operator.controler
 * @Description: TODO(用一句话描述该文件做什么)
 * @author caojian   2013-5-21 下午1:53:20
 */
package com.sdyy.base.ac.ac_operator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.ac.ac_function.service.IAcFunctionService;
import com.sdyy.base.ac.ac_home_module.service.IAcHomeModuleService;
import com.sdyy.base.ac.ac_menu.service.IAcMenuService;
import com.sdyy.base.ac.ac_operator.service.IAcOperatorService;
import com.sdyy.base.om.om_employee.service.IOmEmployeeService;
import com.sdyy.common.RetObj;
import com.sdyy.common.consts.CommonConsts;
import com.sdyy.common.consts.GlobMessageKeys;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

/**
 * @ClassName: LoginControler
 * @Description: 控制登录相关
 * @author caojian 2015年8月21日18:33:44
 */
@Controller
public class LoginController extends BaseController {
	@Autowired
	private IAcOperatorService acOperatorService;
	@Autowired
	private IOmEmployeeService omEmployeeService;
	
	@Autowired
	private IAcMenuService acMenuService;
	@Autowired
	private IAcFunctionService acFunctionService;
	@Autowired
	private IAcHomeModuleService acHomeModuleService;
	
	@RequestMapping("/loginCheck")
	@ResponseBody
	public RetObj loginCheck(HttpServletRequest request, HttpServletResponse response, String userId,String password) {
		
		//先注销
		HttpSession session=request.getSession();
		/*ServletContext application =session.getServletContext(); */
		Map acOperatorMap=null;
		try {
			acOperatorMap = acOperatorService.checkLogin(userId, password);
			 
			if (acOperatorMap == null) {
				return new RetObj(false,GlobMessageKeys.LOGIN_WRONG_INFO, request);
	        }else if(!CommonConsts.OPERATOR_STATUS_NORMAL.equals(acOperatorMap.get("STATUS"))){//判断只有在岗的用户才可以登录
	        	return new RetObj(false,GlobMessageKeys.LOGIN_NO_PERMISSION, request);
			}else{
				/**保存用户session信息*/
				//账号登录信息
				session.setAttribute(LOGIN_IN_OPERATOR_SESSION, acOperatorMap);
//				//对应EMP信息
//				Map paramMap = new HashMap();
//				String operatorId = acOperatorMap.get("OPERATOR_ID").toString();
//				paramMap.put("OPERATOR_ID", operatorId);
//				
//				List employeeMapList = omEmployeeService.query(paramMap);
//				if(!CollectionUtils.isEmpty(employeeMapList)) {
//					session.setAttribute(LOGIN_IN_EMP_SESSION, employeeMapList.get(0));
//				}
//				//拥有的资源
//				List permittedFuncionts = acFunctionService.queryPermitted(paramMap);
//				session.setAttribute(LOGIN_IN_OPERATOR_RESOURCES_SESSION, permittedFuncionts);
		        	
	        }
		} catch (Exception e) { 
			e.printStackTrace(); 
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,GlobMessageKeys.LOGIN_SYS_ERR, request);
		} 
		
		return new RetObj(true);
	}
	
	private void reloadSession(HttpServletRequest request) {
		HttpSession session=request.getSession();
		//账号登录信息
		Map acOperatorMap = (Map)session.getAttribute(LOGIN_IN_OPERATOR_SESSION);
		//对应EMP信息
		Map paramMap = new HashMap();
		String operatorId = acOperatorMap.get("OPERATOR_ID").toString();
		paramMap.put("OPERATOR_ID", operatorId);
		
		List employeeMapList = omEmployeeService.query(paramMap);
		if(!CollectionUtils.isEmpty(employeeMapList)) {
			session.setAttribute(LOGIN_IN_EMP_SESSION, employeeMapList.get(0));
		}
		//拥有的资源
		List permittedFuncionts = acFunctionService.queryPermitted(paramMap);
		session.setAttribute(LOGIN_IN_OPERATOR_RESOURCES_SESSION, permittedFuncionts);
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session) {
		if(session!=null) {
			session.removeAttribute(LOGIN_IN_OPERATOR_SESSION);
			session.removeAttribute(LOGIN_IN_EMP_SESSION);
			session.removeAttribute(LOGIN_IN_OPERATOR_RESOURCES_SESSION);
		}
		return "login";
	}
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {
		reloadSession(request);
		return "main";
	}
	
	@RequestMapping("/top")
	public String top(HttpServletRequest request) {
		Map paramMap = new HashMap();
		paramMap.put("MENU_LEVEL", "1");

		String operatorId = getOperatorIdFromRequest(request);
		paramMap.put("OPERATOR_ID", operatorId);
		//paramMap.put("APP_ID", appId);
		//此处应有APP_ID获取，设为查询参数
		
		List<Map> topMenuList = acMenuService.queryPermitted(paramMap);
		request.setAttribute("topMenuList", topMenuList);
		return "top";
	}
	@RequestMapping("/left")
	public String left(HttpServletRequest request,String pId,String pName) {
		
		//request=HttpUtils.getCurrentRequest();
		
		Map paramMap = new HashMap();
		paramMap.put("PARENT_ID", pId);
		String operatorId = getOperatorIdFromRequest(request);
		paramMap.put("OPERATOR_ID", operatorId);
		//此处应有APP_ID获取，设为查询参数
		if(!StringUtils.isEmpty(pId)) {
			List<Map> leftMenuList = acMenuService.queryPermitted(paramMap);
			request.setAttribute("leftMenuList", leftMenuList);
			request.setAttribute("pName", pName);
		}else {
			request.setAttribute("pName", "首页");
		}
		
		return "left";
	}
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		String operatorId = getOperatorIdFromRequest(request);
		Map paramMap = new HashMap();
		paramMap.put("OPERATOR_ID", operatorId);

		//1、获取自定义列表
		List<Map> operHomeModuleViewList = acHomeModuleService.queryOperHomeModule(paramMap);
		List<Map> allPermHomeModuleViewList = new ArrayList<Map>();
		if(CollectionUtils.isEmpty(operHomeModuleViewList)) {
			operHomeModuleViewList =  acHomeModuleService.queryOperRoleHomeModule(paramMap);
			allPermHomeModuleViewList = new ArrayList<Map>(operHomeModuleViewList);
			//Collections.copy(allPermHomeModuleViewList, operHomeModuleViewList);
			for(Map record:allPermHomeModuleViewList) {
				record.put("CHECKED", "true");
			}
		}else {
			allPermHomeModuleViewList = acHomeModuleService.queryOperRoleHomeModule(paramMap);
			for(Map recorda:allPermHomeModuleViewList) {
				for(Map recordo:operHomeModuleViewList) {
					if(recordo.get("HOME_MODULE_ID").equals(recorda.get("HOME_MODULE_ID"))) {
						recorda.put("CHECKED", "true");
						break;
					}
				}
				
			}
		}
		request.setAttribute("operHomeModuleViewList", operHomeModuleViewList);
		request.setAttribute("allPermHomeModuleViewList", allPermHomeModuleViewList);
		//2、编辑时 获取权限列表   已设置check
		
		return "home";
	}
	@RequestMapping("/right")
	public String right() {
		return "right";
	}
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/footer")
	public String footer() {
		return "footer";
	}
	@RequestMapping("/sessionTimeOut")
	public String sessionTimeOut(HttpServletRequest request) {
		return "sessionTimeOut";
	}
	@RequestMapping("/noPermission")
	public String noPermission(HttpServletRequest request) {
		return "noPermission";
	}
}
