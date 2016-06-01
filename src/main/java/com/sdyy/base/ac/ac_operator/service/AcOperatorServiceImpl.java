package com.sdyy.base.ac.ac_operator.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;
import com.sdyy.common.utils.Md5Util;

@Service
public class AcOperatorServiceImpl implements IAcOperatorService {
	
	@Autowired
	private IBaseDao dao;
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
		return (Map) dao.get("acOperatorSpace.get", id);
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
    	return dao.insert_("acOperatorSpace.insert", record);
    }

    /**
     * 修改		一条记录
     * @param beanMap
     * @return
     */
    @Override
    public int update(Map record){
    	
    	return dao.update_("acOperatorSpace.update", record);
    }
    
    /**
	 * 删除		一条记录
	 * @param  	 
	 */	
    @Override
    public int delete(String id){
    	return dao.delete("acOperatorSpace.delete", id);
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
		return dao.query("acOperatorSpace.query", paramMap);
	}
	/**
	 * @Title: checkLogin
	 * @author：liuyx 
	 * @date：2015年9月21日上午11:35:01
	 * @Description: 验证登录用户
	 * @param userId
	 * @param passWord
	 * @return
	 */
	@Override
	public Map checkLogin(String userId, String password) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("USER_ID", userId);
        Map user=null;
        List list =  dao.query("acOperatorSpace.query", paramMap);
        if(CollectionUtils.isEmpty(list)){
        	return null;
        }else {
        	user = (Map)list.get(0);
        }
        String psw = Md5Util.string2MD5(password);
        if(psw.equals(user.get("PASSWORD"))){
        	return user;
        }else{
        	return null;
        } 	
	}
     
}
