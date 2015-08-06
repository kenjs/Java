package com.cy.dcts.webUser.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.webUser.service.AccountManagementService;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;
/**
 * 账户资料修改
 * @author haoyong
 *
 */
public class AccountManagementUpdateAction extends BaseJsonAction{
	
	private static final long serialVersionUID = -6683614086012460645L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private AccountManagementService accountManagementService;
	private IQueryWebUserInfoService queryWebUserInfoService;
	private WebUserInfoDamain domain;
	
	@Override
	protected void execMethod() throws Exception {
		Object obj =   request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
		
		if(obj == null) {
			logger.info("用户没有登录，没有权利修改信息.");
			sendResponseToJson("-1", "");
			return;
		}
		
		WebUserInfo info = (WebUserInfo) obj;
		domain.setId(info.getId());
		domain.setCompanyId(info.getCompanyId());
		int i = accountManagementService.updateAccountInfo(domain);
		if(i != 1) {
			sendResponseToJson("0", "");
			return;
		}
		info = queryWebUserInfoService.queryWebUserInfoById(info.getId());
		request.getSession().setAttribute(Constants.SESSION_LOGIN_USER, info);
		sendResponseToJson("1", "");
		logger.info("修改用户信息成功.");
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

}
