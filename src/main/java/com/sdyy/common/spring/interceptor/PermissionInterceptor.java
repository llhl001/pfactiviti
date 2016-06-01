package com.sdyy.common.spring.interceptor;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.sdyy.base.ac.ac_function.service.IAcFunctionService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.spring.ComponentFactory;
/**
 * 权限拦截器
 * @ClassName: PermissionInterceptor
 * @Description: 根据权限集判断是否可以登录请求的链接
 * @author: liuyx 
 * @date: 2015年9月21日上午11:05:30
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String path = request.getContextPath();// /platform
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		String uri = request.getRequestURI();// /platform/acApplication/forMain.do
		String link = uri.substring(path.length() + 1); // acApplication/forMain.do
		boolean pass = isOperCanAccess(request, link);
		if (!pass) {
			response.sendRedirect(basePath + "noPermission.do");
			/*HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Class clazz = method.getReturnType();
			ResponseBody body = method.getAnnotation(ResponseBody.class);
			// 返回json形式
			if (null != body && clazz != null && "RetObj".equals(clazz.getSimpleName())) {
				RetObj ret = new RetObj(false, "无权限进行此操作！");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					out.println(JSON.toJSON(ret).toString());
				} finally {
					out.close();
				}
			} else {
				response.sendRedirect(basePath + "noPermission.do");
			}*/

		}
		return pass;
	}
	public static boolean isOperCanAccess(HttpServletRequest request,String link) {
		boolean hasPermission = false;
		IAcFunctionService acFunctionService = (IAcFunctionService)ComponentFactory.getBean("acFunctionServiceImpl");
		Map paramMap = new HashMap();
		paramMap.put("FUNC_ACTION", link);
		List functions = acFunctionService.query(paramMap);
		// 资源未注册说明不用保护
		if (CollectionUtils.isEmpty(functions)) {
			return true;
		}
		HttpSession session=request.getSession();
		List<Map> resourceList = (List)session.getAttribute(BaseController.LOGIN_IN_OPERATOR_RESOURCES_SESSION);
		// 拥有此资源的访问权限
		for(Map m:resourceList) {
			if(link.equals(m.get("FUNC_ACTION"))) {
				return true;
			}
		}
		return hasPermission;
	}
}
