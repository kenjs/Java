package com.cy.driver.webUser.service;

import java.util.Map;

import com.cy.driver.webUser.domain.WebUserInfoDomain;

public interface WebUserInfoService {
	/**
	 * 根据parentId和encoded查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public WebUserInfoDomain selectWebuserInfoByParentIdAndEncoded(Map<String,String> map) throws Exception;
}
