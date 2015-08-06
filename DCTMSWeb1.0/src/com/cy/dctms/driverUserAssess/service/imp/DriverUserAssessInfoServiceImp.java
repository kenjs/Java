package com.cy.dctms.driverUserAssess.service.imp;

import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;
import com.cy.dctms.driverUserAssess.dao.IDriverUserAssessInfoDao;
import com.cy.dctms.driverUserAssess.service.IDriverUserAssessInfoService;

public class DriverUserAssessInfoServiceImp implements IDriverUserAssessInfoService {

	private IDriverUserAssessInfoDao driverUserAssessInfoDao;

	@Override
	public void queryDriverUserAssessInfoList(DriverUserAssessInfoDomain driverUserAssessInfoDomain) {
		driverUserAssessInfoDao.queryDriverUserAssessInfoList(driverUserAssessInfoDomain);
	}

	@Override
	public void exportDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain) {
		 driverUserAssessInfoDao.exportDriverUserAssessInfo(driverUserAssessInfoDomain);
	}

	@Override
	public DriverUserAssessInfoDomain queryDriverUserAssessInfoMxById(String id) {
		 return driverUserAssessInfoDao.queryDriverUserAssessInfoById(id);
	}

	@Override
	public void saveDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain ,String userId) {		
		driverUserAssessInfoDao.saveDriverUserAssessInfo(driverUserAssessInfoDomain,userId);
	}

	@Override
	public void deleteDriverUserAssessInfo(String id,String userId) {
		driverUserAssessInfoDao.deleteDriverUserAssessInfo(id,userId);
		
	}

	public void setDriverUserAssessInfoDao(IDriverUserAssessInfoDao driverUserAssessInfoDao) {
		this.driverUserAssessInfoDao = driverUserAssessInfoDao;
	}
}
