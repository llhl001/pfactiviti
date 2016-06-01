
package com.sdyy.common.spring;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sdyy.common.service.IBaseService;

/**
 * @Title: ComponentFactory.java
 * @Package org.zxgs.jweb.web
 * @Description: 方便获取spring bean工厂中的bean
 * @author liuyx
 * @date 2015年9月5日16:14:51
 * @version V1.0
 */
public class ComponentFactory implements ApplicationContextAware {
	private static ApplicationContext ctx;//Spring应用上下文环境
	
	public static IBaseService getService(String id){
		if(ctx.containsBeanDefinition(id)) {
			Object o = ctx.getBean(id);
			if(o instanceof IBaseService) {
				return (IBaseService)ctx.getBean(id);
			}
		}
		return null;
	}
	
	/**
	 * 从静态变量ctx中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
			return ctx.getBean(requiredType);
	}
	
	public static Object getBean(String id){
		if(ctx.containsBeanDefinition(id)) {
			return ctx.getBean(id);
		}else {
			return null;
		}
		
	}
	/**
	 *@Description: 获取ApplicationContext
	 *@author liuyx
	 *@date 2012-8-10 上午9:23:01
	 *@param arg0
	 *@throws BeansException
	 *@see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext application)
			throws BeansException {
		// TODO Auto-generated method stub
		ComponentFactory.ctx = application; 
	}
	/**
	 * @return ctx
	 */
	public static ApplicationContext getCtx() {
		return ctx;
	}
	/**
	 *@param ctx 要设置的 ctx
	 *//*
	public static void setCtx(ApplicationContext ctx) {
		ComponentFactory.ctx = ctx;
	}*/
	
}
