package com.sdyy.activiti.task.controller;

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
import com.sdyy.activiti.task.service.TaskServiceImpl;
import com.sdyy.common.RetObj;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;

@Controller
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskServiceImpl taskService;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	// =================================================================================
	// 流程定义查询
	@RequestMapping("query")
	public String query(HttpServletRequest request,PageView pageView) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		PageView pds=taskService.forTaskQueryPage(record,pageView);
		request.setAttribute("pageView", pageView);
		return "activiti/task/task_list";
	}
}
