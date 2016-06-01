package com.sdyy.example.demo.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdyy.common.dao.IBaseDao;
import com.sdyy.common.page.PageView;


@Service
public class DemoTestServiceImpl implements IDemoTestService {

	@Autowired
	private IBaseDao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map get(String id) {
		return (Map) dao.get("demoTestSpace.get", id);
	}

	@Override
	public int insert(Map record) {
		return dao.insert_("demoTestSpace.insert", record);
	}

	@Override
	public int update(Map record) {
		return dao.update_("demoTestSpace.get", record);
	}

	@Override
	public PageView queryPage(PageView pageView,Map record) {
		List<Map<String,Object>> list = (List<Map<String,Object>>)dao.queryPage("demoTestSpace.queryPage",pageView, record);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<Map> query(Map record) {
		List<Map<String,Object>> list = (List<Map<String,Object>>)dao.query("demoTestSpace.query", record);
		return null;
	}

	/**
	 * @Title: delete
	 * @author：liuyx 
	 * @date：2015年8月28日下午3:42:49
	 * @Description: TODO
	 * @param id
	 * @return
	 */
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
