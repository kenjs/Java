package com.cy.driver.location.dao.impl;

import java.sql.SQLException;

import com.cy.common.bo.LocationCollectLastInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.location.dao.LocationLastInfoDao;
/**
 * 位置信息dao impl
 * @date 2014-6-6
 * @author haoyong
 *
 */
public class LocationLastInfoDaoImpl extends BaseDao implements LocationLastInfoDao {

	public int insertLastLocation(LocationCollectLastInfoBo bo) {
		int key = 0;
		try {
			key = addObject("iBatisInsertLastLocation", bo);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return key;
	}
	
	public int insertLocation(LocationCollectLastInfoBo bo) {
		int key = 0;
		try {
			key = addObject("iBatisInsertCollentInfoLocation", bo);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return key;
	}
	
	public int checkLocationExist(String driverId) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectExistByDriverId", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public void updateLastLocation(LocationCollectLastInfoBo bo) {
		try {
			saveObject("iBatisUpdateLastLocation", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
