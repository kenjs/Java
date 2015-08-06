package com.cy.dcts.driverCargoAssess.dao.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.DriverCargoAssessInfoDomain;
import com.cy.dcts.driverCargoAssess.dao.IDriverCargoAssessDao;

public class DriverCargoAssessDaoImp extends BaseDao implements IDriverCargoAssessDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<DriverCargoAssessInfoDomain> queryDriverCargoAssessByCargoId(
			Map<String, Object> queryMap) {
		try{
			return (List<DriverCargoAssessInfoDomain>)queryForList("query_driver_cargo_assess_by_cargoId",queryMap);
		}catch(Exception e) {
			logger.warn("query_driver_cargo_assess_by_cargoId error.", e);
			throw new RuntimeException();
		}
	}

	public List<DriverCargoAssessInfoDomain> queryDriverCargoAssessByCargoIdPage(
			Map<String, Object> queryMap) {
		try{
			return (List<DriverCargoAssessInfoDomain>)queryForList("query_driver_cargo_assess_by_cargoId_page",queryMap);
		}catch(Exception e) {
			logger.warn("query_driver_cargo_assess_by_cargoId_page error.", e);
			throw new RuntimeException();
		}
	}

	public Integer queryDriverCargoAssessByCargoIdCount(Map<String, Object> queryMap) {
		try{
			return (Integer)this.queryForObject("query_driver_cargo_assess_by_cargoId_count", queryMap);
		}catch(Exception e) {
			logger.warn("query_driver_cargo_assess_by_cargoId_count error.", e);
			throw new RuntimeException();
		}
	}
	 
}
