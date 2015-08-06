package com.cy.driver.service.impl;

import com.cy.driver.bo.DriverLineInfoBo;
import com.cy.driver.dao.DriverLineInfoDao;
import com.cy.driver.domain.DriverLineInfoDomain;
import com.cy.driver.service.DriverLineInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("driverLineInfoService")
public class DriverLineInfoServiceImpl implements DriverLineInfoService {

    @Resource
	private DriverLineInfoDao driverLineInfoDao;
	
	public int insertDriverLineInfo(DriverLineInfoBo bo) {
		return driverLineInfoDao.insertDriverLineInfo(bo);
	}

	public int updateDriverLineInfo(DriverLineInfoBo bo) {
		return driverLineInfoDao.updateDriverLineInfo(bo);
	}

	public int deleteDriverLineInfo(String id) {
		return driverLineInfoDao.deleteDriverLineInfo(id);
	}

	@SuppressWarnings("unchecked")
	public List<DriverLineInfoDomain> selectDriverLineInfoList(String driverId) {
		return (List<DriverLineInfoDomain>) driverLineInfoDao.selectDriverLineInfoList(driverId);
	}

	public void setDriverLineInfoDao(DriverLineInfoDao driverLineInfoDao) {
		this.driverLineInfoDao = driverLineInfoDao;
	}

	public int selectDriverLineInfoCount(String driverId) {
		return driverLineInfoDao.selectDriverLineInfoCount(driverId);
	}

}
