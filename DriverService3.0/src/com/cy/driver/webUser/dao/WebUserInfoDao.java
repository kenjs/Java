package com.cy.driver.webUser.dao;

import java.util.Map;

import com.cy.driver.webUser.domain.WebUserInfoDomain;

public interface WebUserInfoDao {

	/**
	 * 根据parentId和encoded查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public WebUserInfoDomain selectWebuserInfoByParentIdAndEncoded(Map<String,String> map) throws Exception;
}
