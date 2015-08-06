package com.cy.dcts.webUser.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.dcts.common.domain.DriverUserAssessInfoDomain;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.webUser.dao.IUserDriverAssessInfoDao;
import com.cy.dcts.webUser.service.IEvaluationManagementService;

public class EvaluationManagementServiceImpl implements
		IEvaluationManagementService {
	
	private IUserDriverAssessInfoDao userDriverAssessInfoLoadDao;
	
	
	
	public List<UserDriverAssessInfoDomain> queryUserDriverAssessList(
			UserDriverAssessInfoDomain userDriverAssessInfoDomain, String userId) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		if(userDriverAssessInfoDomain!=null){
			queryMap.put("tradeEvaluateScore", userDriverAssessInfoDomain.getTradeEvaluateScore());
			queryMap.put("userId", userId);
			if(userDriverAssessInfoDomain.getPageInfo()!=null){
				int pageSizes=userDriverAssessInfoDomain.getPageInfo().getPageSize();
				int curPages=userDriverAssessInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes);
				userDriverAssessInfoDomain.getPageInfo().setTotalRecords(userDriverAssessInfoLoadDao.queryUserDriverAssessInfoCount(queryMap));
				return userDriverAssessInfoLoadDao.queryUserDriverAssessInfoByPage(queryMap);
			}
		}
		return userDriverAssessInfoLoadDao.queryUserDriverAssessInfo(queryMap);
	}

	public List<DriverUserAssessInfoDomain> queryDriverUserAssessList(
			DriverUserAssessInfoDomain driverUserAssessInfoDomain,String userId) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		if(driverUserAssessInfoDomain!=null){
			queryMap.put("assessEvaluateScore", driverUserAssessInfoDomain.getAssessEvaluateScore());
			queryMap.put("userId", userId);
			if(driverUserAssessInfoDomain.getPageInfo()!=null){
				int pageSizes=driverUserAssessInfoDomain.getPageInfo().getPageSize();
				int curPages=driverUserAssessInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes);
				driverUserAssessInfoDomain.getPageInfo().setTotalRecords(userDriverAssessInfoLoadDao.queryDriverUserAssessInfoCount(queryMap));
				return userDriverAssessInfoLoadDao.queryDriverUserAssessInfoBypage(queryMap);
			}
		}
		return userDriverAssessInfoLoadDao.queryDriverUserAssessInfo(queryMap);
	}
	
	public DriverUserAssessInfoDomain queryDriverUserAssessCountByAssessScore(
			String userId) {
		return userDriverAssessInfoLoadDao.queryDriverUserAssessCountByAssessScore(userId);
	}
	
	public void setUserDriverAssessInfoLoadDao(
			IUserDriverAssessInfoDao userDriverAssessInfoLoadDao) {
		this.userDriverAssessInfoLoadDao = userDriverAssessInfoLoadDao;
	}

	
}
