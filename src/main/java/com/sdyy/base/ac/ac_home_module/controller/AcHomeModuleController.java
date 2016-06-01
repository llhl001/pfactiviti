/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_home_module.controller;

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

import com.alibaba.fastjson.JSON;
import com.sdyy.base.ac.ac_home_module.service.IAcHomeModuleService;
import com.sdyy.base.ac.ac_role_func.service.IAcRoleFuncService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcHomeModuleController
 * @Description: 首页模块管理
 * @author: liuyx 
 * @date: 2015年12月2日09:52:05
 */
@Controller
@RequestMapping("/acHomeModule")
public class AcHomeModuleController extends BaseController  {
	@Autowired
	private IAcHomeModuleService acHomeModuleService;
	
	@RequestMapping("/forQueryPage")
	public String forQueryPage(HttpServletRequest request,PageView pageView) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
		// 对应查询的mybatis的xml中的id必须以queryPage结尾，查询中加入select top 100
		// percent，结束时并按某个字段排序，否则不能进行分页
		try {
			pageView = acHomeModuleService.queryPage(pageView, record);
/*			List<XtDict> dictList = xtDictService.querySysDictByDictcode("JSLX","","","1");
			model.addAttribute("dictList", dictList);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*model.addAttribute("acRole", acRole);*/
		request.setAttribute("pageView", pageView);
		request.setAttribute("record", record);
		return "base/ac/ac_home_module/ac_home_module_forQueryPage";
	}
	
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String appId) {

		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = acHomeModuleService.get(id);
			
		}
		if(!StringUtils.isEmpty(appId)) {
			record.put("APP_ID", appId);
		}
		request.setAttribute("record", record);
		return "base/ac/ac_home_module/ac_home_module_forUpdate";
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
			if(StringUtils.isEmpty(record.get("HOME_MODULE_ID"))){
				//新增
				
				//此处应有检测重复相关代码
				
				record.put("HOME_MODULE_ID", StringUtils.uniqueKey36());
				acHomeModuleService.insert(record);
			}else {
				//修改
				acHomeModuleService.update(record);
			}
			return new RetObj(true,request,record.get("HOME_MODULE_ID"));
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
	 * @author：liuyx 
	 * @date：2015年12月2日上午9:53:33
	 * @Description: TODO
	 * @param request
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request,String id) {
		try {			
			int ret = acHomeModuleService.delete(id);
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
			acHomeModuleService.batchDelete(StringUtils.getStrJoinQuotes(ids));
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	
	
	@RequestMapping("/forCheckTree")
	public String forCheckTree(HttpServletRequest request,String ROLE_ID,String APP_ID) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		return "base/ac/ac_home_module/ac_home_module_forCheckTree";
	}
	
	@RequestMapping("/queryNodesForRole")
	@ResponseBody
	public List<ZTreeNode> queryNodesForRole(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		return acHomeModuleService.queryNodesForRole(paramMap);
	}
	
	
	@RequestMapping("/batchInsertRoleHomeModule")
	@ResponseBody
	public RetObj batchInsertRoleHomeModule(HttpServletRequest request,String dataJsonStr,String roleId) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("ROLE_ID", roleId);
		try {
			acHomeModuleService.batchInsertRoleHomeModule(dataList,paramMap);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
	
	@RequestMapping("/batchInsertOperHomeModule")
	@ResponseBody
	public RetObj batchInsertOperHomeModule(HttpServletRequest request,String dataJsonStr,String operatorId) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("OPERATOR_ID", operatorId);
		try {
			acHomeModuleService.batchInsertOperHomeModule(dataList,paramMap);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
	}
}
