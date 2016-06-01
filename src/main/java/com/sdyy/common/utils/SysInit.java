/**   
 * @Title: SysInit.java
 * @Package com.sdecloud.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author caojian   2013-5-17 下午3:46:34 
 */
package com.sdyy.common.utils;
 

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * 
 * @ClassName: SysInit
 * @Description: 定义全局Context变量
 * @author: caojian 
 * @date: 2015年8月21日下午6:12:13
 */
public class SysInit implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext application = arg0.getServletContext();
		//系统名称
        String sysname = application.getInitParameter("sysname");
        application.setAttribute("sysname", sysname);
        //全局上下文路径
        application.setAttribute("ctx", application.getContextPath());

	}
}
