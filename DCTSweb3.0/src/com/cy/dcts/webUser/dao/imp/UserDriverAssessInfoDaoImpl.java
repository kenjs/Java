package com.cy.dcts.webUser.dao.imp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.DriverUserAssessInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.webUser.dao.IUserDriverAssessInfoDao;

public class UserDriverAssessInfoDaoImpl extends BaseDao implements IUserDriverAssessInfoDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public List<UserDriverAssessInfoDomain> queryUserDriverAssessInfo(
			Map<String, Object> queryMap) {
		try{
			return (List<UserDriverAssessInfoDomain>)queryForList("query_user_driver_assess_info",queryMap);
		}catch(Exception e) {
			logger.warn("query_user_driver_assess_info error.", e);
			throw new RuntimeException();
		}
	}

	public List<UserDriverAssessInfoDomain> queryUserDriverAssessInfoByPage(
			Map<String, Object> queryMap) {
		try{
			return (List<UserDriverAssessInfoDomain>)queryForList("query_user_driver_assess_info_byPage",queryMap);
		}catch(Exception e) {
			logger.warn("query_user_driver_assess_info_byPage error.", e);
			throw new RuntimeException();
		}
	}

	public Integer queryUserDriverAssessInfoCount(Map<String, Object> queryMap) {
			try{
				return (Integer) queryForObject("query_user_driver_assess_info_count", queryMap);
			}catch(Exception e) {
				logger.warn("query_user_driver_assess_info_count error.", e);
				throw new RuntimeException();
			}
		
	}

	public List<DriverUserAssessInfoDomain> queryDriverUserAssessInfo(
			Map<String, Object> queryMap) {
			try{
				return (List<DriverUserAssessInfoDomain>)queryForList("query_driver_user_assess_info",queryMap);
			}catch(Exception e) {
				logger.warn("query_driver_user_assess_info error.", e);
				throw new RuntimeException();
			}
	}

	public List<DriverUserAssessInfoDomain> queryDriverUserAssessInfoBypage(
			Map<String, Object> queryMap) {
		try{
			return (List<DriverUserAssessInfoDomain>)queryForList("query_driver_user_assess_info_byPage",queryMap);
		}catch(Exception e) {
			logger.warn("query_driver_user_assess_info_byPage error.", e);
			throw new RuntimeException();
		}
	}

	public Integer queryDriverUserAssessInfoCount(Map<String, Object> queryMap) {
		try{
			return (Integer) queryForObject("query_driver_user_assess_info_count", queryMap);
		}catch(Exception e) {
			logger.warn("query_driver_user_assess_info_count error.", e);
			throw new RuntimeException();
		}
	}
	
	public DriverUserAssessInfoDomain queryDriverUserAssessCountByAssessScore(
			String userId) {
		try{
			return (DriverUserAssessInfoDomain) queryForObject("query_driver_user_assess_count_byAssessScore", userId);
		}catch(Exception e) {
			logger.warn("query_driver_user_assess_count_byAssessScore error.", e);
			throw new RuntimeException();
		}
	}
	
	
}
