package com.cy.dctms.webUser.service;

import com.cy.dctms.common.domain.WebUserInfoDomain;

public interface IWebUserInfoService {
	
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
	public WebUserInfoDomain queryWebUserInfoMxById(String id);

	/**
	 * 保存企业信息
	 * @author:wjl
	 */
	public void saveWebUserInfo(WebUserInfoDomain webUserInfoDomain ,String userId);

	/**
	 * 删除企业信息
	 * @author:wjl
	 */
	public void deleteWebUserInfo(WebUserInfoDomain webUserInfoDomain,String userId);
	/**
	 * 审核企业信息
	 * @author:wjl
	 */
	public void auditWebUserInfo(WebUserInfoDomain webUserInfoDomain ,String userId);

}
	
