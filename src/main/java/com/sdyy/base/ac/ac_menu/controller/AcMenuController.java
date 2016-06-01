/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.ac.ac_menu.service.IAcMenuService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcMenuController
 * @Description: 菜单
 * @author: liuyx 
 * @date: 2015年9月2日上午9:07:10
 */
@Controller
@RequestMapping("/acMenu")
public class AcMenuController extends BaseController {
	
	@Autowired
	private IAcMenuService acMenuService;
	
	@RequestMapping("/forMain")
	public String forMain() {
		return "base/ac/ac_menu/ac_menu_forMain";
	}
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(String pId) {
		return acMenuService.queryChildrenTreeNodes(pId);
	}

	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id,String pId,String level,String appId) {
		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = acMenuService.get(id);
			
		}
		if(!StringUtils.isEmpty(pId)) {
			record.put("PARENT_ID", pId);
		}
		if(!StringUtils.isEmpty(level)) {
			record.put("MENU_LEVEL", level);
		}
		if(!StringUtils.isEmpty(appId)) {
			record.put("APP_ID", appId);
		}
		request.setAttribute("record", record);
		return "base/ac/ac_menu/ac_menu_forUpdate";
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
		List<Map> list;
		try {
			if(StringUtils.isEmpty(record.get("MENU_ID"))){
				//新增
				
				//检测重复相关代码
				list = acMenuService.query(record);
				if(!CollectionUtils.isEmpty(list)){
					return new RetObj(false,"菜单代码已经存在！");
				}
				record.put("MENU_ID", StringUtils.uniqueKey36());
				record.put("CREATE_TIME", now);
				acMenuService.insert(record);
			}else {
				//修改
				record.put("NOT_MENU_ID", record.get("MENU_ID"));
				list = acMenuService.query(record);
				if(!CollectionUtils.isEmpty(list)){
					return new RetObj(false,"菜单代码已经存在！");
				}
				record.put("LAST_UPDATE_TIME", now);
				record.put("UPDATOR", getEmpIdFromRequest(request));
				acMenuService.update(record);
			}
			return new RetObj(true,request,record.get("MENU_ID"));
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
	 * @date：2015年9月29日17:36:30
	 * @Description: 删除菜单
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request,String id,HttpServletResponse response) {
		try {
			int status=0;
			if(!StringUtils.isEmpty(id)) {
				status = acMenuService.delete(id);
			}
			StringBuffer jsonStr = new StringBuffer("{\"status\":\"" + status
					+ "\"}");
//			System.out.println("************************************************************"+jsonStr);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonStr.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
