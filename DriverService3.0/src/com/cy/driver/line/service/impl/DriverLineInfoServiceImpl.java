package com.cy.driver.line.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cy.common.bo.DriverLineInfoBo;
import com.cy.driver.line.dao.DriverLineInfoDao;
import com.cy.driver.line.domain.DriverLineInfoDomain;
import com.cy.driver.line.service.DriverLineInfoService;

public class DriverLineInfoServiceImpl implements DriverLineInfoService {
	
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
		List<DriverLineInfoDomain> dataList = new ArrayList<DriverLineInfoDomain>();
		List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoDao.selectDriverLineInfoList(driverId);
		if(list != null) {
			for (DriverLineInfoDomain e : list) {
				dataList.add(e);
				if(dataList.size() >= 3)
					break;
			}
		}
		return dataList;
	}

	public void setDriverLineInfoDao(DriverLineInfoDao driverLineInfoDao) {
		this.driverLineInfoDao = driverLineInfoDao;
	}

	public int selectDriverLineInfoCount(String driverId) {
		return driverLineInfoDao.selectDriverLineInfoCount(driverId);
	}

}
