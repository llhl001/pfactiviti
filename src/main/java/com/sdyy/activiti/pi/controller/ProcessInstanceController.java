package com.sdyy.activiti.pi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.activiti.pi.service.ProcessInstanceServiceImpl;
import com.sdyy.common.RetObj;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;

@Controller
@RequestMapping("/pi")
public class ProcessInstanceController {
	@Autowired
	private ProcessInstanceServiceImpl processInstanceService;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// =================================================================================
	// 流程定义查询
	@RequestMapping("query")
	public String query(HttpServletRequest request,PageView pageView) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		pageView.setPageSize(5);
		PageView pds=processInstanceService.forProcessDefinitionQueryPage(record,pageView);
		///PageView pis=processInstanceService.queryProcessInstance();
		List<ProcessInstance>pis=processInstanceService.queryProcessInstance();
		/*request.setAttribute("pds", pds);
		request.setAttribute("pis", pis);*/
		request.setAttribute("pageView", pageView);
		request.setAttribute("pis", pis);
		return "activiti/pi/pi_list";
	}
	// 启动流程实例
	@RequestMapping("startProcessInstance")
	@ResponseBody
	public RetObj startProcessInstance(HttpServletRequest request,			PageView pageView) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		boolean flag=processInstanceService.startProcessInstance(record);
		return new RetObj(flag,request);
	}
	// =================================================================================
}
