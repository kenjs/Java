package com.cy.dctms.userDirverAssess.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;
import com.cy.dctms.userDirverAssess.service.IUserDriverAssessInfoService;

public class QueryUserDriverAssessInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IUserDriverAssessInfoService userDriverAssessInfoService;
	private UserDriverAssessInfoDomain userDriverAssessInfoDomain;

	/** 查询企业对司机评价信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query userDriverAssessInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (userDriverAssessInfoDomain==null) {
			userDriverAssessInfoDomain = new UserDriverAssessInfoDomain();
		}
		if (userDriverAssessInfoDomain.getPageInfo()==null) {
			userDriverAssessInfoDomain.setPageInfo(new PageInfo());
		}
		userDriverAssessInfoService.queryUserDriverAssessInfoList(userDriverAssessInfoDomain);
		return SUCCESS;
	}

	public void setUserDriverAssessInfoService(IUserDriverAssessInfoService userDriverAssessInfoService) {
		this.userDriverAssessInfoService = userDriverAssessInfoService;
	}
	public UserDriverAssessInfoDomain getUserDriverAssessInfoDomain() {
		return userDriverAssessInfoDomain;
	}

	public void setUserDriverAssessInfoDomain(UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		this.userDriverAssessInfoDomain = userDriverAssessInfoDomain;
	}


}
