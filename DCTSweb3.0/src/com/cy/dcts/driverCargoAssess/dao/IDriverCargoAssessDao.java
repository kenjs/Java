package com.cy.dcts.driverCargoAssess.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.domain.DriverCargoAssessInfoDomain;

public interface IDriverCargoAssessDao {
	/**
	 * 根据货源Id查询司机的点评——不分页
	 * @param cargoId 货源Id
	 * @return
	 */
	List<DriverCargoAssessInfoDomain> queryDriverCargoAssessByCargoId(Map<String, Object> queryMap);
	
	/**
	 * 根据货源Id查询司机的点评——分页
	 * @param cargoId 货源Id
	 * @return
	 */
	List<DriverCargoAssessInfoDomain> queryDriverCargoAssessByCargoIdPage(Map<String, Object> queryMap);
	
	/**
	 * 根据货源Id查询司机的总记录数
	 * @param cargoId 货源Id
	 * @return
	 */
	Integer queryDriverCargoAssessByCargoIdCount(Map<String, Object> queryMap);
	
}
