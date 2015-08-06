package com.cy.driver.user.service.impl;

import java.util.Map;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.driver.user.dao.LoginUserInfoDao;
import com.cy.driver.user.domain.DriverUserInfoDomain;
import com.cy.driver.user.service.LoginUserInfoService;

public class LoginUserInfoServiceImpl implements LoginUserInfoService {
	
	private LoginUserInfoDao loginUserInfoDao;
	
	public boolean checkUserAccountExist(String code) {
		return loginUserInfoDao.checkUserAccountExist(code) == 1 ? true:false;
	}

	public DriverUserInfoDomain checkLogin(String code, String pwd) {
		return loginUserInfoDao.checkLogin(code, pwd);
	}

	public void setLoginUserInfoDao(LoginUserInfoDao loginUserInfoDao) {
		this.loginUserInfoDao = loginUserInfoDao;
	}

	public int addDriverUserInfo(DriverUserInfoBo bo) {		
		return loginUserInfoDao.addDriverUserInfo(bo);
	}

	public int updateBaiduPushId(Map<String, Object> map) {
		return loginUserInfoDao.updateBaiduPushId(map);
	}

	@Override
	public boolean chkFreezeAccount(String code) throws Exception {
		return loginUserInfoDao.chkFreezeAccount(code) == 1 ? true : false;
	}
	
}
