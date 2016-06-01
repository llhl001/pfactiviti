/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.sys.sys_file.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.sdyy.common.page.PageView;
import com.sdyy.common.service.IBaseService;

/** 
 * @ClassName: ISysFileService.java
 * @Description: 
 * @author yuqing 
 * @version 2015年10月19日 下午2:46:04 
 */

public interface ISysFileService extends IBaseService{
	int batchInsert(List list);
}
