package com.sdyy.activiti.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activiti")
public class ActivitiController {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	 @Autowired
	private 	ProcessEngine processEngine;
	 
	 @RequestMapping("forActiviti")
	 public String  forActiviti(HttpServletRequest request){
		 return "activiti/act_forQueryPage";
	 }
		@RequestMapping("forActivitiExample")
		public void forActivitiExample(HttpServletRequest request){
			System.out.println("111111111111111111111111111");
			System.out.println(processEngine);
			System.out.println("2222222222222222222222222222");
		}
		
		//1发布流程
		@RequestMapping("forActivitiDeploy")
		public void deploy(){
			//获取仓库服务的实例
			Deployment d= processEngine.getRepositoryService()
							.createDeployment()
							.name("TestHello")
							.addClasspathResource("diagrams/helloWorld.bpmn")
							.addClasspathResource("diagrams/helloWorld.png")
							.deploy();
			System.out.println(d.getId()+""+d.getName());
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
