package com.cy.driver.line.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.cy.common.bo.DriverLineInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.line.dao.DriverLineInfoDao;
import com.cy.driver.line.domain.DriverLineInfoDomain;
/**
 * WEB司机线路daoImpl
 * @date 2014-6-3
 * @author haoyong
 *
 */
public class DriverLineInfoDaoImpl extends BaseDao implements DriverLineInfoDao {

	public int insertDriverLineInfo(DriverLineInfoBo bo) {
		int i = 0;
		try {
			i = addObject("iBatisInsertDriverLineInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int updateDriverLineInfo(DriverLineInfoBo bo) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateDriverLineInfoById", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int deleteDriverLineInfo(String id) {
		int i = 0;
		try {
			i = deleteObject("iBatisDeleteDriverLineInfo", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<DriverLineInfoDomain> selectDriverLineInfoList(String driverId) {
		List<DriverLineInfoDomain> list = null;
		try {
			list = (List<DriverLineInfoDomain>) queryForList("iBatisSelectDriverLineInfoByDriverId", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int selectDriverLineInfoCount(String driverId) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectDriverLineInfoCount", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
