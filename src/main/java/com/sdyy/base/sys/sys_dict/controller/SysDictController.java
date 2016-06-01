/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.sys.sys_dict.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.sys.sys_dict.service.ISysDictService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

/** 
 * @ClassName: SysDictController.java
 * @Description: 
 * @author yuqing 
 * @version 2015年10月19日 下午2:44:06 
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictController extends BaseController{
	// 日志记录器
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ISysDictService dictService;
	
	@RequestMapping("/forQueryPage")
	public String forQueryPage(HttpServletRequest request,PageView pageView) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
		// 对应查询的mybatis的xml中的id必须以queryPage结尾，查询中加入select top 100
		// percent，结束时并按某个字段排序，否则不能进行分页
		try {
			pageView = dictService.queryPage(pageView, record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageView", pageView);
		request.setAttribute("record", record);
		return "base/sys/sys_dict/sys_dict_forQueryPage";
	}
	
	@RequestMapping("/rightQueryPage")
	public String rightQueryPage(HttpServletRequest request,String id,PageView pageView) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
//		record.put("TYPE_CODE",id);
		record.put("DIC_TYPE_ID",id);
		try {
			pageView = dictService.rightQueryPage(pageView, record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		record.put("TYPE_ID",id);
		request.setAttribute("pageView", pageView);
		request.setAttribute("record", record);
		return "base/sys/sys_dict/sys_dict_forRight";
	}
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String pageNow) {
		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = dictService.get(id);
		}
		request.setAttribute("record", record);
		request.setAttribute("pageNow", pageNow);
		return "base/sys/sys_dict/sys_dict_forUpdate";
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(HttpServletRequest request) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
		List<Map> list;
		try {
			if(StringUtils.isEmpty(record.get("DIC_TYPE_ID"))){
				//新增
				//此处应有检测重复相关代码
				list = dictService.query(record);
				if(!CollectionUtils.isEmpty(list)){
					return new RetObj(false,"字典编码已经存在！");
				}
				record.put("DIC_TYPE_ID", StringUtils.uniqueKey36());
				dictService.insert(record);
			}else {
				//修改
				record.put("NOT_ID", record.get("DIC_TYPE_ID"));
				list = dictService.query(record);
				if(!CollectionUtils.isEmpty(list)){
					return new RetObj(false,"字典编码已经存在！");
				}
				dictService.update(record);
			}
			return new RetObj(true,request,record.get("DIC_TYPE_ID"));
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/rightForUpdate")
	public String rightForUpdate(HttpServletRequest request, String id,String typeId,String pageNow) {
		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = dictService.rightGet(id);
		}
		record.put("TYPE_ID", typeId);
		record.put("DIC_ID", id);
		request.setAttribute("record", record);
		request.setAttribute("pageNow", pageNow);
		return "base/sys/sys_dict/sys_dict_right_forUpdate";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/rightUpdate")
	@ResponseBody
	public RetObj rightUpdate(HttpServletRequest request,String id) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
		List<Map> list;
		try {
			if(StringUtils.isEmpty(record.get("DIC_ID"))){
				//新增
				//此处应有检测重复相关代码
				list = dictService.rightQuery(record);
				if(!CollectionUtils.isEmpty(list)){
					return new RetObj(false,"字典值已经存在！");
				}
				record.put("DIC_ID", StringUtils.uniqueKey36());
				record.put("TYPE_ID", record.get("TYPE_ID"));
				dictService.rightInsert(record);
			}else {
				//修改
				record.put("NOT_ID", record.get("DIC_ID"));
				list = dictService.rightQuery(record);
				if(!CollectionUtils.isEmpty(list)){
					return new RetObj(false,"字典值已经存在！");
				}
				dictService.rightUpdate(record);
			}
			return new RetObj(true,request,record.get("DIC_ID"));
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	/**
	 * 
	 * @Title: 批量删除
	 * @author：yuqing
	 * @Description: 右侧表格批量删除数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/rightBatchDelete")
	@ResponseBody
	public RetObj rightBatchDelete(HttpServletRequest request,String ids) {
		try {
			dictService.rightBatchDelete(StringUtils.getStrJoinQuotes(ids));
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	/**
	 * 
	 * @Title: 批量删除
	 * @author：yuqing
	 * @Description: 字典类型批量删除数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public RetObj batchDelete(HttpServletRequest request,String ids) {
		try {
			dictService.batchDelete(StringUtils.getStrJoinQuotes(ids));
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	
	
	
}
