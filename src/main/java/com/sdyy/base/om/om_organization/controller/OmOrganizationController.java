/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_organization.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.om.om_organization.service.IOmOrganizationService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: OmOrganizationController
 * @Description: 机构
 * @author: liuyx 
 * @date: 2015年9月2日上午9:07:10
 */
@Controller
@RequestMapping("/omOrganization")
public class OmOrganizationController extends BaseController {
	
	@Autowired
	private IOmOrganizationService omOrganizationService;
	
	@RequestMapping("/forMain")
	public String forMain() {
		return "base/om/om_organization/om_organization_forMain";
	}
/*	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(String pId) {
		Map paraMap = new HashMap();
		paraMap.put("pId", pId);
		return omOrganizationService.queryChildrenTreeNodes(paraMap);
	}*/
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String pId,String level) {
		/*OmOrganization org = new OmOrganization();
		OmOrganization porg = new OmOrganization();
		org.setOrgid(id);
		porg.setOrgid(pid);
		try {
			List<OmEmployee>emps=	omEmployeeService.query(org);
			model.addAttribute("emps", emps);
			List<OmEmployee>pemps=	omEmployeeService.query(porg);
			model.addAttribute("pemps", pemps);
			org = (OmOrganization) omOrganizationService.get(org);
			model.addAttribute("t", org);
			model.addAttribute("parentname", parentname);
			List<XtDict> dictList = xtDictService.querySysDictByDictcode("JGZT","","","1");
			model.addAttribute("dictList", dictList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("pid", id);*/
		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = omOrganizationService.get(id);
			
		}
		if(!StringUtils.isEmpty(pId)) {
			record.put("PARENT_ORG_ID", pId);
		}
		if(!StringUtils.isEmpty(level)) {
			record.put("ORG_LEVEL", level);
		}
		request.setAttribute("record", record);
		return "base/om/om_organization/om_organization_forUpdate";
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
			if(StringUtils.isEmpty(record.get("ORG_ID"))){
				//新增
				
				//此处应有检测重复相关代码
				Map paramMap = new HashMap();
				paramMap.put("ORG_CODE", record.get("ORG_CODE"));
				List orgList = omOrganizationService.query(paramMap);
				if(!CollectionUtils.isEmpty(orgList)) {
					return new RetObj(false,"操作失败，机构代码重复！");
				}
				record.put("ORG_ID", StringUtils.uniqueKey36());
				record.put("CREATE_TIME", now);
				omOrganizationService.insert(record);
			}else {
				Map paramMap = new HashMap();
				paramMap.put("ORG_CODE", record.get("ORG_CODE"));
				paramMap.put("NOT_ORG_ID", record.get("ORG_ID"));
				List orgList = omOrganizationService.query(paramMap);
				if(!CollectionUtils.isEmpty(orgList)) {
					return new RetObj(false,"操作失败，机构代码重复！");
				}
				//修改
				record.put("LAST_UPDATE_TIME", now);
				record.put("UPDATOR", getEmpIdFromRequest(request));
				omOrganizationService.update(record);
			}
			return new RetObj(true,request,record.get("ORG_ID"));
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
			int ret = omOrganizationService.delete(id);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			
		}
		return new RetObj(false,request);
	}
	
	/**
	 * 
	 * @Title: forCheckTree
	 * @author：liuyx 
	 * @date：2015年11月18日15:51:19
	 * @Description: 跳转到选择树
	 * @param request
	 * @return
	 */
	@RequestMapping("/forCheckTree")
	public String forCheckTree(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		return "base/om/om_organization/om_organization_forCheckTree";
	}
	
	/**
	 * 
	 * @Title: queryTreeNodes
	 * @author：liuyx
	 * @date：2015年12月24日下午3:34:19
	 * @Description: 获取机构岗位人员节点
	 * @param request
	 * 		有init则查出所有可管理的节点
	 * 		有pId则只查出pId下的所有节点
	 * 		init和pId不可同时存在
	 * @return
	 */
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
/*		if(paramMap.get("pId")==null) {
			paramMap.put("pId", getAttributeFromEmpSession(request, "ORG_ID"));
		}*/

		if(paramMap.get("init")!=null) {
			paramMap.put("orgIdList",getAttributeFromEmpSession(request, "ORG_ID_LIST") );
		}
		return omOrganizationService.queryChildrenTreeNodes(paramMap);
	}
	
	/**
	 * 
	 * @Title: adjust
	 * @author：liuyx
	 * @date：2015年12月29日上午10:49:14
	 * @Description: 组织机构结构调整
	 * @param request
	 * @param id
	 * @param type
	 * @param fromId 原父节点id
	 * @param fromType 原父节点类型
	 * @param toId 目标父节点id
	 * @param toType 目标父节点类型
	 * @return
	 */
	@RequestMapping("/adjustByTree")
	@ResponseBody
	public RetObj adjustByTree(HttpServletRequest request,String id,String type,String fromId,
			String fromType,String toId,String toType,String isCopy) {
		try {
			
			omOrganizationService.adjust(id,type,fromId,fromType,toId,toType,isCopy);
			
			return new RetObj(true);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false);
		}
		
		
	}
}
