package com.cy.driver.user.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.user.dao.DriverUserCargoInfoDao;
import com.cy.driver.user.domain.CompanyInfoDomain;
import com.cy.driver.user.domain.DriverNotificationInfoDomain;
import com.cy.driver.user.domain.DriverUserInfoDomain;
/**
 * 货源信息dao impl
 * @date 2014-5-29
 * @author haoyong
 *
 */
public class DriverUserCargoInfoDaoImpl extends BaseDao implements
		DriverUserCargoInfoDao {

	@SuppressWarnings("unchecked")
	public List<DriverBusinessLineInfoDomain> selectDriverBusinessLineInfoByDriverId(String driverId){
		List<DriverBusinessLineInfoDomain> list = null;
		try {
			list = (List<DriverBusinessLineInfoDomain>) queryForList("iBatisSelectDriverBusinessLineInfoByDriverId", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int selectSuitCargoCount(Map<String,Object> map){
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectSuitCargoCount", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int selectNearByCargoCount(Map<String,Object> map) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectNearByCargoCount", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int updateDriverUserInfo(DriverUserInfoBo bo) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateDriverUserInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public Map<String,Object> selectDriverLastLocation(String driverId ) {
		Map<String,Object> map = null;
		try {
			map = (Map<String, Object>) queryForObject("iBatisSelectDriverLastLocationByDriverId", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	public DriverUserInfoDomain selectUserBasicInfo(String driverId) {
		DriverUserInfoDomain domain = null;
		try {
			domain = (DriverUserInfoDomain) queryForObject("iBatisSelectDriverUserInfoByCode",driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domain;
	}

	@SuppressWarnings("unchecked")
	public List<DriverNotificationInfoDomain> queryDriverNotificationInfo(Map<String,Object> map) {
		List<DriverNotificationInfoDomain> list  = null;
		try {
			list =  (List<DriverNotificationInfoDomain>) queryForList("iBatisQueryDriverNotificationInfo", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public CompanyInfoDomain selectConpanyInfoById(String id) {
		CompanyInfoDomain domain = null;
		try {
			domain = (CompanyInfoDomain) queryForObject("iBatisSelectCompanyInfoById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domain;
	}
}
