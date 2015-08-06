package com.cy.dcts.webUser.service;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;

public interface AccountManagementService {

	/**
	 * 初始化用户信息
	 * @param map
	 * @return
	 */
	public WebUserInfo initUserInfor(String code);
	
	/**
	 * 初始化庄户管理页面公司信息
	 * @author haoyong
	 * @param id
	 * @param companyId
	 * @return
	 */
	public WebUserInfoDamain initAccoutInfo(String id,String companyId) ;
	
	/**
	 * 完善用户信息
	 * @author haoyong
	 * @date 2014-5-22
	 * @param domain
	 */
	public int updateAccountInfo(WebUserInfoDamain domain);

}
