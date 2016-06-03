<<<<<<< HEAD
package com.sdyy.activiti.processDefinition.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.activiti.processDefinition.service.ProcessDefinitionServiceImpl;
import com.sdyy.common.RetObj;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;

@Controller
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {
	@Autowired
	private ProcessDefinitionServiceImpl processDefinitionService;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// =================================================================================
	// 流程定义查询
	@RequestMapping("forProcessDefinitionQueryPage")
	public String forProcessDefinitionQueryPage(HttpServletRequest request,
			PageView pageView) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		pageView=processDefinitionService.forProcessDefinitionQueryPage(record,pageView);
		request.setAttribute("pageView", pageView);
		return "activiti/processDefinition/acti_forProcessDefinitionQueryPage";
	}

	// 删除流程定义流程
	@RequestMapping("deleteProcessDefinitionById")
	@ResponseBody
	public RetObj deleteProcessDefinitionById(HttpServletRequest request,	HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		boolean flag=processDefinitionService.deleteProcessDefinitionById(record);
		return new RetObj(flag,request);
	}
	
	// 添加流程定义
	@RequestMapping("forProcessDefinitionDeploy")
	public String forProcessDefinitionDeploy(HttpServletRequest request,PageView pageView) {
		return "activiti/processDefinition/acti_forProcessDefinitionDeploy";
	}

	// 发布流程
	@RequestMapping("processDefinitionDeploy")
	public String processDefinitionDeploy(HttpServletRequest request,			HttpServletResponse response) {
		processDefinitionService.processDefinitionDeploy(request);
	        return "redirect:/processDefinition/forProcessDefinitionDeploy.do"; 
	}
	// =================================================================================
/*
	@RequestMapping("forActivitiExample")
	public void forActivitiExample(HttpServletRequest request) {
		System.out.println(processEngine);
	}

	// 1发布流程
	@RequestMapping("forActivitiDeploy")
	public RetObj forActivitiDeploy(HttpServletRequest request,
			HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		try {
			// 获取仓库服务的实例
			Deployment d = processEngine.getRepositoryService()
					.createDeployment().name("TestHello")
					.addClasspathResource("diagrams/helloworld.bpmn")
					.addClasspathResource("diagrams/helloworld.png").deploy();
			System.out.println(d.getId() + "" + d.getName());
			return new RetObj(true, "保存成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "保存失败");
		}
	}

	// 1发布流程
	@RequestMapping("forActivitiDeploy1")
	public RetObj deploy(HttpServletRequest request,
			HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		try {
			// 获取仓库服务的实例
			Deployment d = processEngine.getRepositoryService()
					.createDeployment().name("TestHello")
					.addClasspathResource("diagrams/helloworld.bpmn")
					.addClasspathResource("diagrams/helloworld.png").deploy();
			System.out.println(d.getId() + "" + d.getName());
			return new RetObj(true, "保存成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "保存失败");
		}
	}

	// 2启动流程实例
	@RequestMapping("forActivitiRun")
	public void run() {
		// 获取流程引擎
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		// 使用流程定义的key启动
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(
				"helloworld");
		System.out.println(pi.getId());
	}

	// 3查看我的个人任务
	@RequestMapping("forActivitiQuery")
	public void queryMyTask() {
		String assignee = "张三";
		// 获取流程引擎
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		List<Task> tasks = pe.getTaskService().createTaskQuery()
				.taskAssignee(assignee).list();
		for (Task t : tasks) {
			System.out.println(t.getId() + "" + t.getName());// 5004提交申请
		}
	}

	// 4完成我的个人任务
	@RequestMapping("forActivitiComplete")
	public void completeTask() {
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		pe.getTaskService().complete("5004");
		System.out.println("---------");
	}*/

}
=======
package com.sdyy.activiti.processDefinition.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sdyy.common.RetObj;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

