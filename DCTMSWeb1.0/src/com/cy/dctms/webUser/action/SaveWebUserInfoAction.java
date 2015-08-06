package com.cy.dctms.webUser.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.WebUserInfoDomain;
import com.cy.dctms.webUser.service.IWebUserInfoService;

public class SaveWebUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IWebUserInfoService webUserInfoService;
	private WebUserInfoDomain webUserInfoDomain;

	/** ������ҵ��Ϣ
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("save webUserInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		webUserInfoService.saveWebUserInfo(webUserInfoDomain,getSessionUser().getId());
		return SUCCESS;
	}

	public void setWebUserInfoService(IWebUserInfoService webUserInfoService) {
		this.webUserInfoService = webUserInfoService;
	}
	public WebUserInfoDomain getWebUserInfoDomain() {
		return webUserInfoDomain;
	}
	public void setWebUserInfoDomain(WebUserInfoDomain webUserInfoDomain) {
		this.webUserInfoDomain = webUserInfoDomain;
	}


}
