package com.sdyy.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description：处理Controller中返回的异常
 * @author：liuyx  @date：2015年7月20日下午3:05:49
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		// 异常处理，例如将异常信息存储到数据库

        // 视图显示专门的错误页
        //ModelAndView modelAndView = new ModelAndView("errorPage");
        //return modelAndView;
		
		//返回NULL 交给web.xml中的 error-page标签处理
		return null;
	}

}