@Controller
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {

	private Logger logger = Logger.getLogger(this.getClass().getName());
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

	// =================================================================================

	// 流程定义查询
	@RequestMapping("forProcessDefinitionQueryPage")
	public String forProcessDefinitionQueryPage(HttpServletRequest request,
			PageView pageView) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		String name = (String) record.get("name");
		try {
			int start = pageView.getPageNow();
			int pageSize = pageView.getPageSize();
			List<ProcessDefinition> pds = repositoryService
					.createProcessDefinitionQuery()
					.orderByProcessDefinitionKey().desc()
					.orderByProcessDefinitionVersion().desc().list();
			// List<ProcessDefinition>pds=repositoryService.createProcessDefinitionQuery().listPage((start-1)*pageSize,pageSize);
			// pageView.setRowCount(pds.size());
			pageView.setRecords(pds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageView", pageView);
		return "activiti/processDefinition/acti_forProcessDefinitionQueryPage";
	}

	// 删除流程定义流程
	@RequestMapping("deleteProcessDefinitionById")
	public RetObj deleteProcessDefinitionById(HttpServletRequest request,
			HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		String deploymentId = (String) record.get("id");
		try {
			repositoryService.deleteDeployment(deploymentId);
			return new RetObj(true, "删除成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "删除失败");
		}
	}
	// 添加流程定义
	@RequestMapping("forProcessDefinitionDeploy")
	public String forProcessDefinitionDeploy(HttpServletRequest request,
			PageView pageView) {
		return "activiti/processDefinition/acti_forProcessDefinitionDeploy";
	}

	// 1发布流程
///*	@RequestMapping("processDefinitionDeploy")
	/*public RetObj processDefinitionDeploy(HttpServletRequest request,
			HttpServletResponse response) {
		*/
//		@RequestMapping(value = "processDefinitionDeploy")
	@RequestMapping("processDefinitionDeploy")
	/*	public void processDefinitionDeploy(HttpServletRequest request, HttpServletResponse response, @RequestParam ArrayList<MultipartFile> multipartFiles,
				Integer chunk, Integer chunks, String uuid) {*/
	public void processDefinitionDeploy(HttpServletRequest request,
			HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		
		 MultipartFile multipartFile = multipartRequest.getFile("file");
	        /** 获取文件的后缀* */
	        String suffix = multipartFile.getOriginalFilename().substring(
	                multipartFile.getOriginalFilename().lastIndexOf("."));
	        /** 使用UUID生成文件名称* */
	        String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
		
		System.out.println(record);
		/*for (MultipartFile multipartFile : multipartFiles) {
			String name = multipartFile.getOriginalFilename();
			String fileType = FilenameUtils.getExtension(name);
			String fileId = UUID.randomUUID().toString().replace("-", "");
			String	newFileName = fileId.concat(".").concat(fileType);
			String nFname = newFileName;
		}*/
		
	/*	try {
			// 获取仓库服务的实例
			Deployment d = processEngine.getRepositoryService()
					.createDeployment().name("TestHello")
					.addClasspathResource("diagrams/helloworld.bpmn")
					.addClasspathResource("diagrams/helloworld.png").deploy();
			System.out.println(d.getId() + "" + d.getName());
			return new RetObj(true, "保存成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "保存失败");
		}*/
	}
	// =================================================================================

	@RequestMapping("forActivitiExample")
	public void forActivitiExample(HttpServletRequest request) {
		System.out.println(processEngine);
	}

	// 1发布流程
	@RequestMapping("forActivitiDeploy")
	public RetObj forActivitiDeploy(HttpServletRequest request,
			HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		try {
			// 获取仓库服务的实例
			Deployment d = processEngine.getRepositoryService()
					.createDeployment().name("TestHello")
					.addClasspathResource("diagrams/helloworld.bpmn")
					.addClasspathResource("diagrams/helloworld.png").deploy();
			System.out.println(d.getId() + "" + d.getName());
			return new RetObj(true, "保存成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "保存失败");
		}
	}

	// 1发布流程
	@RequestMapping("forActivitiDeploy1")
	public RetObj deploy(HttpServletRequest request,
			HttpServletResponse response) {
		Map record = HttpUtils.getRequestMap(request);
		try {
			// 获取仓库服务的实例
			Deployment d = processEngine.getRepositoryService()
					.createDeployment().name("TestHello")
					.addClasspathResource("diagrams/helloworld.bpmn")
					.addClasspathResource("diagrams/helloworld.png").deploy();
			System.out.println(d.getId() + "" + d.getName());
			return new RetObj(true, "保存成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "保存失败");
		}
	}

	// 2启动流程实例
	@RequestMapping("forActivitiRun")
	public void run() {
		// 获取流程引擎
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		// 使用流程定义的key启动
		ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(
				"helloworld");
		System.out.println(pi.getId());
	}

	// 3查看我的个人任务
	@RequestMapping("forActivitiQuery")
	public void queryMyTask() {
		String assignee = "张三";
		// 获取流程引擎
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		List<Task> tasks = pe.getTaskService().createTaskQuery()
				.taskAssignee(assignee).list();
		for (Task t : tasks) {
			System.out.println(t.getId() + "" + t.getName());// 5004提交申请

		}
	}

	// 4完成我的个人任务
	@RequestMapping("forActivitiComplete")
	public void completeTask() {
		ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
		pe.getTaskService().complete("5004");
		System.out.println("---------");
	}

}
>>>>>>> origin/master
