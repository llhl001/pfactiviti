/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.activiti.task.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sdyy.common.RetObj;
import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.zTree.ZTreeNode;


@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	private IBaseDao dao;
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
	@Override
	public Map get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageView queryPage(PageView pageView, Map paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Map> query(Map paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insert(Map record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(Map record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public PageView forTaskQueryPage(Map record, PageView pageView) {
		try {
			int start = pageView.getPageNow();
			int pageSize = pageView.getPageSize();
			long count = taskService.createTaskQuery()
					.count();
			List<Task> ts =taskService.createTaskQuery()
					.orderByTaskCreateTime().desc()
					.listPage((start - 1) * pageSize, pageSize);
		
			pageView.setRowCount(count);
			pageView.setRecords(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}

}
