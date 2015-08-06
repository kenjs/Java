package com.cy.dctms.driverUserAssess.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;
import com.cy.dctms.driverUserAssess.service.IDriverUserAssessInfoService;

public class DeleteDriverUserAssessInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserAssessInfoService driverUserAssessInfoService;
	private DriverUserAssessInfoDomain driverUserAssessInfoDomain;
	
	/** ɾ��˾������ҵ������Ϣ
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("delete driverUserAssessInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		driverUserAssessInfoService.deleteDriverUserAssessInfo(driverUserAssessInfoDomain.getId(),getSessionUser().getId());
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
