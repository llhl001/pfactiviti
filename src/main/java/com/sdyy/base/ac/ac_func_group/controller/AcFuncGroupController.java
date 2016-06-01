/**
k * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_func_group.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.ac.ac_func_group.service.IAcFuncGroupService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

/**
 * @ClassName: AcFuncGroupController
 * @Description: 功能组
 * @author: liuyx 
 * @date: 2015年9月17日下午3:30:05
 */
@Controller
@RequestMapping("/acFuncGroup")
public class AcFuncGroupController extends BaseController  {
	@Autowired
	private IAcFuncGroupService acFuncGroupService;
	
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String funcGroupId,String appId,String level) {

		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = acFuncGroupService.get(id);
			
		}
		if(!StringUtils.isEmpty(funcGroupId)) {
			record.put("PARENT_GROUP", funcGroupId);
		}
		if(!StringUtils.isEmpty(appId)) {
			record.put("APP_ID", appId);
		}
		if(!StringUtils.isEmpty(level)) {
			record.put("GROUP_LEVEL", level);
		}
		request.setAttribute("record", record);
		return "base/ac/ac_func_group/ac_func_group_forUpdate";
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
		try {
			if(StringUtils.isEmpty(record.get("FUNC_GROUP_ID"))){
				//新增
				
				//此处应有检测重复相关代码
				
				record.put("FUNC_GROUP_ID", StringUtils.uniqueKey36());
				record.put("CREATE_TIME", now);
				acFuncGroupService.insert(record);
			}else {
				//修改
				record.put("LAST_UPDATE_TIME", now);
				record.put("UPDATOR", getEmpIdFromRequest(request));
				acFuncGroupService.update(record);
			}
			return new RetObj(true,request,record.get("FUNC_GROUP_ID"));
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	/**
	 * 
	 * @Title: delete
	 * @author: yuqing
	 * @date：2015年9月29日17:25:30
	 * @Description: 删除功能组
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request,String id,HttpServletResponse response) {
		try {
			int status=0;
			if(!StringUtils.isEmpty(id)) {
				status = acFuncGroupService.delete(id);
			}
			StringBuffer jsonStr = new StringBuffer("{\"status\":\"" + status
					+ "\"}");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonStr.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
