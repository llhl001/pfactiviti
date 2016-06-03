/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.activiti.pi.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.sdyy.common.page.PageView;
import com.sdyy.common.service.IBaseService;


public interface IProcessInstanceService extends IBaseService {
	public PageView forProcessDefinitionQueryPage(Map record, PageView pageView);
	public List<ProcessInstance> queryProcessInstance();
	public boolean startProcessInstance(Map record);
}
