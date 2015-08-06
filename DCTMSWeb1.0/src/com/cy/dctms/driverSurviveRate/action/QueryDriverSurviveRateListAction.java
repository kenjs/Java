package com.cy.dctms.driverSurviveRate.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverSurviveRateDomain;
import com.cy.dctms.driverSurviveRate.service.IDriverSurviveRateService;

public class QueryDriverSurviveRateListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverSurviveRateService driverSurviveRateService;
	private DriverSurviveRateDomain driverSurviveRateDomain;

	/** 查询查询存活率信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query driverSurviveRate list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (driverSurviveRateDomain==null) {
			driverSurviveRateDomain = new DriverSurviveRateDomain();
		}
		if (driverSurviveRateDomain.getPageInfo()==null) {
			driverSurviveRateDomain.setPageInfo(new PageInfo());
		}
		driverSurviveRateService.queryDriverSurviveRateList(driverSurviveRateDomain);
		return SUCCESS;
	}

	public void setDriverSurviveRateService(IDriverSurviveRateService driverSurviveRateService) {
		this.driverSurviveRateService = driverSurviveRateService;
	}
	public DriverSurviveRateDomain getDriverSurviveRateDomain() {
		return driverSurviveRateDomain;
	}

	public void setDriverSurviveRateDomain(DriverSurviveRateDomain driverSurviveRateDomain) {
		this.driverSurviveRateDomain = driverSurviveRateDomain;
	}


}
