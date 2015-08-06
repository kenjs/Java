package com.cy.dcts.userDriverAssess.dao.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.userDriverAssess.dao.IUserDriverAssessInfoDao;

public class UserDriverAssessInfoDaoImp extends BaseDao implements IUserDriverAssessInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public String addUserDriverAssessInfo(UserDriverAssessInfo userDriverAssessInfo) {
		try {
			return this.addObjectKeyString("insert_user_driver_assess_info", userDriverAssessInfo);
		} catch (Exception e) {
			logger.warn("insert_user_driver_assess_info error",e);
			throw new RuntimeException();
		}
	}
	
	public List<UserDriverAssessInfoDomain> queryUserDriverAssessInfoDomainPage(Map<String, Object> queryMap) {
		try{
			return (List<UserDriverAssessInfoDomain>)this.queryForList("query_userdriver_assess_info_domain_Page",queryMap);
		}catch(Exception e) {
			logger.warn("query_userdriver_assess_info_domain_Page error",e);
			throw new RuntimeException();
		}
	}
	
	public Integer queryUserDriverAssessInfoDomainPageCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_userdriver_assess_info_domain_Page_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_userdriver_assess_info_domain_Page_count error",e);
			throw new RuntimeException();
		}
	}

	
	public UserDriverAssessInfoDomain qyeryUserDriverAssesByTradeId(
			String transactionId) {
		try {
			return (UserDriverAssessInfoDomain)this.queryForObject("query_user_driver_assess_by_transactionId",transactionId);
		} catch (Exception e) {
			logger.debug("query_user_driver_assess_by_transactionId error",e);
			throw new RuntimeException();
		}
	}
	public UserDriverAssessInfo qyeryUserDriverAssesInfoByTradeId(
			String transactionId) {
		try {
			return (UserDriverAssessInfo)this.queryForObject("query_user_driver_assess_info_by_trandeId",transactionId);
		} catch (Exception e) {
			logger.debug("query_user_driver_assess_info_by_trandeId error",e);
			throw new RuntimeException();
		}
	}

	public Integer queryUserDriverAssessInfoDomainCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_userdriver_assess_info_domain_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_userdriver_assess_info_domain_count error",e);
			throw new RuntimeException();
		}
	}

}
