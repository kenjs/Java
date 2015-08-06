package com.cy.dctms.webUser.dao;

import com.cy.dctms.common.domain.WebUserInfoDomain;

public interface IWebUserInfoDao {

	/**
	 * 查询企业信息列表
	 * @author:wjl
	 */
	public void queryWebUserInfoList(WebUserInfoDomain webUserInfoDomain);
	
	/**
	 * 查询审核企业信息列表
	 * @author:wjl
	 */
	public void auditWebUserInfoList(WebUserInfoDomain webUserInfoDomain);
	
	/**
	 * 查询企业交易信息列表
	 * @author:wjl
	 */
	public void queryWebUserInfoTransactionList(WebUserInfoDomain webUserInfoDomain);

	/**
	 * 查企业信息明细根据ID
	 * @author:wjl
	 */
	public WebUserInfoDomain queryWebUserInfoById(String id);	

	/**
	 * 保存企业信息
	 * @author:wjl
	 */
	public void saveWebUserInfo(WebUserInfoDomain webUserInfoDomain,String userId);

	/**
	 * 删除企业信息
	 * @author:wjl
	 */
	public void deleteWebUserInfo(WebUserInfoDomain webUserInfoDomain,String userId);
	/**
	 * 保存企业信息
	 * @author:wjl
	 */
	public void auditWebUserInfo(WebUserInfoDomain webUserInfoDomain,String userId);
}
