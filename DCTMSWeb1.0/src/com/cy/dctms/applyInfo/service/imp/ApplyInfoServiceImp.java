package com.cy.dctms.applyInfo.service.imp;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dctms.applyInfo.dao.IApplyInfoDao;
import com.cy.dctms.applyInfo.service.IApplyInfoService;
import com.cy.dctms.common.domain.ApplyInfoDomain;

public class ApplyInfoServiceImp implements IApplyInfoService {

	private IApplyInfoDao applyInfoDao;

	@Override
	public void queryApplyInfoList(ApplyInfoDomain applyInfoDomain) {
		applyInfoDao.queryApplyInfoList(applyInfoDomain);
	}

	@Override
	public ApplyInfoDomain queryApplyInfoMxById(String id) {
		 return applyInfoDao.queryApplyInfoById(id);
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public void saveApplyInfo(ApplyInfoDomain applyInfoDomain ,String userId) {		
		applyInfoDao.saveApplyInfo(applyInfoDomain,userId);
	}

	public void setApplyInfoDao(IApplyInfoDao applyInfoDao) {
		this.applyInfoDao = applyInfoDao;
	}
}
