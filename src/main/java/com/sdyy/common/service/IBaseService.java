/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.common.service;

import java.util.List;
import java.util.Map;

import com.sdyy.common.page.PageView;

/**
 * @ClassName: IBaseService
 * @Description: 共通Service接口，跨项目调用必须继承该接口
 * @author: liuyx 
 * @date: 2015年9月5日下午3:47:16
 */
public interface IBaseService {
	/**
	 * 
	 * @Title: getMapperSpaceName
	 * @author：liuyx 
	 * @date：2015年9月17日下午4:19:19
	 * @Description: 返还当前service中的主space名
	 * @return
	 */
	/*public String getMapperSpaceName();*/
	/**
	 * 
	 * @Title: get
	 * @author：liuyx 
	 * @date：2015年9月2日下午4:23:59
	 * @Description: 获取一条数据
	 * @param id
	 * @return
	 */
    public Map get(String id);

    /**
     * 
     * @Title: getPageAcOperator
     * @author：liuyx 
     * @date：2015年9月2日下午4:21:21
     * @Description: 根据条件获取一页数据
     * @param paramMap
     * @return
     */
    public PageView queryPage(PageView pageView,Map paramMap);

    /**
     * 
     * @Title: query
     * @author：liuyx 
     * @date：2015年9月2日下午4:21:21
     * @Description: 根据条件获取数据列表
     * @param paramMap
     * @return
     */
    public List<Map> query(Map paramMap);
    
	/**
	 *    
	 * @Title: insert
	 * @author：liuyx 
	 * @date：2015年9月2日下午4:25:00
	 * @Description: 插入一条数据
	 * @param record
	 * @return
	 */
    public int insert(Map record);

    /**
     * 
     * @Title: update
     * @author：liuyx 
     * @date：2015年9月2日下午4:25:33
     * @Description: 修改一条数据
     * @param record
     * @return
     */
    public int update(Map record);
    
    /**
     * 
     * @Title: delete
     * @author：liuyx 
     * @date：2015年9月2日下午4:25:52
     * @Description: 根据主键删除一条数据
     * @param id
     */
    public int delete(String id);
}
