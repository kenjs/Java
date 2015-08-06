package com.cy.dcts.driverCargoAssess.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.DriverCargoAssessInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.driverCargoAssess.service.IDriverCargoAssessService;

public class QueryDriverCargoAssessByCargoIdAction extends BasePageAction{
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverCargoAssessService driverCargoAssessService;
	private DriverCargoAssessInfoDomain driverCargoAssessInfoDomain;
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			return LOGIN;
		}
		logger.debug("query driver cargo assess by cargoId begin, userId=[{}], companyId=[{}]",
		getSessionUser().getId(), getSessionUser().getCompanyId());
		if(driverCargoAssessInfoDomain==null){
			driverCargoAssessInfoDomain=new DriverCargoAssessInfoDomain();
		}
		if(driverCargoAssessInfoDomain.getPageInfo()==null){
			driverCargoAssessInfoDomain.setPageInfo(new PageInfo());
		}
		List<DriverCargoAssessInfoDomain> list=driverCargoAssessService.queryDriverCargoAssessByCargoId(driverCargoAssessInfoDomain);
		driverCargoAssessInfoDomain.setList(list);
		logger.debug("query driver cargo assess by cargoId success! list.size()=[{}]",list.size());
		return SUCCESS;
	}
	public IDriverCargoAssessService getDriverCargoAssessService() {
		return driverCargoAssessService;
	}
	public void setDriverCargoAssessService(
			IDriverCargoAssessService driverCargoAssessService) {
		this.driverCargoAssessService = driverCargoAssessService;
	}
	public DriverCargoAssessInfoDomain getDriverCargoAssessInfoDomain() {
		return driverCargoAssessInfoDomain;
	}
	public void setDriverCargoAssessInfoDomain(
			DriverCargoAssessInfoDomain driverCargoAssessInfoDomain) {
		this.driverCargoAssessInfoDomain = driverCargoAssessInfoDomain;
	}

}
