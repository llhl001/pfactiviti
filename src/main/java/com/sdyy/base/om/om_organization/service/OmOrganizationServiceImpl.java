/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_organization.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: OmOrganizationServiceImpl
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class OmOrganizationServiceImpl implements IOmOrganizationService {

	@Autowired
	private IBaseDao dao;
	
	@Override
	public Map get(String id) {
		// TODO Auto-generated method stub
		//return (Map) dao.get("omOrganizationSpace.get", operator_id);
		return (Map) dao.get("omOrganizationSpace.get", id);  
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
    	return dao.insert_("omOrganizationSpace.insert", record);
    }

    /**
     * 修改		一条记录
     * @param beanMap
     * @return
     */
    @Override
    public int update(Map record){
    	return dao.update_("omOrganizationSpace.update", record);
    }
    
    /**
	 * 删除		一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("omOrganizationSpace.delete", id);
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
		List list = (List) dao.query("omOrganizationSpace.query", paramMap);
		return list;
	}
	/**
	 * @Title: queryChildrenTreeNodes
	 * @author：liuyx 
	 * @date：2015年9月7日下午4:56:36
	 * @Description: 构建 机构 岗位 人员树
	 * @param paramMap
	 * @return
	 */
	public List<ZTreeNode> queryChildrenTreeNodes(Map<String,Object> paramMap){
		// TODO Auto-generated method stub
		List<ZTreeNode> list = null;
		if(StringUtils.isEmpty(paramMap.get("onlyOrg"))) {
			list = (List) dao.query("omOrganizationSpace.queryChildrenTreeNodes", paramMap);
		}else {
			list = (List) dao.query("omOrganizationSpace.queryOnlyOrgChildrenTreeNodes", paramMap);
		}
		
		String pId = paramMap.get("pId")==null?null:paramMap.get("pId").toString();
		//设置根节点打开
		if(pId==null||"-1".equals(pId)) {
			for(ZTreeNode node:list) {
				if("-1".equals(node.getpId())) {
					node.setOpen(true);
					break;
				}
			}
		}else {
			for(ZTreeNode n:list) {
				if(n.getId().equals(pId)) {
					n.setOpen(true);
					break;
				}
			}
		}

		String checkedOrgIdList = paramMap.get("checkedOrgIdList")==null?null:paramMap.get("checkedOrgIdList").toString();
		if(!StringUtils.isEmpty(checkedOrgIdList)) {
			String[] checkedArray = checkedOrgIdList.split(",");
			for(String corg:checkedArray) {
				for(ZTreeNode ztn:list) {
					if(ztn.getId().equals(corg)) {
						ztn.setChecked(true);
						break;
					}
				}
			}
		}
		return list;
	}
	/**
	 * 
	 * @Title: adjustByTree
	 * @author：liuyx
	 * @date：2015年12月29日上午11:25:39
	 * @Description: 机构岗位人员结构调整
	 * @param id
	 * @param type
	 * @param fromId
	 * @param fromType
	 * @param toId
	 * @param toType
	 * @param isCopy
	 * @return
	 */
	public int adjust(String id,String type,String fromId,
			String fromType,String toId,String toType,String isCopy) {
		Map record = new HashMap();
		if("ORG".equals(type)) {
			//机构只会调整到机构下,只需将机构表父节点修改即可
			record.put("ORG_ID", id);
			record.put("PARENT_ORG_ID", toId);
			return dao.update_("omOrganizationSpace.update", record);
		}else if("POSI".equals(type)) {
			//岗位可以调整到机构或者岗位下
			//如果调整到岗位下，只要修改所属岗位
			record.put("POSITION_ID", id);
			if("POSI".equals(toType)) {
				record.put("PARENT_POSI_ID", toId);
			}
			//如果调整到机构下，则清空所属岗位，将机构调整
			else if("ORG".equals(toType)) {
				record.put("PARENT_POSI_ID", "");
				record.put("ORG_ID", toId);
			}
			return dao.update_("omPositionSpace.update", record);
		}else if("EMP".equals(type)) {
			//人员可以调整到机构或者岗位下
			record.put("EMP_ID", id);
			//如果是复制到岗位下，则插入关联表，主岗设成否
			if("true".equals(isCopy)) {
				if("POSI".equals(toType)) {
					record.put("POSITION_ID",toId);
					record.put("IS_MAIN", "0");
					return dao.insert_("omEmpPositionSpace.insert", record);
				}
			}
			//如果移动到岗位下，
			//1先删除之前的人员岗位关联表，2以及人员机构关联表，3然后插入岗位关联关系,4再修改emp主表中的岗位,以及机构改为岗位所属机构
			if("POSI".equals(toType)) {
				record.put("POSITION_ID",fromId);
				//先删除之前的人员岗位关联表
				dao.delete("omEmpPositionSpace.delete", record);
				//删除人员机构关联表
				dao.delete("omEmpOrgSpace.delete", record);
				record.put("POSITION_ID",toId);
				record.put("IS_MAIN", "1");
				//插入岗位关联关系
				dao.insert_("omEmpPositionSpace.insert", record);
				record.put("POSITION",toId);
				Map toPosition = (Map) dao.get("omPositionSpace.get", toId); 
				record.put("ORG_ID", toPosition.get("ORG_ID"));
				return dao.update_("omEmployeeSpace.update", record);
			}
			
			//如果是移动到机构下，1先删除之前的该人员和岗位的所有关联关系，2以及人员机构关联表,3插入人员机构关联关系，4再清空emp主表中的岗位,并且修改机构
			//注 如果移动到机构下，则之前的多个岗位全部清空
			else if("ORG".equals(toType)) {
				//先删除之前的人员岗位关联表
				dao.delete("omEmpPositionSpace.delete", record);
				//删除人员机构关联表
				dao.delete("omEmpOrgSpace.delete", record);
				record.put("ORG_ID",toId);
				record.put("IS_MAIN", "1");
				dao.insert_("omEmpOrgSpace.insert", record);
				record.put("POSITION","");
				return dao.update_("omEmployeeSpace.update", record);
			}
		}
		
		return 0;
	}
}
