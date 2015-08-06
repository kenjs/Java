package com.cy.dcts.webUser.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.webUser.service.AccountManagementService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
/**
 * 我的快到--账户管理
 * @date 2014-5-21
 * @author haoyong
 *
 */
public class AccountManagementAction extends BasePageAction{

	private static final long serialVersionUID = 669803459457232348L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private AccountManagementService accountManagementService;
	private IQueryWebUserInfoService queryWebUserInfoService;
	private WebUserInfo userInfoDomain;
	private WebUserInfoDamain domain;
	@Override
	protected String execMethod() throws Exception {
		if(this.getSessionUser() == null){
		    logger.debug("用户未登录.");
		    return LOGIN;
		}
		try {
			
		String menuAId="";
		if(domain!=null){
			menuAId=domain.getMenuAId();
		}
		userInfoDomain = queryWebUserInfoService.queryWebUserInfoById(getSessionUser().getId());
		domain = accountManagementService.initAccoutInfo(getSessionUser().getId(), getSessionUser().getCompanyId());
		domain.setMenuAId(menuAId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public WebUserInfo getUserInfoDomain() {
		return userInfoDomain;
	}

	public void setUserInfoDomain(WebUserInfo userInfoDomain) {
		this.userInfoDomain = userInfoDomain;
	}

	public WebUserInfoDamain getDomain() {
		return domain;
	}

	public void setDomain(WebUserInfoDamain domain) {
		this.domain = domain;
	}

	public void setAccountManagementService(
			AccountManagementService accountManagementService) {
		this.accountManagementService = accountManagementService;
	}

	public void setQueryWebUserInfoService(
			IQueryWebUserInfoService queryWebUserInfoService) {
		this.queryWebUserInfoService = queryWebUserInfoService;
	}

	public AccountManagementService getAccountManagementService() {
		return accountManagementService;
	}
	
}
