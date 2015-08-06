package com.cy.common.dao;

import java.sql.SQLException;
import java.util.List;


import com.ibatis.sqlmap.client.SqlMapClient;

//import com.ibatis.sqlmap.client.SqlMapClient;
/**
 * 基础Dao层
 * @author Administrator
 *
 */
public class BaseDao {

	private SqlMapClient sqlMapClient;
	
	/**
	 * 查询集合
	 * @param methodName
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public List<?> queryForList(String methodName, Object object) throws SQLException {
		List <?> list=sqlMapClient.queryForList(methodName,object);
		return list;

	}
	/**
	 * 查询集合
	 * @param methodName
	 * @return
	 * @throws SQLException
	 */
	public List<?> queryForList(String methodName) throws SQLException {
		List <?> list=sqlMapClient.queryForList(methodName);
		return list;

	}
	
	/**
	 * 查询对象
	 * @param methodName
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public Object queryForObject(String methodName, Object object) throws SQLException {
		Object ob=sqlMapClient.queryForObject(methodName, object);
		return ob;

	}
	/**
	 * 查询对象
	 * @param methodName
	 * @return
	 * @throws SQLException
	 */
	public Object queryForObject(String methodName) throws SQLException {
		Object ob=sqlMapClient.queryForObject(methodName);
		return ob;

	}
	/**
	 * 查询对象
	 * @param methodName
	 * @param paraStr
	 * @return
	 * @throws SQLException
	 */
	public Object queryForObject(String methodName, String paraStr) throws SQLException {
		Object ob=sqlMapClient.queryForObject(methodName, paraStr);
		return ob;

	}
	/**
	 * 插入对象
	 * @param methodName
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public int addObject(String methodName, Object object) throws SQLException {
		int lo=(Integer)sqlMapClient.insert(methodName, object);
		return lo;

	}
	/**
	 * 插入对象
	 * @param methodName
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public String addStringKeyObject(String methodName, Object object) throws SQLException {
		String str=(String)sqlMapClient.queryForObject(methodName);
		return  str;

	}
	/**
	 *更新对象
	 * @param methodName
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public int saveObject(String methodName, Object object) throws SQLException {
		int i=sqlMapClient.update(methodName, object);
		return i;

	}
	/**
	 * 删除对象
	 * @param methodName
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteObject(String methodName, Long id) throws SQLException {
		return sqlMapClient.delete(methodName, id);

	}
	/**
	 * 删除对象
	 * @param methodName
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public int deleteObject(String methodName, Object object) throws SQLException {
		return sqlMapClient.delete(methodName, object);
	}
	

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
}
