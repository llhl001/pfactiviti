package com.sdyy.activiti.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.common.RetObj;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

@Controller
@RequestMapping("/activiti")
public class ActivitiController {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	 @Autowired
	private 	ProcessEngine processEngine;
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
	 
	 
	 @RequestMapping("forActiviti")
	 public String  forActivitiQueryPage(HttpServletRequest request,PageView pageView) {
		 Map<String,Object> record = HttpUtils.getRequestMap(request);
		 String name=(String) record.get("name");
		 
			try {
				int start=pageView.getPageNow();
				int pageSize=pageView.getPageSize();
				List<ProcessDefinition>pds=repositoryService.createProcessDefinitionQuery()
						/*if(name!=null){
							.processDefinitionName(name)
						}*/
						
																								.orderByProcessDefinitionKey().desc()
																								.orderByProcessDefinitionVersion().desc()
																								.list();
//				List<ProcessDefinition>pds=repositoryService.createProcessDefinitionQuery().listPage((start-1)*pageSize,start*pageSize);
				pageView.setRecords(pds);
			} catch (Exception e) {
				e.printStackTrace();
			}
		request.setAttribute("pageView", pageView);
		 return "activiti/act_forQueryPage";
	 }
		@RequestMapping("forActivitiExample")
		public void forActivitiExample(HttpServletRequest request){
			System.out.println(processEngine);
		}
		
		//删除流程定义流程
		@RequestMapping("activitiDeleteById")
		public RetObj activitiDeleteById(HttpServletRequest request,HttpServletResponse response){
			Map record = HttpUtils.getRequestMap(request);
			String deploymentId=(String) record.get("id");
			try {
				repositoryService.deleteDeployment(deploymentId);
				return new RetObj(true,"删除成功");
			} catch (Exception e) {
				if(logger.isDebugEnabled()){
					logger.error(e.getMessage());
				}
				return new RetObj(false,"删除失败");
			}
		}
		//1发布流程
		@RequestMapping("forActivitiDeploy")
		public RetObj deploy(HttpServletRequest request,HttpServletResponse response){
			Map record = HttpUtils.getRequestMap(request);
			try {
				//获取仓库服务的实例
				Deployment d= processEngine.getRepositoryService()
								.createDeployment()
								.name("TestHello")
								.addClasspathResource("diagrams/helloworld.bpmn")
								.addClasspathResource("diagrams/helloworld.png")
								.deploy();
				System.out.println(d.getId()+""+d.getName());
				return new RetObj(true,"保存成功");
			} catch (Exception e) {
				if(logger.isDebugEnabled()){
					logger.error(e.getMessage());
				}
				return new RetObj(false,"保存失败");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//2启动流程实例
		@RequestMapping("forActivitiRun")
		public void run(){
			//获取流程引擎
			ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
			//使用流程定义的key启动
			ProcessInstance pi= pe.getRuntimeService()
									.startProcessInstanceByKey("helloworld");
			System.out.println(pi.getId());
		}
		//3查看我的个人任务
		@RequestMapping("forActivitiQuery")
		public void queryMyTask(){
			String assignee="张三";
			//获取流程引擎
					ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
				List<Task> tasks=	pe.getTaskService()
						.createTaskQuery()
						.taskAssignee(assignee)
						.list();
			for(Task t:tasks){
				System.out.println(t.getId()+""+t.getName());//5004提交申请
				
			}
		}
		//4完成我的个人任务
		@RequestMapping("forActivitiComplete")
	public void completeTask(){
		ProcessEngine pe =ProcessEngines.getDefaultProcessEngine();
		pe.getTaskService()
		.complete("5004");
		System.out.println("---------");
	}
		
		
		
		
		
}
