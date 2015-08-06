package com.cy.dctms.funRelation.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cy.dctms.common.domain.FunRealationInfoDomain;
import com.cy.dctms.funRelation.dao.IFunRealationInfoDao;
import com.cy.dctms.funRelation.service.IFunRealationInfoService;

public class FunRealationInfoServiceImp implements IFunRealationInfoService {

	private IFunRealationInfoDao funRealationInfoDao;

	@Override
	public List<FunRealationInfoDomain> queryFunRealationInfoList(FunRealationInfoDomain funRealationInfoDomain,String userId) {
		return funRealationInfoDao.queryFunRealationInfoByPage(funRealationInfoDomain,userId);
	}
	@Override
	public void saveFunRealationInfo(FunRealationInfoDomain funRealationInfoDomain,String userId) {
		funRealationInfoDao.saveFunRealationInfo(funRealationInfoDomain,userId);
	}
	public void setFunRealationInfoDao(IFunRealationInfoDao funRealationInfoDao) {
		this.funRealationInfoDao = funRealationInfoDao;
	}
}
