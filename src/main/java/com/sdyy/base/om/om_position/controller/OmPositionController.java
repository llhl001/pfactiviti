/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_position.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.om.om_position.service.IOmPositionService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

/**
 * @ClassName: OmPositionController
 * @Description: 岗位
 * @author: liuyx 
 * @date: 2015年9月17日下午3:30:05
 */
@Controller
@RequestMapping("/omPosition")
public class OmPositionController extends BaseController  {
	@Autowired
	private IOmPositionService omPositionService;
	
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String orgId,String posiId,String level) {

		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = omPositionService.get(id);
			
		}
		if(!StringUtils.isEmpty(posiId)) {
			record.put("PARENT_POSI_ID", posiId);
		}
		if(!StringUtils.isEmpty(orgId)) {
			record.put("ORG_ID", orgId);
		}
		if(!StringUtils.isEmpty(level)) {
			record.put("POSI_LEVEL", level);
		}
		request.setAttribute("record", record);
		return "base/om/om_position/om_position_forUpdate";
	}
	/**
	 * 
	 * @Title: update
	 * @author：liuyx 
	 * @date：2015年9月15日下午3:15:30
	 * @Description: 新增/修改 数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(HttpServletRequest request) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
		String now = DateUtils.getCurrentDate();
		Map paramMap = new HashMap();
		paramMap.put("POSI_CODE", record.get("POSI_CODE"));
		try {
			if(StringUtils.isEmpty(record.get("POSITION_ID"))){
				//新增
				
				//此处应有检测重复相关代码
				
				List posiList = omPositionService.query(paramMap);
				if(!CollectionUtils.isEmpty(posiList)) {
					return new RetObj(false,"操作失败，岗位代码重复！");
				}
				record.put("POSITION_ID", StringUtils.uniqueKey36());
				record.put("CREATE_TIME", now);
				omPositionService.insert(record);
			}else {
				//修改
				paramMap.put("NOT_POSITION_ID", record.get("POSITION_ID"));
				List posiList = omPositionService.query(paramMap);
				if(!CollectionUtils.isEmpty(posiList)) {
					return new RetObj(false,"操作失败，岗位代码重复！");
				}
				record.put("LAST_UPDATE_TIME", now);
				record.put("UPDATOR", getEmpIdFromRequest(request));
				omPositionService.update(record);
			}
			return new RetObj(true,request,record.get("POSITION_ID"));
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
		
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request,String id) {
		try {			
			int ret = omPositionService.delete(id);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			
		}
		return new RetObj(false,request);
	}
}
