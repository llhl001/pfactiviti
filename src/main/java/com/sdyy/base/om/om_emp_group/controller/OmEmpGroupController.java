/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_emp_group.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sdyy.base.om.om_emp_group.service.IOmEmpGroupService;
import com.sdyy.base.om.om_employee.service.IOmEmployeeService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.HttpUtils;

/**
 * @ClassName: OmEmpGroupController
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月27日上午10:53:55
 */
@Controller
@RequestMapping("/omEmpGroup")
public class OmEmpGroupController extends BaseController {

	@Autowired
	private IOmEmpGroupService omEmpGroupService;
	@Autowired
	private IOmEmployeeService omEmployeeService;
	
	@RequestMapping("/queryEmpGroup")
	@ResponseBody
	public List queryEmpGroup(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		List<Map> list = omEmpGroupService.query(paramMap);
		return list;
	}
	
	@RequestMapping("/batchInsertByJson")
	@ResponseBody
	public RetObj batchInsertByJson(HttpServletRequest request,String dataJsonStr,String groupId,String notInEmpIds) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("GROUP_ID", groupId);
		paramMap.put("notInEmpIds", notInEmpIds);
		try {
			omEmpGroupService.batchInsert(dataList,paramMap);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		try {
			int ret  = omEmpGroupService.delete(paramMap);
			if(ret>0) {
				return new RetObj(true,request);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
		}
		return new RetObj(false,request);
	}
}
