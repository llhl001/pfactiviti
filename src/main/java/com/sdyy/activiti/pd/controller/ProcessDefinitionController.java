package com.sdyy.activiti.pd.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
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
	private ProcessEngine processEngine;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
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
	
	
	// 查看流程定义的流程图
	@RequestMapping("showView")
	public void showView(HttpServletRequest request,	HttpServletResponse response) throws IOException {
		Map record = HttpUtils.getRequestMap(request);
		String type = (String) record.get("type");
		String deploymentId = (String) record.get("deploymentId");
		List<String> names=repositoryService.getDeploymentResourceNames(deploymentId);
		String fileName="";
			for(String name:names){
				if(type.equalsIgnoreCase("XML")){
					if(name.indexOf(".bpmn")>=0){
						fileName=name;
					}
				}else{
					if(name.indexOf(".png")>=0){
						fileName=name;
					}
				}
			}
			if(fileName!=null){
				InputStream	in=processEngine.getRepositoryService().getResourceAsStream(deploymentId, fileName);
				try {
					//FileUtils.copyInputStreamToFile(in, f);
					byte[] b = new byte[1024];
				      int len = -1;
				      while ((len = in.read(b, 0, 1024)) != -1) {
				          response.getOutputStream().write(b, 0, len);
				      }
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	
}
