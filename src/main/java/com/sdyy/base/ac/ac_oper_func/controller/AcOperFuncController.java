/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_oper_func.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sdyy.base.ac.ac_oper_func.service.IAcOperFuncService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.HttpUtils;

/**
 * @ClassName: AcOperFuncController
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月27日上午10:53:55
 */
@Controller
@RequestMapping("/acOperFunc")
public class AcOperFuncController extends BaseController {

	@Autowired
	private IAcOperFuncService acOperFuncService;
	@RequestMapping("/queryOperFunc")
	@ResponseBody
	public List forCheckTreeForOper(HttpServletRequest request) {
		Map<String,Object> paramMap = HttpUtils.getRequestMap(request);
		request.setAttribute("record", paramMap);
		List<Map> list = acOperFuncService.query(paramMap);
		return list;
	}
	
	@RequestMapping("/batchInsert")
	@ResponseBody
	public RetObj batchInsert(HttpServletRequest request,String operFuncJsonStr,String operatorId) {
		List<Map> dataList = JSON.parseArray(operFuncJsonStr, Map.class);
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("OPERATOR_ID", operatorId);
		try {
			acOperFuncService.batchInsert(dataList,paramMap);
			return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			return new RetObj(false,request);
		}
		
		
	}
}
