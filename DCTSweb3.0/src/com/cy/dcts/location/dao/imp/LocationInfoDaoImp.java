package com.cy.dcts.location.dao.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.LocationCollectLastInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.LocationCollectInfoDomain;
import com.cy.dcts.common.domain.LocationCollectLastInfoDomain;
import com.cy.dcts.location.dao.ILocationInfoDao;

public class LocationInfoDaoImp extends BaseDao implements ILocationInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<LocationCollectInfoDomain> queryLocationCollectInfoByDriverIdByPage(
			Map<String, Object> queryMap) {
		try{
			return (List<LocationCollectInfoDomain>)queryForList("query_location_collectInfo_driverId_byPage",queryMap);
		}catch(Exception e) {
			logger.warn("query_location_collectInfo_driverId_byPage error.", e);
			throw new RuntimeException();
		}
	}

	public List<LocationCollectInfoDomain> queryLocationCollectInfoByDriverId(
			Map<String, Object> queryMap) {
		try{
			return (List<LocationCollectInfoDomain>)queryForList("query_location_collectInfo_driverId",queryMap);
		}catch(Exception e) {
			logger.warn("query_location_collectInfo_driverId error.", e);
			throw new RuntimeException();
		}
	}
	
	public Integer queryLocationCollectInfoByDriverIdCount(
			Map<String, Object> queryMap) {
		try{
			return (Integer) queryForObject("query_location_collectInfo_driverId_count", queryMap);
		}catch(Exception e) {
			logger.warn("query_location_collectInfo_driverId_count error.", e);
			throw new RuntimeException();
		}
	}

	public LocationCollectLastInfo queryLocationCollectLastByDriverId(
			String driverId) {
		try {
			return (LocationCollectLastInfo)this.queryForObject("query_location_collect_last_by_driverId", driverId);
		} catch (Exception e) {
			logger.warn("query_location_collect_last_by_driverId error", e);
			throw new RuntimeException();
		}
	}

	public LocationCollectLastInfoDomain queryLocationCollectLastDomainByDriverId(
			String driverId) {
		try {
			return (LocationCollectLastInfoDomain)this.queryForObject("query_location_collect_last_domain_by_driverId", driverId);
		} catch (Exception e) {
			logger.warn("query_location_collect_last_domain_by_driverId error", e);
			throw new RuntimeException();
		}
	}

}
