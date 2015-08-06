package com.cy.driver.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.cy.driver.bo.AppShareInfo;

@Repository("appShareInfoDao")
public interface AppShareInfoDao {
	
	/**
	 * 新增记录
	 * @param appShareInfo
	 * @return
	 * @throws SQLException
	 */
	public int addAppShareInfo(AppShareInfo appShareInfo) throws SQLException;
	
	/**
	 * 查询是否已推荐
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public int selectAppShareInfoByNum(String num) throws SQLException;
}
