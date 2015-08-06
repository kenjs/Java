package com.cy.driver.user.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.user.dao.LoginUserInfoDao;
import com.cy.driver.user.domain.DriverUserInfoDomain;

public class LoginUserInfoDaoImpl extends BaseDao implements LoginUserInfoDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public int checkUserAccountExist(String code) {
		try {
			return (Integer) queryForObject("iBatisSelectDriverInfoByCode", code);
		} catch (SQLException e) {
			logger.warn("query dirver user information by code happen error", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public DriverUserInfoDomain checkLogin(String code, String pwd) {
		try {
			Map<String,Object> map = new HashMap<String, Object>(); 
			map.put("code", code);
			map.put("password", pwd);
			return (DriverUserInfoDomain) queryForObject("iBatisSelectDriverInfoByCodeAndPwd", map);
		} catch (SQLException e) {
			logger.warn("query dirver user information by code and password happen error", e);
			throw new RuntimeException();
		}
	}

	public int addDriverUserInfo(DriverUserInfoBo bo) {
		int i = 0;
		try {
			i = addObject("iBatisInsertDriverUserInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int checkUser(String id) {
		int i = -1;
		try {
			Object obj = queryForObject("iBatisSelectDriverUserById", id);			
			if(obj != null) {
				i = (Integer) obj;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return i;
	}

	public int updateBaiduPushId(Map<String, Object> map) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateBaiduPushId", map);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int chkFreezeAccount(String code) throws Exception {
		return (Integer) queryForObject("iBatisSelectFreezeUser", code);
	}

}
