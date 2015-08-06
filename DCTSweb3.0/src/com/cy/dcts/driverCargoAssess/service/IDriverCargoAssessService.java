package com.cy.dcts.driverCargoAssess.service;

import java.util.List;

import com.cy.dcts.common.domain.DriverCargoAssessInfoDomain;

public interface IDriverCargoAssessService {
	/**
	 * 根据货源Id查询司机的点评
	 * @param cargoId 货源Id
	 * @return
	 */
	List<DriverCargoAssessInfoDomain> queryDriverCargoAssessByCargoId(DriverCargoAssessInfoDomain driverCargoAssessInfoDomain);
}
