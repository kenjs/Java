package com.cy.driver.webUser.service.impl;

import java.util.Map;

import com.cy.driver.webUser.dao.WebUserInfoDao;
import com.cy.driver.webUser.domain.WebUserInfoDomain;
import com.cy.driver.webUser.service.WebUserInfoService;

public class WebUserInfoServiceImpl implements WebUserInfoService {
	
	private WebUserInfoDao webUserInfoDao;

	@Override
	public WebUserInfoDomain selectWebuserInfoByParentIdAndEncoded(
			Map<String, String> map) throws Exception {
		return webUserInfoDao.selectWebuserInfoByParentIdAndEncoded(map);
	}

	public void setWebUserInfoDao(WebUserInfoDao webUserInfoDao) {
		this.webUserInfoDao = webUserInfoDao;
	}

}
