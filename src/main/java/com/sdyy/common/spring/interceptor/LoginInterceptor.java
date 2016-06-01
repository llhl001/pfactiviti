package com.sdyy.common.spring.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sdyy.common.controller.BaseController;

//import base.common.Access;

/**
 * 登录拦截器
 * @ClassName: LoginInterceptor
 * @Description: 判断是否有session，或者是否是无需验证的请求链接
 * @author: liuyx 
 * @date: 2015年9月21日上午11:16:18
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HandlerMethod method = (HandlerMethod) handler;
//		Access access = method.getMethodAnnotation(Access.class);
//		if (access != null) {
//			String role = access.role();
//			System.out.println(role + "  fffffffffffffffffffffffffffffffffffffffffff");
//		}

		// method.getMethodAnnotation(annotationType)
		// System.out.println(method.);
		// MethodNameResolver methodNameResolver = new
		// InternalPathMethodNameResolver();
		// System.out.println("methodName="+methodNameResolver.getHandlerMethodName(request));

		String path = request.getContextPath();
		//String ctxPath = (String) request.getSession().getServletContext().getAttribute("ctx");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		/*String[] noFilters = new String[] { "/login", "/verficationCode", "loginCheck", "/error", "/sessionTimeOut", "/directAudit", "/register","/logout","/noPermission"};*/
		String[] noFilters = new String[] { "/login", "/loginCheck", "/sessionTimeOut","/logout","/sysFile/batchUpload.do"};
		String uri = request.getRequestURI();
		boolean pass = false;
		for (int i = 0; i < noFilters.length; i++) {
			if (uri.startsWith(path + noFilters[i])) {
				pass = true;
				break;
			}
		}
		if (pass) {
			return true;
		} else {
			if (request.getSession().getAttribute(BaseController.LOGIN_IN_OPERATOR_SESSION) != null) {
				return true;
			} else {
				response.sendRedirect(basePath + "sessionTimeOut.do");
				return false;
			}
		}
	}

}
