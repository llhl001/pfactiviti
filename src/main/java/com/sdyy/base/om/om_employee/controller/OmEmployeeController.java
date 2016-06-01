/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.ac.ac_operator.service.IAcOperatorService;
import com.sdyy.base.om.om_employee.service.IOmEmployeeService;
import com.sdyy.common.RetObj;
import com.sdyy.common.consts.CommonConsts;
import com.sdyy.common.consts.GlobMessageKeys;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: OmEmployeeController
 * @Description: 人员
 * @author: liuyx 
 * @date: 2015年9月17日下午3:30:05
 */
@Controller
@RequestMapping("/omEmployee")
public class OmEmployeeController extends BaseController  {
	@Autowired
	private IOmEmployeeService omEmployeeService;
	@Autowired
	private IAcOperatorService acOperatorService;
	
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		return omEmployeeService.queryTreeNodes(paramMap);
	}
	/**
	 * 
	 * @Title: queryOrgEmpTreeNodes
	 * @author：liuyx 
	 * @date：2015年11月19日上午8:40:35
	 * @Description: 查询机构人员树（不包含岗位）供选择树
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryOrgEmpTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryOrgEmpTreeNodes(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		//任何人选人只能看到自己可以管理的机构下的人员
		paramMap.put("orgIdList",getAttributeFromEmpSession(request, "ORG_ID_LIST") );
		return omEmployeeService.queryOrgEmpTreeNodes(paramMap);
	}
	/**
	 * 
	 * @Title: forCheckTree
	 * @author：liuyx 
	 * @date：2015年9月28日上午11:12:53
	 * @Description: 跳转到选择树
	 * @param request
	 * @return
	 */
	@RequestMapping("/forCheckTree")
	public String forCheckTree(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		return "base/om/om_employee/om_employee_forCheckTree";
	}
	/**
	 * 
	 * @Title: forUpdate
	 * @author：liuyx 
	 * @date：2015年9月20日下午12:11:02
	 * @Description: 进入修改/新增页面
	 * @param request
	 * @param id 修改则必须
	 * @param orgId 新增则必须
	 * @param posiId 新增则必须
	 * @return
	 */
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String orgId,String posiId) {

		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = omEmployeeService.get(id);
			
			String operatorId =  StringUtils.getTrimStr(record.get("OPERATOR_ID"));
			Map oper = acOperatorService.get(operatorId);
			request.setAttribute("oper", oper);
			
		}else {
			//新增数据
			if(!StringUtils.isEmpty(posiId)) {
				record.put("POSITION", posiId);
			}
			if(!StringUtils.isEmpty(orgId)) {
				record.put("ORG_ID", orgId);
			}
		}
		request.setAttribute("record", record);
		return "base/om/om_employee/om_employee_forUpdate";
	}
	@RequestMapping("/forDetail")
	public String forDetail(HttpServletRequest request, String id,String orgId,String posiId) {

		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = omEmployeeService.get(id);
			
			String operatorId =  StringUtils.getTrimStr(record.get("OPERATOR_ID"));
			Map oper = acOperatorService.get(operatorId);
			request.setAttribute("oper", oper);
			
		}else {
			//新增数据
			if(!StringUtils.isEmpty(posiId)) {
				record.put("POSITION", posiId);
			}
			if(!StringUtils.isEmpty(orgId)) {
				record.put("ORG_ID", orgId);
			}
		}
		request.setAttribute("record", record);
		return "base/om/om_employee/om_employee_forDetail";
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
			if(StringUtils.isEmpty(record.get("EMP_ID"))){
/*				if(StringUtils.isEmpty(record.get("PASSWORD"))) {
					record.put("PASSWORD","111222");
				}*/
				//新增
				int ret = omEmployeeService.insert(record);
				if(ret==CommonConsts.DATA_REPEAT_ERR) {
					return new RetObj(false,GlobMessageKeys.OPER_USER_ID_REPEAT_ERR,request);
				}
			}else {
				//修改
				record.put("LAST_UPDATE_TIME", now);
				int ret = omEmployeeService.update(record);
				if(ret==CommonConsts.DATA_REPEAT_ERR) {
					return new RetObj(false,GlobMessageKeys.OPER_USER_ID_REPEAT_ERR,request);
				}
			}
			return new RetObj(true,request,record.get("EMP_ID"));
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
		
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request,String id,String pId) {
		try {
			//pId：暂只支持一人一岗，一人多岗需要判断是只删除关系还是完全删除,根据是否主岗。
			
			int ret = omEmployeeService.delete(id);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			
		}
		return new RetObj(false,request);
	}
}
