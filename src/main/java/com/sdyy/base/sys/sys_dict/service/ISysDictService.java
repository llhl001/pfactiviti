/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.sys.sys_dict.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.sdyy.common.page.PageView;
import com.sdyy.common.service.IBaseService;

/** 
 * @ClassName: ISysDictService.java
 * @Description: 
 * @author yuqing 
 * @version 2015年10月19日 下午2:46:04 
 */

public interface ISysDictService extends IBaseService{
	/**
	 * 字典项列表
	 * @author: 于庆
	 */
	@SuppressWarnings("rawtypes")
	public PageView rightQueryPage(PageView pageView,Map paramMap);
	/**
	 * 字典项验证字段重复
	 * @author: 于庆
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> rightQuery(Map paramMap);
	/**
	 * 字典项验证字段重复
	 * @author: liuyx
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryDictItemsByTypeCode(String typeCode);
	/**
	 * 字典项插入记录
	 * @author: 于庆
	 */
	@SuppressWarnings("rawtypes")
	public int rightInsert(Map record);
	/**
	 * 字典项修改记录
	 * @author: 于庆
	 */
	@SuppressWarnings("rawtypes")
	public int rightUpdate(Map record);
	
	/**
	 * 字典项获取 一条记录
	 * @author: 于庆
	 */
	@SuppressWarnings("rawtypes")
	public Map rightGet(String id);
	
	/**
	 * 字典项 批量删除
	 * @author: 于庆
	 */
	public int rightBatchDelete(String ids);
	
	/**
	 * 字典类型 批量删除
	 * @author: 于庆
	 */
	public int batchDelete(String ids);
}
