/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_operator_role.controller;

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
import com.sdyy.base.ac.ac_operator_role.service.IAcOperatorRoleService;
import com.sdyy.base.ac.ac_role.service.IAcRoleService;
import com.sdyy.base.om.om_employee.service.IOmEmployeeService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

/**
 * @ClassName: AcOperatorRoleController
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月27日上午10:53:55
 */
@Controller
@RequestMapping("/acOperatorRole")
public class AcOperatorRoleController extends BaseController {

	@Autowired
	private IAcOperatorRoleService acOperatorRoleService;
	@Autowired
	private IOmEmployeeService omEmployeeService;
	@Autowired
	private IAcRoleService acRoleService;
	
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request,String empId,String operatorId) {
		if(StringUtils.isEmpty(operatorId)) {
			Map emp = omEmployeeService.get(empId);
			operatorId = emp.get("OPERATOR_ID").toString();
		}
		//获取当前应用的所有角色
		Map paramMap = new HashMap();
		//取出所有角色
		List<Map> roleList = acRoleService.query(paramMap);
		paramMap.put("OPERATOR_ID", operatorId);
		//取出已经授权的角色
		List<Map> operatorRoleList = acOperatorRoleService.query(paramMap);
		
		//删除所有已授权的角色
		for(Map operatorRole:operatorRoleList) {
			for(Map role:roleList) {
				if(role.get("ROLE_ID").equals(operatorRole.get("ROLE_ID"))) {
					roleList.remove(role);
					break;
				}
			}
		}
		request.setAttribute("roleList", roleList);
		request.setAttribute("operatorRoleList", operatorRoleList);
		request.setAttribute("operatorId", operatorId);
		return "base/ac/ac_operator_role/ac_operator_role_forUpdate";
	}
	
	@RequestMapping("/queryOperatorRole")
	@ResponseBody
	public List queryOperatorRole(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		List<Map> list = acOperatorRoleService.query(paramMap);
		return list;
	}
	
	@RequestMapping("/batchInsertByJson")
	@ResponseBody
	public RetObj batchInsertByJson(HttpServletRequest request,String dataJsonStr,String roleId,String notInOperatorIds) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		/*String ROLE_ID = dataList.get(0).get("ROLE_ID").toString();*/
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("ROLE_ID", roleId);
		paramMap.put("notInOperatorIds", notInOperatorIds);
		try {
			acOperatorRoleService.batchInsert(dataList,paramMap);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	
	@RequestMapping("/batchInsertByArray")
	@ResponseBody
	public RetObj batchInsertByArray(HttpServletRequest request, String[] selectright,String operatorId) {
		

		
		List<Map> dataList = new ArrayList();
		if(selectright!=null&&selectright.length>0) {
			for (int i=0;i<selectright.length;i++) {
				Map operRole = new HashMap();
				operRole.put("OPERATOR_ID", operatorId);
				operRole.put("ROLE_ID", selectright[i]);
				dataList.add(operRole);
			}
		}

		
		try {
			Map paramMap = new HashMap();
			paramMap.put("OPERATOR_ID", operatorId);
			acOperatorRoleService.batchInsert(dataList,paramMap);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
}
