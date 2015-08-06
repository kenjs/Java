package com.cy.driver.service;

import com.cy.driver.domain.WebUserInfoDomain;

import java.util.Map;

public interface WebUserInfoService {
	/**
	 * 根据parentId和encoded查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public WebUserInfoDomain selectWebuserInfoByParentIdAndEncoded(Map<String,String> map) throws Exception;
}
