package com.cy.dcts.userDriverAssess.service.imp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.bo.UserDriverAssessInfo;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.userDriverAssess.dao.IUserDriverAssessInfoDao;
import com.cy.dcts.userDriverAssess.service.IUserDriverAssessInfoService;

public class UserDriverAssessInfoServiceImp implements IUserDriverAssessInfoService{
	
	private IUserDriverAssessInfoDao userDriverAssessInfoDao; 

	public String addUserDriverAssessInfo(UserDriverAssessInfo userDriverAssessInfo) {
		return userDriverAssessInfoDao.addUserDriverAssessInfo(userDriverAssessInfo);
	}
	
	public UserDriverAssessInfoDomain queryUserDriverAssesByTradeId(
			String transactionId) {
		return userDriverAssessInfoDao.qyeryUserDriverAssesByTradeId(transactionId);
	}
	
	public UserDriverAssessInfo queryUserDriverAssesInfoByTradeId(
			String transactionId) {
		return userDriverAssessInfoDao.qyeryUserDriverAssesInfoByTradeId(transactionId);
	}

	public IUserDriverAssessInfoDao getUserDriverAssessInfoDao() {
		return userDriverAssessInfoDao;
	}

	public List<UserDriverAssessInfoDomain> queryUserDriverAssessInfoDomainPage(UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<UserDriverAssessInfoDomain> list = new ArrayList<UserDriverAssessInfoDomain>();
		if(userDriverAssessInfoDomain != null) {
			if(StringUtils.isNotEmpty(userDriverAssessInfoDomain.getDriverId())) {
				queryMap.put("driverId", userDriverAssessInfoDomain.getDriverId());
			}
			if(userDriverAssessInfoDomain.getPageInfo() != null) {
				queryMap.put("pageSize", userDriverAssessInfoDomain.getPageInfo().getPageSize());
				queryMap.put("curPage", userDriverAssessInfoDomain.getPageInfo().getPageSize()*userDriverAssessInfoDomain.getPageInfo().getCurPage());
			}
			userDriverAssessInfoDomain.getPageInfo().setTotalRecords(userDriverAssessInfoDao.queryUserDriverAssessInfoDomainPageCount(queryMap));
			list = userDriverAssessInfoDao.queryUserDriverAssessInfoDomainPage(queryMap);
		}
		return list;
	}
	public void setUserDriverAssessInfoDao(
			IUserDriverAssessInfoDao userDriverAssessInfoDao) {
		this.userDriverAssessInfoDao = userDriverAssessInfoDao;
	}

	
	public Integer queryUserDriverAssessInfoDomainCount(String driverId,String tradeEvaluateScore) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("driverId", driverId);
		queryMap.put("tradeEvaluateScore", tradeEvaluateScore);
		return userDriverAssessInfoDao.queryUserDriverAssessInfoDomainCount(queryMap);
	}


}
