package com.cy.driver.assess.service.impl;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverUserAssessInfoBo;
import com.cy.driver.assess.dao.DriverUserAssessInfoDao;
import com.cy.driver.assess.domain.AssessDomain;
import com.cy.driver.assess.domain.UserDriverAssessInfoDomain;
import com.cy.driver.assess.service.DriverUserAssessInfoService;
/**
 * 货源评价service impl
 * @date 2014-6-9
 * @author haoyong
 *
 */
public class DriverUserAssessInfoServiceImpl implements
		DriverUserAssessInfoService {

	private DriverUserAssessInfoDao driverUserAssessInfoDao;
	
	public void setDriverUserAssessInfoDao(
			DriverUserAssessInfoDao driverUserAssessInfoDao) {
		this.driverUserAssessInfoDao = driverUserAssessInfoDao;
	}

	public int addNewDriverUserAssessInfo(DriverUserAssessInfoBo bo) {
		return driverUserAssessInfoDao.addNewDriverUserAssessInfo(bo);
	}

	public int updateDriverUserAssessInfo(DriverUserAssessInfoBo bo) {
		return driverUserAssessInfoDao.updateDriverUserAssessInfo(bo);
	}
	
	public int selectAssessNum(DriverUserAssessInfoBo bo) {
		return driverUserAssessInfoDao.selectAssessNum(bo);
	}

	public int selectDriverUserAssess(String transactionId) {		
		return driverUserAssessInfoDao.selectDriverUserAssess(transactionId);
	}

	public List<AssessDomain> selectUserDriverAssessNum(String driverId)
			throws Exception {
		return driverUserAssessInfoDao.selectUserDriverAssessNum(driverId);
	}

	public List<UserDriverAssessInfoDomain> selectUserDriverAssessInfoList(
			Map<String, Object> map) throws Exception {
		return driverUserAssessInfoDao.selectUserDriverAssessInfoList(map);
	}

	
}
