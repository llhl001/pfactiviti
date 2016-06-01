/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.om.om_group.service.IOmGroupService;
import com.sdyy.base.om.om_organization.service.IOmOrganizationService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;
/**
 * @ClassName: OcGroupController
 * @Description: 工作组
 * @author: 于庆
 * @date: 2015年9月24日下午6:07:10
 */
@Controller
@RequestMapping("/omGroup")
public class OmGroupController extends BaseController{

	@Autowired
	private IOmGroupService omGroupService;
	@Autowired
	private IOmOrganizationService omOrganizationService;
	@RequestMapping("/forMain")
	public String forMain() {
		return "base/om/om_group/om_group_forMain";
	}
	
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(String pId) {
		return omGroupService.queryChildrenTreeNodes(pId);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String pId,String level) {
		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = omGroupService.get(id);
		}
		List orgList = omOrganizationService.query(null);
		request.setAttribute("orgList", orgList);
		if(!StringUtils.isEmpty(pId)) {
			record.put("PARENT_GROUP_ID", pId);
		}
		if(!StringUtils.isEmpty(level)) {
			record.put("GROUP_LEVEL", level);
		}
		request.setAttribute("record", record);
		return "base/om/om_group/om_group_forUpdate";
	}
	
	/**
	 * 
	 * @Title: update
	 * @author: yuqing
	 * @date：2015年9月25日下午3:15:30
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
			if(StringUtils.isEmpty(record.get("GROUP_ID"))){
				
				//此处应有检测重复相关代码	
				record.put("GROUP_ID", StringUtils.uniqueKey36());
				record.put("CREATE_TIME", now);
				omGroupService.insert(record);
			}else {
				//修改
				record.put("LAST_UPDATE", now);
				record.put("UPDATOR", getEmpIdFromRequest(request));
				omGroupService.update(record);
			}
			return new RetObj(true,request,record.get("GROUP_ID"));
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
	 * @date：2015年9月28日上午午10:15:30
	 * @Description: 删除工作组
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request,String id,HttpServletResponse response) {
		try {
			int status=0;
			if(!StringUtils.isEmpty(id)) {
				status = omGroupService.delete(id);
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
	
	@RequestMapping("/query")
	@ResponseBody
	public RetObj query(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		List<Map> list;
		if(StringUtils.isEmpty(paramMap.get("GROUP_ID"))){
			list = omGroupService.query(paramMap);
		}else {
			list = omGroupService.queryUpdate(paramMap);
		}
		int count = list.size();
		if(count>0){
			return new RetObj(true,"机构代码已经存在！");
		}else{
			return new RetObj(false,"");
		}
	}
}
