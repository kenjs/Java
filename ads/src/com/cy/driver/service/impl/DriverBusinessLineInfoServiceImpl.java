package com.cy.driver.service.impl;

import com.cy.driver.bo.DriverBusinessLineInfo;
import com.cy.driver.dao.DriverBusinessLineInfoDao;
import com.cy.driver.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.service.DriverBusinessLineInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
/**
 * 预约service impl
 * 2014-5-27
 * @author haoyong
 *
 */
@Service("driverBusinessLineInfoService")
public class DriverBusinessLineInfoServiceImpl implements DriverBusinessLineInfoService {

    @Resource
	private DriverBusinessLineInfoDao driverBusinessLineInfoDao;
	
	@Transactional
	public int insertDriverBusinessLineInfo(DriverBusinessLineInfo bo) {
		return driverBusinessLineInfoDao.insertDriverBusinessLineInfo(bo);
	}

	@Transactional
	public int updateDriverBusinessLineInfo(DriverBusinessLineInfo bo) {
		return driverBusinessLineInfoDao.updateDriverBusinessLineInfo(bo);
	}

	@Transactional
	public int deleteDriverBusinessLineInfo(String id) {
		return driverBusinessLineInfoDao.deleteDriverBusinessLineInfo(id);
	}

	public List<DriverBusinessLineInfoDomain> selectDriverBusinessLineInfoList(String driverId) {
        return driverBusinessLineInfoDao.selectDriverBusinessLineInfoList(driverId);
	}

	public void setDriverBusinessLineInfoDao(
			DriverBusinessLineInfoDao driverBusinessLineInfoDao) {
		this.driverBusinessLineInfoDao = driverBusinessLineInfoDao;
	}

	public int selectDriverBusinessLineInfoCount(String driverId) {
		return driverBusinessLineInfoDao.selectDriverBusinessLineInfoCount(driverId);
	}

}
