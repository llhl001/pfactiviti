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
