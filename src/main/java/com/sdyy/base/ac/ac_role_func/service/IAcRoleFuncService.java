/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_role_func.service;

import java.util.List;
import java.util.Map;

import com.sdyy.common.service.IBaseService;

/**
 * @ClassName: IAcRoleFuncService
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午3:39:36
 */
public interface IAcRoleFuncService extends IBaseService {

	public int batchInsert(List<Map> list,Map deleteParam);
}
