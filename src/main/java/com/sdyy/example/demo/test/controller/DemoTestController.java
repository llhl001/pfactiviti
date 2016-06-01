package com.sdyy.example.demo.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.common.RetObj;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.example.demo.test.service.IDemoTestService;

@Controller
@RequestMapping("/demoTest")
public class DemoTestController {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private IDemoTestService demoTestService;
	
	/**
	 * 
	 * @Title: forUpdate
	 * @author：liuyx 
	 * @date：2015年8月28日下午3:04:31
	 * @Description: 进入新增/修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("forUpdate")
	public String forUpdate(HttpServletRequest request){
		
		return "example/demo/test/demo_test_forUpdate";
	}
	

	
	@RequestMapping("update")
	@ResponseBody
	public RetObj update(HttpServletRequest request,HttpServletResponse response){
		Map record = HttpUtils.getRequestMap(request);
		try {
			if(StringUtils.isEmpty(record.get("TEST_ID"))) {
				record.put("TEST_ID", StringUtils.uniqueKey36());
				demoTestService.insert(record);
			}
			return new RetObj(true,"保存成功");
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,"保存失败");
		}
	}
	
	@RequestMapping("forQueryPage")
	public String forQueryPage(HttpServletRequest request){
		return "example/demo/test/demo_test_forQueryPage";
	}
	
	@RequestMapping("forDetail")
	public String forDetail(HttpServletRequest request){
		
		return "example/demo/test/demo_test_forDetail";
	}

	
	/*@Autowired 
	CookieLocaleResolver resolver;
	//@Autowired 
	//SessionLocaleResolver resolver;
	*//**
	 * 语言动态切换思路 可做成拦截器
	 *//*
	@RequestMapping("testLanguage")
	public String testLanguage(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		String language="zh_CN".toLowerCase();
		language="en_US";
		if(language==null||language.equals("")){
			
		}else{
			if(language.equals("zh_CN")){
				resolver.setLocale(request, response, Locale.CHINA );
			}else if(language.equals("en_US")){
				resolver.setLocale(request, response, Locale.US );
			}else{
				resolver.setLocale(request, response, Locale.CHINA );
			}
		}
		request.setAttribute("a", "");
		return "example/demo/test/testLanguage";
	}*/
}
