package com.cy.dctms.orm;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MybatisDaoSurrport extends SqlSessionDaoSupport{

	public int add(String key,Object object) {
		return getSqlSession().insert(key, object);
	}
	
	public void update(String key,Object object) {
		getSqlSession().update(key, object);
	}
	
	public Object queryObject(String key,Object object) {
		return getSqlSession().selectOne(key, object);
	}
	
	public Object queryObject(String key) {
		return getSqlSession().selectOne(key);
	}
	
	public List<T> queryList(String key,Object object) {
		return getSqlSession().selectList(key, object); 
	}
	
	public List<T> queryList(String key) {
		return getSqlSession().selectList(key); 
	}
	
	public void delete(String key,Object object) {
		getSqlSession().delete(key,object);
	}
	
	public void delete(String key) {
		getSqlSession().delete(key);
	}
	
	public Map<T,T> queryMap(String key,String value) {
		return getSqlSession().selectMap(key, value);
	}
}
