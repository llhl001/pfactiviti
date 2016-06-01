/**
 * BaseDaoImpl.java
 * @author：caojian @date：2015年1月22日下午10:47:23
 * Copyright2011-2015 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.common.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sdyy.common.page.PageView;

/**
 * @Description：
 * @author：caojian @date：2015年1月22日下午10:47:23
 */
public class BaseDaoImpl implements IBaseDao {
	private SqlSession sqlSession;

	public BaseDaoImpl(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#insert(java.lang.String,java.lang.Object)
	 */
	@Override
	public Object insert(String sqlId, Object parameter) {
		// TODO Auto-generated method stub
		sqlSession.insert(sqlId, parameter);
		return parameter;
	}
	@Override
	public int insert_(String sqlId, Object parameter) {
		// TODO Auto-generated method stub
		return sqlSession.insert(sqlId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#update(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public Object update(String sqlId, Object parameter) {
		// TODO Auto-generated method stub
		return sqlSession.update(sqlId, parameter);
	}
	@Override
	public int update_(String sqlId, Object parameter) {
		// TODO Auto-generated method stub
		return sqlSession.update(sqlId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#delete(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public int delete(String sqlId, Object parameter) {
		// TODO Auto-generated method stub
		return sqlSession.delete(sqlId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#get(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public Object get(String sqlId, Object parameter) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(sqlId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#query(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public List<Object> query(String sqlId, Object parameter) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(sqlId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#queryAll(java.lang.String)
	 */
	@Override
	public List<Object> queryAll(String sqlId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(sqlId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#batchInsert(java.lang.String,
	 * java.util.List)
	 */
	@Override
	public <T extends Object> int  batchInsert(String sqlId, List<T> list) {
		// TODO Auto-generated method stub
		if (list != null) {
			for (Iterator<T> it = list.iterator(); it.hasNext();) {
				sqlSession.insert(sqlId, it.next());
			}
			return list.size();
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sdyy.publics.dao.IBaseDao#batchUpdate(java.lang.String,
	 * java.util.List)
	 */
	@Override
	public int batchUpdate(String sqlId, List<Object> list) {
		// TODO Auto-generated method stub
		if (list != null) {
			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				sqlSession.update(sqlId, it.next());
			}
			return list.size();
		} else {
			return 0;
		}
	}

	/**
	 * @return the sqlSession
	 */
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	/**
	 * @param sqlSession
	 *            the sqlSession to set
	 */
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<Object> queryPage(String sqlId,PageView pageView,Object t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", t);
		return sqlSession.selectList(sqlId,map);
	}

}
