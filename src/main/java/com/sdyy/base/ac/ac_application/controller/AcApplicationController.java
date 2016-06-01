/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_application.controller;

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

import com.sdyy.base.ac.ac_application.service.IAcApplicationService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcApplicationController
 * @Description: 应用
 * @author: liuyx 
 * @date: 2015年9月2日上午9:07:10
 */
@Controller
@RequestMapping("/acApplication")
public class AcApplicationController extends BaseController {
	
	@Autowired
	private IAcApplicationService acApplicationService;
	
	@RequestMapping("/forMain")
	public String forMain() {
		return "base/ac/ac_application/ac_application_forMain";
	}
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(String pId) {
		return acApplicationService.queryChildrenTreeNodes(pId);
	}
	@RequestMapping("/forUpdate")
	public String forUpdate(HttpServletRequest request, String id) {
		Map record = new HashMap();
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = acApplicationService.get(id);
			
		}
		request.setAttribute("record", record);
		return "base/ac/ac_application/ac_application_forUpdate";
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
	@SuppressWarnings("rawtypes")
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(HttpServletRequest request) {
		Map<String,Object> record = HttpUtils.getRequestMap(request);
		String now = DateUtils.getCurrentDate();
		List<Map> list;
		int count;
		try {
			if(StringUtils.isEmpty(record.get("APP_ID"))){
				//新增
				//检测重复相关代码
				list = acApplicationService.query(record);
				if(!CollectionUtils.isEmpty(list)){
					return new RetObj(false,"应用代码已经存在！");
				}
				record.put("APP_ID", StringUtils.uniqueKey36());
				record.put("CREATE_TIME", now);
				acApplicationService.insert(record);
			}else {
				//修改
				record.put("NOT_ID", record.get("APP_ID"));
				list = acApplicationService.query(record);
				if(!CollectionUtils.isEmpty(list)) {
					return new RetObj(false,"应用代码已经存在！");
				}
				record.put("LAST_UPDATE_TIME", now);
				record.put("UPDATOR", getEmpIdFromRequest(request));
				acApplicationService.update(record);
			}
			return new RetObj(true,request,record.get("APP_ID"));
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
	 * @date：2015年9月29日17:15:30
	 * @Description: 删除应用
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request,String id,HttpServletResponse response) {
		try {
			int status=0;
			if(!StringUtils.isEmpty(id)) {
				status = acApplicationService.delete(id);
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
