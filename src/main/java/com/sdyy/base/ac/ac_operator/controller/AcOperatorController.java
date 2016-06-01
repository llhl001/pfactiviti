/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_operator.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdyy.base.ac.ac_operator.service.IAcOperatorService;
import com.sdyy.common.RetObj;
import com.sdyy.common.controller.BaseController;
import com.sdyy.common.utils.HttpUtils;
import com.sdyy.common.utils.Md5Util;

/**
 * @ClassName: AcOperatorController
 * @Description: 角色
 * @author: liuyx 
 * @date: 2015年9月17日下午3:30:05
 */
@Controller
@RequestMapping("/acOperator")
public class AcOperatorController extends BaseController  {
	@Autowired
	private IAcOperatorService acOperatorService;
	
	@RequestMapping("/forUpdatePassword")
	public String forUpdatePassword(HttpServletRequest request) {
		return "base/ac/ac_operator/ac_operator_forUpdatePassword";
	}
	
	/**
	 * 
	 * @Title: updatePassword
	 * @author：liuyx 
	 * @date：2015年9月15日下午3:15:30
	 * @Description: 修改密码
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public RetObj updatePassword(HttpServletRequest request) {
		try {
			String operatorid = null;
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String psw = Md5Util.string2MD5(oldPassword);
			String newpassword_md5 = Md5Util.string2MD5(newPassword);
	        String m="";
				@SuppressWarnings("rawtypes")
				Map operatorMap=(Map)request.getSession().getAttribute(LOGIN_IN_OPERATOR_SESSION); 
				if(operatorMap!=null){
					String operatorId = (String)operatorMap.get("OPERATOR_ID");
					Map acOperator = acOperatorService.get(operatorId);
					if(!acOperator.get("PASSWORD").equals(psw)){
						return new RetObj(false,"修改失败，旧密码不正确!");
					}else{
						acOperator.put("OPERATOR_ID", operatorId);
						acOperator.put("PASSWORD", newpassword_md5);
						acOperatorService.update(acOperator);
					}
				} else {
					return new RetObj(false,"修改失败，该用户不存在！");
				}
				return new RetObj(true,request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
			
			return new RetObj(false,request);
		}
		
	}

}
