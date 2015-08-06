package com.cy.dctms.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao {

	private SqlMapClient sqlMapClient;
	
	public List<?> queryForList(String methodName, Object object) throws SQLException {
		return sqlMapClient.queryForList(methodName, object);

	}
	
	public List<?> queryForList(String methodName) throws SQLException {
		return sqlMapClient.queryForList(methodName);

	}
	
	
	public Object queryForObject(String methodName, Object object) throws SQLException {
		return sqlMapClient.queryForObject(methodName, object);

	}
	
	public Object queryForObject(String methodName) throws SQLException {
		return sqlMapClient.queryForObject(methodName);

	}
	
	public Object queryForObject(String methodName, String paraStr) throws SQLException {
		return sqlMapClient.queryForObject(methodName, paraStr);

	}
	
	public Long addObject(String methodName, Object object) throws SQLException {
		return (Long) sqlMapClient.insert(methodName, object);

	}
	
	public String addStringKeyObject(String methodName, Object object) throws SQLException {
		return (String) sqlMapClient.insert(methodName, object);

	}
	
	public int saveObject(String methodName, Object object) throws SQLException {
		return sqlMapClient.update(methodName, object);

	}
	
	public int deleteObject(String methodName, Long id) throws SQLException {
		return sqlMapClient.delete(methodName, id);

	}
	
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
