/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.activiti.processDefinition.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdyy.common.page.PageView;
import com.sdyy.common.service.IBaseService;


public interface IProcessDefinitionService extends IBaseService {
	
	public PageView forProcessDefinitionQueryPage(Map record, PageView pageView);
	public boolean deleteProcessDefinitionById(Map record);
	public void processDefinitionDeploy(HttpServletRequest request);
}
