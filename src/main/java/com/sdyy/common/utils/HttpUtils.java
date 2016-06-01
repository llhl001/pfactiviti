/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.common.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName: HttpUtils
 * @Description: 和requst response session等对象相关的工具类
 * @author: liuyx 
 * @date: 2015年8月28日下午4:32:51
 */
public class HttpUtils {
	/**
	 * 
	 * @Title: getRequestMap
	 * @author：liuyx 
	 * @date：2015年8月28日下午4:37:08
	 * @Description: 获取简单表单对象，转化为map
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getRequestMap(HttpServletRequest request){
		Map<String,Object> requestMap = new HashMap<String,Object>();
		Enumeration<String> enu = request.getParameterNames();
		while(enu.hasMoreElements()){
		       String key=enu.nextElement().toString();
		       String value=request.getParameter(key);
		       requestMap.put(key, value);
		}
		return requestMap;
	}
	
	/**
	 * 
	 * @Title: getCurrentRequest
	 * @author：liuyx 
	 * @date：2016年1月13日下午6:14:43
	 * @Description: 获取当前request
	 * @return
	 * @throws IllegalStateException 当前线程不是web请求抛出此异常.
	 */
    public static HttpServletRequest getCurrentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
    }
}
