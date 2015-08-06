package com.cy.dcts.pactDriverInfo.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.PactDriverInfoDomain;
import com.cy.dcts.pactDriverInfo.dao.IPactDriverInfoDao;
import com.cy.dcts.pactDriverInfo.service.IPactDriverInfoService;

public class PactDriverInfoServiceImp implements IPactDriverInfoService{
    private IPactDriverInfoDao pactDriverInfoDao;
	public String addPactDriverInfo(PactDriverInfoDomain pactDriverInfoDomain) {
		return pactDriverInfoDao.addPactDriverInfo(pactDriverInfoDomain);
	}

	public boolean deletePactDriverInfo(String id) {
		Map<String, Object> modifyMap = new HashMap<String, Object>();
		modifyMap.put("deletedFlag", Constants.DELETED_FLAG_TRUE);
		modifyMap.put("id", id);
		return pactDriverInfoDao.deletePactDriverInfo(modifyMap);
	} 

	public List<PactDriverInfoDomain> queryPactDriverInfo(
			PactDriverInfoDomain pactDriverInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(pactDriverInfoDomain!=null){
			queryMap.put("userId", pactDriverInfoDomain.getUserId());
			queryMap.put("deletedFlag", Constants.DELETED_FLAG_FALSE);
			queryMap.put("code", pactDriverInfoDomain.getCode());
			queryMap.put("name", pactDriverInfoDomain.getName());
			queryMap.put("carNumber", pactDriverInfoDomain.getCarNumber());
			queryMap.put("pactStartTime", pactDriverInfoDomain.getPactStartTime());
			queryMap.put("pactEndTime", pactDriverInfoDomain.getPactEndTime());
			queryMap.put("startProvince", pactDriverInfoDomain.getStartProvince());
			queryMap.put("startCity", pactDriverInfoDomain.getStartCity());
			queryMap.put("endProvince", pactDriverInfoDomain.getEndProvince());
			queryMap.put("endCity", pactDriverInfoDomain.getEndCity());
		 if(pactDriverInfoDomain.getPageInfo()!=null){
			 pactDriverInfoDomain.getPageInfo().setPageSize(10);//在PageInfo类中计算总页数
			    int pageSizes=10;
				int curPages=pactDriverInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes);
				pactDriverInfoDomain.getPageInfo().setTotalRecords(pactDriverInfoDao.queryPactDriverInfoCount(queryMap));
			    return pactDriverInfoDao.queryPactDriverInfoByPage(queryMap);
		 }
		}
		return pactDriverInfoDao.queryPactDriverInfo(queryMap);
	}
	
	public IPactDriverInfoDao getPactDriverInfoDao() {
		return pactDriverInfoDao;
	}

	public void setPactDriverInfoDao(IPactDriverInfoDao pactDriverInfoDao) {
		this.pactDriverInfoDao = pactDriverInfoDao;
	}

	

}
