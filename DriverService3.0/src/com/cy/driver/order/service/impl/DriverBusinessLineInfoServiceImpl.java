package com.cy.driver.order.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.DriverBusinessLineInfo;
import com.cy.common.util.DateUtil;
import com.cy.driver.order.dao.DriverBusinessLineInfoDao;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.order.service.DriverBusinessLineInfoService;
/**
 * 预约service impl
 * 2014-5-27
 * @author haoyong
 *
 */
public class DriverBusinessLineInfoServiceImpl implements
		DriverBusinessLineInfoService {

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
		/**
		 * 对司机预约线路进行处理，由于只有3条有效数据，所以查出的预约线路做多只能取三条
		 * 而且预约的结束时间不能小于系统时间
		 */		
		List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoDao.selectDriverBusinessLineInfoList(driverId);
		List<DriverBusinessLineInfoDomain> dataList = new ArrayList<DriverBusinessLineInfoDomain>();
		Date currenDate = null;
		Date endDate = null;
		String endTime = "";
		try {
			currenDate = DateUtil.getNow();
			if(list != null) {
				for (DriverBusinessLineInfoDomain e : list) {
					endTime = e.getEndTime();
					endDate = DateUtil.formatDate(endTime);
					if(DateUtil.isEarly(endDate,currenDate)) {
						continue;
					}
					dataList.add(e);
					if(dataList.size() >=3 ) {
						break;
					}
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		return dataList;
	}

	public void setDriverBusinessLineInfoDao(
			DriverBusinessLineInfoDao driverBusinessLineInfoDao) {
		this.driverBusinessLineInfoDao = driverBusinessLineInfoDao;
	}

	public int selectDriverBusinessLineInfoCount(String driverId) {
		return driverBusinessLineInfoDao.selectDriverBusinessLineInfoCount(driverId);
	}

}
