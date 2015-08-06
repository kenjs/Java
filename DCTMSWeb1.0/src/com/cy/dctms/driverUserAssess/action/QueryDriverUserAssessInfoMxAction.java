package com.cy.dctms.driverUserAssess.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;
import com.cy.dctms.driverUserAssess.service.IDriverUserAssessInfoService;

public class QueryDriverUserAssessInfoMxAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserAssessInfoService driverUserAssessInfoService;
	private DriverUserAssessInfoDomain driverUserAssessInfoDomain;

	/** 查询司机对企业评价信息明细
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query driverUserAssessInfo Mx start");
		if(getSessionUser()==null){
			return "loginMx";
		}
		if(!driverUserAssessInfoDomain.getId().equals("0")){
			driverUserAssessInfoDomain = driverUserAssessInfoService.queryDriverUserAssessInfoMxById(driverUserAssessInfoDomain.getId());
		}else {
			driverUserAssessInfoDomain = new DriverUserAssessInfoDomain();
			driverUserAssessInfoDomain.setId("0");
		}
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
