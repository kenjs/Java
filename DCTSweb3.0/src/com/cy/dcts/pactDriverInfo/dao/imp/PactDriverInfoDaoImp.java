package com.cy.dcts.pactDriverInfo.dao.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.PactDriverInfoDomain;
import com.cy.dcts.pactDriverInfo.dao.IPactDriverInfoDao;

public class PactDriverInfoDaoImp extends BaseDao implements IPactDriverInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String addPactDriverInfo(PactDriverInfoDomain pactDriverInfoDomain) {
		try {
			return addObjectKeyString("insert_pact_driver_info", pactDriverInfoDomain);
		} catch (Exception e) {
			logger.warn("insert_pact_driver_info error", e);
			throw new RuntimeException();
		}
	}

	public boolean deletePactDriverInfo(Map<String, Object> modifyMap) {
		try {
			return this.saveObject("delete_pact_driver_info", modifyMap)==1;
		} catch (Exception e) {
			logger.warn("delete_pact_driver_info",e);
			throw new RuntimeException();
		}
	}
	
	public List<PactDriverInfoDomain> queryPactDriverInfo(
			Map<String, Object> queryMap) {
		try {
			return (List<PactDriverInfoDomain>)this.queryForList("query_pact_driver_info",queryMap);
		} catch (Exception e) {
			logger.debug("query_pact_driver_info error",e);
			throw new RuntimeException();
		}
	}

	public List<PactDriverInfoDomain> queryPactDriverInfoByPage(
			Map<String, Object> queryMap) {
		try {
			return (List<PactDriverInfoDomain>)this.queryForList("query_pact_driver_info_byPage",queryMap);
		} catch (Exception e) {
			logger.debug("query_pact_driver_info_byPage error",e);
			throw new RuntimeException();
		}
	}

	public Integer queryPactDriverInfoCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_pact_driver_info_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_pact_driver_info_count error",e);
			throw new RuntimeException();
		}
	}
}
