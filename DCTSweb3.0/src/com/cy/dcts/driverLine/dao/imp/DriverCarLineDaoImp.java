package com.cy.dcts.driverLine.dao.imp;

import java.util.List;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.DriverLineInfo;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.driverLine.dao.IDriverCarLineDao;

public class DriverCarLineDaoImp extends BaseDao implements IDriverCarLineDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainByPage(Map<String, Object> queryMap) {
		try {
			return (List<DriverBusinessLineInfoDomain>) queryForList("query_driver_business_line_info_domain_byPage",queryMap);
		}catch (Exception e) {
			logger.error("query_driver_business_line_info_domain_byPage error!",e);
			throw new RuntimeException();
		}
	}

	public Integer queryDriverBusinessLineInfoDomainByPageCount(Map<String, Object> queryMap) {
		try {
			return (Integer) queryForObject("query_driver_business_line_info_domain_byPage_count",queryMap);
		}catch (Exception e) {
			logger.error("query_driver_business_line_info_domain_byPage_count error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainList(
			Map<String, Object> queryMap) {
		try {
			return (List<DriverBusinessLineInfoDomain>) queryForList("query_driver_business_line_info_domain",queryMap);
		}catch (Exception e) {
			logger.error("query_driver_business_line_info_domain error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainByDriverId(
			String driverId) {
		try {
			return (List<DriverBusinessLineInfoDomain>) queryForList("query_driver_business_line_info_domain_by_driverId",driverId);
		}catch (Exception e) {
			logger.error("query_driver_business_line_info_domain_by_driverId error!",e);
			throw new RuntimeException();
		}
	}

	public List<DriverLineInfo> queryDriverLineInfoByDriverId(String driverId) {
		try {
			return (List<DriverLineInfo>) queryForList("query_driver_line_info_domain_by_driverId",driverId);
		}catch (Exception e) {
			logger.error("query_driver_line_info_domain_by_driverId error!",e);
			throw new RuntimeException();
		}
	}
	
	
}
