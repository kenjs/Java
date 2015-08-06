package com.cy.swp.dao;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.WebUserInfo;

@Repository("webUserInfoDao")
public interface WebUserInfoDao {
	/**
	 * 根据用户手机号查询
	 * @param mobilephone 用户手机号
	 * @return
	 */
	WebUserInfo queryWebUserInfoByMobilephone(String mobilephone);
}
