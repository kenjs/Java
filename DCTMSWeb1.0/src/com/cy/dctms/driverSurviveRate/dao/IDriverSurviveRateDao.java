package com.cy.dctms.driverSurviveRate.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.DriverSurviveRateDomain;

public interface IDriverSurviveRateDao {

	/**
	 * 查询查询存活率信息列表
	 * @author:wjl
	 */
	public void queryDriverSurviveRateList(DriverSurviveRateDomain driverSurviveRateDomain);
}
