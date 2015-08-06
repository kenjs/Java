package com.cy.dctms.userDirverAssess.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.userDirverAssess.dao.IUserDriverAssessInfoDao;
import com.cy.dctms.userDirverAssess.service.IUserDriverAssessInfoService;

public class UserDriverAssessInfoServiceImp implements IUserDriverAssessInfoService {

	private IUserDriverAssessInfoDao userDriverAssessInfoDao;

	@Override
	public void queryUserDriverAssessInfoList(UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		userDriverAssessInfoDao.queryUserDriverAssessInfoList(userDriverAssessInfoDomain);
	}

	@Override
	public void exportUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		 userDriverAssessInfoDao.exportUserDriverAssessInfo(userDriverAssessInfoDomain);
	}

	@Override
	public UserDriverAssessInfoDomain queryUserDriverAssessInfoMxById(String id) {
		 return userDriverAssessInfoDao.queryUserDriverAssessInfoById(id);
	}

	@Override
	public void saveUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain ,String userId) {		
		userDriverAssessInfoDao.saveUserDriverAssessInfo(userDriverAssessInfoDomain,userId);
	}

	@Override
	public void deleteUserDriverAssessInfo(String id,String userId) {
		userDriverAssessInfoDao.deleteUserDriverAssessInfo(id,userId);
		
	}

	public void setUserDriverAssessInfoDao(IUserDriverAssessInfoDao userDriverAssessInfoDao) {
		this.userDriverAssessInfoDao = userDriverAssessInfoDao;
	}
}
