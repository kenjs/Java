package com.cy.dcts.webUser.service;

import com.cy.dcts.common.bo.WebUserInfo;

public interface UpdatePwdService {

	/**
	 * 修改密码
	 * haoyong
	 * @param webUserInfo
	 * @return
	 */
	public boolean updateUserPwd(WebUserInfo webUserInfo);
}
