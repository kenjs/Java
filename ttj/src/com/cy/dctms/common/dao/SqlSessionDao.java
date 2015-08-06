package com.cy.dctms.common.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;
/**
 * mybatis 数据库操作类
 * @author Administrator
 *
 */
public class SqlSessionDao extends SqlSessionDaoSupport{

	/**
	 * 插入数据库对象
	 * @param key
	 * @param object
	 * @return 主键
	 */
	public int insert(String key,Object object) {
		return getSqlSession().insert(key, object);
	}

	/**
	 * 插入数据库对象
	 * @param key
	 * @return 主键
	 */
	public int insert(String key) {
		return getSqlSession().insert(key);
	}

	/**
	 * 更新数据库对象
	 * @param key
	 * @param object
	 * @return 1 or 0
	 */
	public int update(String key,Object object) {
		return getSqlSession().update(key, object);
	}

	/**
	 * 查询数据库对象
	 * @param key
	 * @param object
	 * @return object
	 */
	public Object queryForObject(String key,Object object) {
		return getSqlSession().selectOne(key, object);
	}

	/**
	 * 查询数据库对象
	 * @param key
	 * @return object
	 */
	public Object queryForObject(String key) {
		return getSqlSession().selectOne(key);
	}

	/**
	 * 查询数据库集合
	 * @param key
	 * @param object
	 * @return list
	 */
	public List<?> queryForList(String key,Object object) {
		return getSqlSession().selectList(key, object);
	}

	/**
	 * 查询数据库集合
	 * @param key
	 * @return list
	 */
	public List<?> queryForList(String key) {
		return getSqlSession().selectList(key);
	}

	/**
	 * 删除数据库对象
	 * @param key
	 * @param object
	 * @return 1 or 0
	 */
	public int delete(String key,Object object) {
		return getSqlSession().delete(key,object);
	}

	/**
	 * 删除数据库对象
	 * @param key
	 * @param object
	 * @return 1 or 0
	 */
	public int delete(String key) {
		return getSqlSession().delete(key);
	}

	/**
	 * 查询数据库对象
	 * @param key
	 * @param value
	 * @return map
	 */
	public Map<?,?> queryMap(String key,String value) {
		return getSqlSession().selectMap(key, value);
	}
}
