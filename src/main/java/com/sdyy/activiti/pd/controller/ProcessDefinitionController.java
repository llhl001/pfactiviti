package com.sdyy.activiti.pd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sdyy.activiti.pd.service.ProcessDefinitionServiceImpl;
import com.sdyy.common.RetObj;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;

@Controller
@RequestMapping("/pd")
public class ProcessDefinitionController {
	@Autowired
	private ProcessDefinitionServiceImpl processDefinitionService;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	// 流程定义查询
	@RequestMapping("query")
	public String query(HttpServletRequest request,
			PageView pageView) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		pageView=processDefinitionService.forProcessDefinitionQueryPage(record,pageView);
		request.setAttribute("pageView", pageView);
		return "activiti/pd/pd_list";
	}

	// 删除流程定义流程
	@RequestMapping("deleteById")
	@ResponseBody
	public RetObj deleteById(HttpServletRequest request,	HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		boolean flag=processDefinitionService.deleteProcessDefinitionById(record);
		return new RetObj(flag,request);
	}
/*	// 发布流程
		@RequestMapping("deploy")
		public String deploy(HttpServletRequest request,HttpServletResponse response) {
			Map record = HttpUtils.getRequestMap(request);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			if(record!=null&&multipartRequest!=null){
				processDefinitionService.processDefinitionDeploy(request);
			}
			return "activiti/pd/pd_deploy";
		}*/
	// 添加流程定义
	@RequestMapping("deploy")
	public String deploy(HttpServletRequest request,	HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		return "activiti/pd/pd_deploy";
	}

	// 发布流程
	@RequestMapping("processDefinitionDeploy")
	//@ResponseBody
	public String processDefinitionDeploy(HttpServletRequest request,	HttpServletResponse response) {
			processDefinitionService.processDefinitionDeploy(request);
			//return new RetObj(true,request);
			 return "redirect:/pd/deploy.do"; 
	}
}
