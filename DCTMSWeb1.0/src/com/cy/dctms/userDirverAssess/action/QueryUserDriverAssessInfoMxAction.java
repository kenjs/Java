package com.cy.dctms.userDirverAssess.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;
import com.cy.dctms.userDirverAssess.service.IUserDriverAssessInfoService;

public class QueryUserDriverAssessInfoMxAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IUserDriverAssessInfoService userDriverAssessInfoService;
	private UserDriverAssessInfoDomain userDriverAssessInfoDomain;

	/** 查询企业对司机评价信息明细
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query userDriverAssessInfo Mx start");
		if(getSessionUser()==null){
			return "loginMx";
		}
		if(!userDriverAssessInfoDomain.getId().equals("0")){
			userDriverAssessInfoDomain = userDriverAssessInfoService.queryUserDriverAssessInfoMxById(userDriverAssessInfoDomain.getId());
		}else {
			userDriverAssessInfoDomain = new UserDriverAssessInfoDomain();
			userDriverAssessInfoDomain.setId("0");
		}
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
