package com.cy.dctms.driverUserAssess.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;
import com.cy.dctms.driverUserAssess.service.IDriverUserAssessInfoService;

public class QueryDriverUserAssessInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserAssessInfoService driverUserAssessInfoService;
	private DriverUserAssessInfoDomain driverUserAssessInfoDomain;

	/** 查询司机对企业评价信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query driverUserAssessInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (driverUserAssessInfoDomain==null) {
			driverUserAssessInfoDomain = new DriverUserAssessInfoDomain();
		}
		if (driverUserAssessInfoDomain.getPageInfo()==null) {
			driverUserAssessInfoDomain.setPageInfo(new PageInfo());
		}
		driverUserAssessInfoService.queryDriverUserAssessInfoList(driverUserAssessInfoDomain);
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
