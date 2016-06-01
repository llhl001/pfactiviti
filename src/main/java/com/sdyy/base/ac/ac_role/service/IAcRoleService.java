/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.ac.ac_role.service;

import com.sdyy.common.service.IBaseService;

/**
 * @ClassName: IAcRoleService
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午3:39:36
 */
public interface IAcRoleService extends IBaseService {
	public int batchDelete(String ids);
}
