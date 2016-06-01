/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_function.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.ac.ac_function.service.IAcFunctionService;
import com.sdyy.base.ac.ac_role_func.service.IAcRoleFuncService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcFunctionController
 * @Description: 资源
 * @author: liuyx 
 * @date: 2015年9月17日下午3:30:05
 */
@Controller
@RequestMapping("/acFunction")
public class AcFunctionController extends BaseController  {
	@Autowired
	private IAcFunctionService acFunctionService;
	@RequestMapping("/forCheckTree")
	public String forCheckTree(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		return "base/ac/ac_function/ac_function_forCheckTree";
	}
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		return acFunctionService.queryTreeNodes(paramMap);
	}
	
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String funcGroupId) {

		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = acFunctionService.get(id);
			
		}
		if(!StringUtils.isEmpty(funcGroupId)) {
			record.put("FUNC_GROUP_ID", funcGroupId);
		}
		request.setAttribute("record", record);
		return "base/ac/ac_function/ac_function_forUpdate";
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
			if(StringUtils.isEmpty(record.get("FUNC_ID"))){
				//新增
				
				//此处应有检测重复相关代码
				
				record.put("FUNC_ID", StringUtils.uniqueKey36());
				acFunctionService.insert(record);
			}else {
				//修改
				acFunctionService.update(record);
			}
			return new RetObj(true,request,record.get("FUNC_ID"));
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
	 * @date：2015年9月29日17:35:30
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
				status = acFunctionService.delete(id);
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
