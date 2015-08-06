package com.cy.dctms.driverUser.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.driverUser.service.IDriverUserInfoService;

public class DeleteDriverUserInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserInfoService driverUserInfoService;
	private DriverUserInfoDomain driverUserInfoDomain;
	
	/** 删除司机信息
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("delete driverUserInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		driverUserInfoService.deleteDriverUserInfo(driverUserInfoDomain,getSessionUser().getId());
		return SUCCESS;
	}

	public void setDriverUserInfoService(IDriverUserInfoService driverUserInfoService) {
		this.driverUserInfoService = driverUserInfoService;
	}
	public DriverUserInfoDomain getDriverUserInfoDomain() {
		return driverUserInfoDomain;
	}

	public void setDriverUserInfoDomain(DriverUserInfoDomain driverUserInfoDomain) {
		this.driverUserInfoDomain = driverUserInfoDomain;
	}

}
