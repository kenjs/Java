package com.cy.dctms.webUser.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.WebUserInfoDomain;
import com.cy.dctms.webUser.service.IWebUserInfoService;

public class WebUserTotalDataListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IWebUserInfoService webUserInfoService;
	private WebUserInfoDomain webUserInfoDomain;

	/** 查询企业信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query webUserInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (webUserInfoDomain==null) {
			webUserInfoDomain = new WebUserInfoDomain();
		}
		if (webUserInfoDomain.getPageInfo()==null) {
			webUserInfoDomain.setPageInfo(new PageInfo());
		}
		webUserInfoService.queryWebUserInfoList(webUserInfoDomain);
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
