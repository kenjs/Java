package com.cy.dctms.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;
@Repository("orderCarogoInfoDao")
public interface OrderCarogoInfoDao {

	/**
	 * 移动
	 * @param map
	 * @throws SQLException
	 */
	public void insertExpiredCargoToHistoryTable(Map map) throws SQLException;

	/**
	 * 删除过期货源
     * @param map
	 * @throws SQLException
	 */
	public void deleteCargoOutOfDate(Map map) throws SQLException;

	/**
	 * 检查table是否存在
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public String chkTableIsExists(String tableName) throws SQLException;

	/**
	 * 创建保存过期货物的table
	 * @param sql
	 * @throws SQLException
	 */
	public void createTableByMonth(String sql) throws SQLException;
}
