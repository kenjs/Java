package com.cy.dctms.driverUserAssess.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;
import com.cy.dctms.driverUserAssess.service.IDriverUserAssessInfoService;

public class SaveDriverUserAssessInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserAssessInfoService driverUserAssessInfoService;
	private DriverUserAssessInfoDomain driverUserAssessInfoDomain;

	/** 保存司机对企业评价信息
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("save driverUserAssessInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		driverUserAssessInfoService.saveDriverUserAssessInfo(driverUserAssessInfoDomain,getSessionUser().getId());
		return SUCCESS;
	}

	public void setDriverUserAssessInfoService(IDriverUserAssessInfoService driverUserAssessInfoService) {
		this.driverUserAssessInfoService = driverUserAssessInfoService;
	}
	public DriverUserAssessInfoDomain getDriverUserAssessInfoDomain() {
		return driverUserAssessInfoDomain;
	}
	public void setDriverUserAssessInfoDomain(DriverUserAssessInfoDomain driverUserAssessInfoDomain) {
		this.driverUserAssessInfoDomain = driverUserAssessInfoDomain;
	}


}
