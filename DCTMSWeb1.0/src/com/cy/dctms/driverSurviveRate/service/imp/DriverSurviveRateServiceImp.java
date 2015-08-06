package com.cy.dctms.driverSurviveRate.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.DriverSurviveRateDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.driverSurviveRate.dao.IDriverSurviveRateDao;
import com.cy.dctms.driverSurviveRate.service.IDriverSurviveRateService;

public class DriverSurviveRateServiceImp implements IDriverSurviveRateService {

	private IDriverSurviveRateDao driverSurviveRateDao;

	@Override
	public void queryDriverSurviveRateList(DriverSurviveRateDomain driverSurviveRateDomain) {
		driverSurviveRateDao.queryDriverSurviveRateList(driverSurviveRateDomain);
	}

	public void setDriverSurviveRateDao(IDriverSurviveRateDao driverSurviveRateDao) {
		this.driverSurviveRateDao = driverSurviveRateDao;
	}
}
