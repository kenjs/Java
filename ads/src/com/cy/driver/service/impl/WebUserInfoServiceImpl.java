package com.cy.driver.service.impl;

import com.cy.driver.dao.WebUserInfoDao;
import com.cy.driver.domain.WebUserInfoDomain;
import com.cy.driver.service.WebUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
@Service("webUserInfoService")
public class WebUserInfoServiceImpl implements WebUserInfoService {

    @Resource
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
