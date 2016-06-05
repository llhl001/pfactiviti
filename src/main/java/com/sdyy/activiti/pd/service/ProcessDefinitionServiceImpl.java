/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.activiti.pd.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.commons.io.FileUtils;
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
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {


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
	public PageView forProcessDefinitionQueryPage(Map record, PageView pageView) {
		String name = (String) record.get("name");
		try {
			int start = pageView.getPageNow();
			int pageSize = pageView.getPageSize();
			if (!StringUtils.isEmpty(name)) {
				long count = repositoryService.createProcessDefinitionQuery()
						.processDefinitionKeyLike(name).count();
				List<ProcessDefinition> pds = repositoryService
						.createProcessDefinitionQuery()
						.processDefinitionKeyLike(name)
						.orderByProcessDefinitionKey().desc()
						.orderByProcessDefinitionVersion().desc()
						.listPage((start - 1) * pageSize, pageSize);
				pageView.setRowCount(count);
				pageView.setRecords(pds);
			} else {
				long count = repositoryService.createProcessDefinitionQuery()
						.count();
				List<ProcessDefinition> pds = repositoryService
						.createProcessDefinitionQuery()
						.orderByProcessDefinitionKey().desc()
						.orderByProcessDefinitionVersion().desc()
						.listPage((start - 1) * pageSize, pageSize);
				pageView.setRowCount(count);
				pageView.setRecords(pds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

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
	public boolean deleteProcessDefinitionById(Map record) {
		String deploymentId = (String) record.get("id");
		try {
			repositoryService.deleteDeployment(deploymentId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void processDefinitionDeploy(HttpServletRequest request) {
		Map record = HttpUtils.getRequestMap(request);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String filename = (String) record.get("name");
		MultipartFile multipartFile = multipartRequest.getFile("file");
		String suffix = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf("."));
		InputStream is = null;
		if (suffix.equalsIgnoreCase(".zip")) {
			try {
				is = multipartFile.getInputStream();
				ZipInputStream zipInputStream = new ZipInputStream(is);
				repositoryService.createDeployment()// 创建部署对象
						.name(filename)// 添加部署名称
						.addZipInputStream(zipInputStream)//
						.deploy();// 完成部署
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (suffix.equalsIgnoreCase(".bpmn")) {

		}
	}

	@Override
	public void showView(HttpServletRequest request) {
		Map record = HttpUtils.getRequestMap(request);
		String filename = (String) record.get("name");
		String deploymentId="17501";
		List<String> names=repositoryService.getDeploymentResourceNames(deploymentId);
		String imageName="";
		for(String name:names){
			if(name.indexOf(".png")>=0){
				imageName=name;
			}
		}
		if(imageName!=null){
			File f=new File("e://"+imageName);
			InputStream in=processEngine.getRepositoryService()
					.getResourceAsStream(deploymentId, imageName);
			try {
				FileUtils.copyInputStreamToFile(in, f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	  
	
	
	
	
	
	
	
}
