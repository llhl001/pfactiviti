package com.sdyy.base.ac.ac_menu.service;


/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: AcMenuServiceImpl
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class AcMenuServiceImpl implements IAcMenuService {

	@Autowired
	private IBaseDao dao;
	
	@Override
	public Map get(String id) {
		// TODO Auto-generated method stub
		//return (Map) dao.get("acMenuSpace.get", operator_id);
		return (Map) dao.get("acMenuSpace.get", id);  
	}
	/**
     * 查询	一页记录
     * @param rowSelection
     * @param map
     * @return
     */
	@Override
    public PageView queryPage(PageView pageView,Map paramMap){
    	return null;//分页待完善
    }


	/**
	 * 插入		一条记录
	 * @param beanMap
	 * @return
	 */    
    @Override
    public int insert(Map record){
    	return dao.insert_("acMenuSpace.insert", record);
    }

    /**
     * 修改		一条记录
     * @param beanMap
     * @return
     */
    @Override
    public int update(Map record){
    	return dao.update_("acMenuSpace.update", record);
    }
    
    /**
	 * 删除		一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("acMenuSpace.delete", id);
    }
	/**
	 * @Title: query
	 * @author：liuyx 
	 * @date：2015年9月5日下午3:51:13
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Map> query(Map paramMap) {
		List list = (List) dao.query("acMenuSpace.query", paramMap);
		return list;
	}
	
	/**
	 * @Title: queryChildrenTreeNodes
	 * @author：liuyx 
	 * @date：2015年9月7日下午4:56:36
	 * @Description: 构建 机构 岗位 人员树
	 * @param pid
	 * @return
	 */
	@Override
	public List<ZTreeNode> queryChildrenTreeNodes(String pId) {
		// TODO Auto-generated method stub
		List<ZTreeNode> list = (List) dao.query("acMenuSpace.queryChildrenTreeNodes", pId);
		//设置根节点打开
		if(pId==null) {
			for(ZTreeNode node:list) {
				if("-1".equals(node.getpId())) {
					node.setOpen(true);
					break;
				}
			}
		}

		return list;
	}
	/**
	 * @Title: queryPermitted
	 * @author：liuyx 
	 * @date：2015年10月4日上午11:59:36
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Map> queryPermitted(Map paramMap) {
		// TODO Auto-generated method stub
		return (List<Map>) dao.query("acMenuSpace.queryPermitted", paramMap);
	}

}
