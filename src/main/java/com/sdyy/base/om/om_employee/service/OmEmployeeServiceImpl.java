/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.om.om_employee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sdyy.base.ac.ac_operator.service.IAcOperatorService;
import com.sdyy.base.om.om_emp_org.service.IOmEmpOrgService;
import com.sdyy.common.consts.CommonConsts;
import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;
import com.sdyy.common.service.ConfigService;
import com.sdyy.common.utils.DateUtils;
import com.sdyy.common.utils.Md5Util;
import com.sdyy.common.utils.StringUtils;
import com.sdyy.common.zTree.ZTreeNode;

/**
 * @ClassName: OmEmployeeServiceImpl
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class OmEmployeeServiceImpl implements IOmEmployeeService {

	private static final String MAPPER_SPACE_NAME = "omEmployeeSpace";
	
	@Autowired
	private IBaseDao dao;
	@Autowired
	private IAcOperatorService acOperatorService;
	@Autowired
	private IOmEmpOrgService omEmpOrgService;
	/*@Autowired
	private IAcApplicationService acApplicationService;
	public void testTransaction() {
		this.delete("2");
		acApplicationService.delete("2");
		int i = 1/0;
		return;
	}*/
	
	@Override
	public Map get(String id) {
		// TODO Auto-generated method stub
		//return (Map) dao.get("omEmployeeSpace.get", operator_id);
		return (Map) dao.get("omEmployeeSpace.get", id);  
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
	 * 新增人员
	 * @param beanMap
	 * @return 
	 * 		-1:操作员重复
	 */    
    @Override
    public int insert(Map record){
    	
    	String userId = StringUtils.getTrimStr(record.get("USER_ID"));
    	String operatorId = "";
    	String now = DateUtils.getCurrentDate();
    	//操作员新增
    	if(!StringUtils.isEmpty(userId)) {
    		//操作员USER_ID重复检测
    		Map paramMap = new HashMap();
    		paramMap.put("USER_ID", userId);
    		List checkList = acOperatorService.query(paramMap);
    		if(!CollectionUtils.isEmpty(checkList)) {
    			return CommonConsts.DATA_REPEAT_ERR;
    		}
    		//设置密码
    		String psw = StringUtils.getTrimStr(record.get("PASSWORD"));
    		if(StringUtils.isEmpty(psw)) {
    			//空密码则设置默认密码
    			psw = ConfigService.getConfig(ConfigService.DEFAULT_PASSWORD);
    		}
    		Map operatorRecord = new HashMap();
    		operatorId = StringUtils.uniqueKey36();
    		operatorRecord.put("OPERATOR_NAME", record.get("EMP_NAME"));
    		operatorRecord.put("OPERATOR_ID", operatorId);
    		operatorRecord.put("USER_ID", userId);
    		operatorRecord.put("PASSWORD", Md5Util.string2MD5(psw));
    		operatorRecord.put("STATUS", record.get("STATUS"));
    		operatorRecord.put("EMAIL", record.get("P_EMAIL"));
    		
    		//新增操作员数据
    		dao.insert_("acOperatorSpace.insert", operatorRecord);
    	}
    	String empId = StringUtils.uniqueKey36();
    	record.put("EMP_ID", empId);
    	record.put("OPERATOR_ID", operatorId);
    	//record.put("REG_DATE", now);
    	record.put("CREATE_TIME", now);
    	
    	if(StringUtils.isEmpty(record.get("POSITION"))) {
        	Map empOrgRecord = new HashMap();
        	empOrgRecord.put("ORG_ID",record.get("ORG_ID"));
        	empOrgRecord.put("EMP_ID",record.get("EMP_ID"));
        	empOrgRecord.put("IS_MAIN", "1");
        	dao.insert_("omEmpOrgSpace.insert", empOrgRecord);
    	}else {
        	Map empPosiRecord = new HashMap();
        	empPosiRecord.put("POSITION_ID",record.get("POSITION"));
        	empPosiRecord.put("EMP_ID",record.get("EMP_ID"));
        	empPosiRecord.put("IS_MAIN", "1");
        	dao.insert_("omEmpPositionSpace.insert", empPosiRecord);
    	}

    	return dao.insert_("omEmployeeSpace.insert", record);
    }

    /**
     * 修改		一条记录
     * @param beanMap
     * @return
     */
    @Override
    public int update(Map record){
    	
    	String userId = StringUtils.getTrimStr(record.get("USER_ID"));
    	String operatorId = StringUtils.getTrimStr(record.get("OPERATOR_ID"));
    	String now = DateUtils.getCurrentDate();
    	//操作员新增
    	if(!StringUtils.isEmpty(userId)) {
    		//操作员USER_ID重复检测
    		Map paramMap = new HashMap();
    		paramMap.put("USER_ID", userId);
    		
    		
        	if(!StringUtils.isEmpty(operatorId)) {
        		//已有对应注册账号，检测时排除对应数据
        		paramMap.put("NOT_OPERATOR_ID", operatorId);
        	}
        	
    		List checkList = acOperatorService.query(paramMap);
    		if(!CollectionUtils.isEmpty(checkList)) {
    			return CommonConsts.DATA_REPEAT_ERR;
    		}
    		//设置密码
    		
    		Map operatorRecord = new HashMap();
    		
    		operatorRecord.put("OPERATOR_NAME", record.get("EMP_NAME"));
    		operatorRecord.put("USER_ID", userId);
    		String empStatus = StringUtils.getTrimStr(record.get("EMP_STATUS"));
    		if(!CommonConsts.EMP_STATUS_NORMAL.equals(empStatus)) {
    			//如果员工状态已经非正常在职，则禁用操作员，前台也应该加上限制。
    			operatorRecord.put("STATUS", CommonConsts.OPERATOR_STATUS_ABNORMAL);//做成常量
    		}else {
    			//operatorRecord.put("STATUS", record.get("STATUS"));
    			operatorRecord.put("STATUS", CommonConsts.OPERATOR_STATUS_NORMAL);
    		}
    		
    		operatorRecord.put("STATUS", record.get("STATUS"));
    		operatorRecord.put("EMAIL", record.get("P_EMAIL"));
    		
    		if(StringUtils.isEmpty(operatorId)) {
    			//无已有操作员账号
    			operatorId = StringUtils.uniqueKey36();
        		operatorRecord.put("OPERATOR_ID", operatorId);
        		String psw = StringUtils.getTrimStr(record.get("PASSWORD"));
        		if(StringUtils.isEmpty(psw)) {
        			//空密码则设置默认密码
        			psw = ConfigService.getConfig(ConfigService.DEFAULT_PASSWORD);
        		}
        		operatorRecord.put("PASSWORD", Md5Util.string2MD5(psw));
        		//新增操作员数据
        		dao.insert_("acOperatorSpace.insert", operatorRecord);
        	}else {
        		//已有操作员账号
        		operatorRecord.put("OPERATOR_ID", operatorId);
        		String psw = StringUtils.getTrimStr(record.get("PASSWORD"));
        		if(!StringUtils.isEmpty(psw)) {
        			operatorRecord.put("PASSWORD", Md5Util.string2MD5(psw));
        		}
        		dao.update_("acOperatorSpace.update", operatorRecord);
        	}
    	}
    	record.put("OPERATOR_ID", operatorId);
    	//暂不支持调岗调机构
    	return dao.update_("omEmployeeSpace.update", record);
    }
    
    /**
	 * 删除		一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("omEmployeeSpace.delete", id);
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
		List list = (List) dao.query("omEmployeeSpace.query", paramMap);
		return list;
	}
	@Override
	public List<ZTreeNode> queryTreeNodes(Map paramMap) {
		// TODO Auto-generated method stub
		List<ZTreeNode> list = (List) dao.query("omEmployeeSpace.queryTreeNodes", paramMap);
		for(ZTreeNode n:list) {
			if(n.getpId().equals("-1")) {
				n.setOpen(true);
				break;
			}
		}
		//设置根节点打开
/*		if(pId==null) {
			for(ZTreeNode node:list) {
				if("-1".equals(node.getpId())) {
					node.setOpen(true);
					break;
				}
			}
		}*/

		return list;
	}
	
	@Override
	public List<ZTreeNode> queryOrgEmpTreeNodes(Map paramMap) {
		// TODO Auto-generated method stub
		List<ZTreeNode> list = (List) dao.query("omEmployeeSpace.queryOrgEmpTreeNodes", paramMap);
		for(ZTreeNode n:list) {
			if(n.getpId().equals("-1")) {
				n.setOpen(true);
				break;
			}
		}
		//设置根节点打开
/*		if(pId==null) {
			for(ZTreeNode node:list) {
				if("-1".equals(node.getpId())) {
					node.setOpen(true);
					break;
				}
			}
		}*/

		return list;
	}
}
