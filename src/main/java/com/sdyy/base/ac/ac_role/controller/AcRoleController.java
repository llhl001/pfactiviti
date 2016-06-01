/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdyy.common.page.PageView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.ac.ac_application.service.IAcApplicationService;
import com.sdyy.base.ac.ac_role.service.IAcRoleService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;

/**
 * @ClassName: AcRoleController
 * @Description: 角色
 * @author: liuyx 
 * @date: 2015年9月17日下午3:30:05
 */
@Controller
@RequestMapping("/acRole")
public class AcRoleController extends BaseController  {
	@Autowired
	private IAcRoleService acRoleService;
	@Autowired
	private IAcApplicationService acApplicationService;
	@RequestMapping("/forQueryPage")
	public String forQueryPage(HttpServletRequest request,PageView pageView) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
		// 对应查询的mybatis的xml中的id必须以queryPage结尾，查询中加入select top 100
		// percent，结束时并按某个字段排序，否则不能进行分页
		try {
			pageView = acRoleService.queryPage(pageView, record);
/*			List<XtDict> dictList = xtDictService.querySysDictByDictcode("JSLX","","","1");
			model.addAttribute("dictList", dictList);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*model.addAttribute("acRole", acRole);*/
		request.setAttribute("pageView", pageView);
		request.setAttribute("record", record);
		return "base/ac/ac_role/ac_role_forQueryPage";
	}
/*	@RequestMapping("/forQueryPageContent")
	@ResponseBody
	public PageView forQueryPageContent(HttpServletRequest request,PageView pageView) {
		
		return pageView;
	}*/
	
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String pageNow) {

		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = acRoleService.get(id);
			
		}
		Map paramMap = new HashMap<>();
		paramMap.put("NOT_ID", "root");
		List<Map> appList = acApplicationService.query(paramMap);
		request.setAttribute("appList", appList);
		request.setAttribute("record", record);
		request.setAttribute("pageNow", pageNow);
		return "base/ac/ac_role/ac_role_forUpdate";
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
			if(StringUtils.isEmpty(record.get("ROLE_ID"))){
				//新增
				
				//此处应有检测重复相关代码
				
				record.put("ROLE_ID", StringUtils.uniqueKey36());
				record.put("CREATE_TIME", now);
				acRoleService.insert(record);
			}else {
				//修改
				record.put("LAST_UPDATE_TIME", now);
				record.put("UPDATOR", getEmpIdFromRequest(request));
				acRoleService.update(record);
			}
			return new RetObj(true,request,record.get("ROLE_ID"));
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
	 * @author：liuyx 
	 * @date：2015年9月15日下午3:15:30
	 * @Description: 批量删除数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public RetObj batchDelete(HttpServletRequest request,String ids) {
		
		try {
			acRoleService.batchDelete(StringUtils.getStrJoinQuotes(ids));
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
}
