package com.cy.dctms.driverUser.service.imp;


import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.driverUser.dao.IDriverUserInfoDao;
import com.cy.dctms.driverUser.service.IDriverUserInfoService;

public class DriverUserInfoServiceImp implements IDriverUserInfoService {

	private IDriverUserInfoDao driverUserInfoDao;

	@Override
	public void queryDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain) {
		driverUserInfoDao.queryDriverUserInfoList(driverUserInfoDomain);
	}
	
	@Override
	public void auditDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain) {
		driverUserInfoDao.auditDriverUserInfoList(driverUserInfoDomain);
	}
	
	@Override
	public void queryDriverUserTransactionInfoList(DriverUserInfoDomain driverUserInfoDomain) {
		driverUserInfoDao.queryDriverUserTransactionInfoList(driverUserInfoDomain);
	}
	
	@Override
	public void driverUserTotalDataList(DriverUserInfoDomain driverUserInfoDomain) {
		driverUserInfoDao.driverUserTotalDataList(driverUserInfoDomain);
	}
	

	@Override
	public DriverUserInfoDomain queryDriverUserInfoMxById(String id) {
		 return driverUserInfoDao.queryDriverUserInfoById(id);
	}
	
	@Override
	public DriverUserInfoDomain queryDriverUserInfoMxByCode(String code) {
		 return driverUserInfoDao.queryDriverUserInfoByCode(code);
	}
	
	@Override
	public void saveDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain ,String userId) {
		driverUserInfoDao.saveDriverUserInfo(driverUserInfoDomain,userId);
	}

	@Override
	public void deleteDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId) {
		driverUserInfoDao.deleteDriverUserInfo(driverUserInfoDomain,userId);
		
	}

	public void setDriverUserInfoDao(IDriverUserInfoDao driverUserInfoDao) {
		this.driverUserInfoDao = driverUserInfoDao;
	}
	@Override
	public void auditDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain ,String userId) {
		DriverUserInfoDomain domain = new DriverUserInfoDomain();		
		driverUserInfoDao.auditDriverUserInfo(driverUserInfoDomain,userId);
	}
}
