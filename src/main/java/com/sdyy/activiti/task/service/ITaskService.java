/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.activiti.task.service;

import java.util.Map;

import com.sdyy.common.page.PageView;
import com.sdyy.common.service.IBaseService;


public interface ITaskService extends IBaseService {
	public PageView forTaskQueryPage(Map record, PageView pageView);
}
