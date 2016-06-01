package com.sdyy.base.ac.ac_operator.service;

import java.util.Map;

import com.sdyy.common.service.IBaseService;

public interface IAcOperatorService extends IBaseService {
	public Map checkLogin(String userId, String passWord);
}
