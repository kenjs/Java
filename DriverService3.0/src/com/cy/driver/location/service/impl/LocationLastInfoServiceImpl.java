package com.cy.driver.location.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.LocationCollectLastInfoBo;
import com.cy.driver.location.dao.LocationLastInfoDao;
import com.cy.driver.location.service.LocationLastInfoService;
/**
 * 位置信息service impl
 * @date 2014-6-6
 * @author haoyong
 *
 */
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class LocationLastInfoServiceImpl implements LocationLastInfoService {
	
	private LocationLastInfoDao locationLastInfoDao;
	
	public void setLocationLastInfoDao(LocationLastInfoDao locationLastInfoDao) {
		this.locationLastInfoDao = locationLastInfoDao;
	}

	public int insertLastLocation(LocationCollectLastInfoBo bo) {
		return locationLastInfoDao.insertLastLocation(bo);
	}

	public int insertLocation(LocationCollectLastInfoBo bo) {
		return locationLastInfoDao.insertLocation(bo);
	}

	public int checkLocationExist(String driverId) {
		return locationLastInfoDao.checkLocationExist(driverId);
	}

	public void updateLastLocation(LocationCollectLastInfoBo bo) {
		locationLastInfoDao.updateLastLocation(bo);
	}

}
