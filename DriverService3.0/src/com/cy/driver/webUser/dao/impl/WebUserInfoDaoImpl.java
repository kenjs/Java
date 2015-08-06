package com.cy.driver.webUser.dao.impl;

import java.util.Map;

import com.cy.common.dao.BaseDao;
import com.cy.driver.webUser.dao.WebUserInfoDao;
import com.cy.driver.webUser.domain.WebUserInfoDomain;

public class WebUserInfoDaoImpl extends BaseDao implements WebUserInfoDao {

	@Override
	public WebUserInfoDomain selectWebuserInfoByParentIdAndEncoded(
			Map<String, String> map) throws Exception {
		return (WebUserInfoDomain) queryForObject("iBatisSelectWebUserInfoByParentIdAndEncoded", map);
	}

}
