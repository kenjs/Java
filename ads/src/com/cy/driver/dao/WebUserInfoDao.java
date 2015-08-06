package com.cy.driver.dao;

import com.cy.driver.domain.WebUserInfoDomain;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository("webUserInfoDao")
public interface WebUserInfoDao {

	/**
	 * 根据parentId和encoded查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public WebUserInfoDomain selectWebuserInfoByParentIdAndEncoded(Map<String,String> map) throws Exception;
}
