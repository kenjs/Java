package com.cy.dctms.driverUser.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.DriverUserInfoDomain;
import com.cy.dctms.driverUser.service.IDriverUserInfoService;

public class QueryDriverUserInfoMxAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IDriverUserInfoService driverUserInfoService;
	private DriverUserInfoDomain driverUserInfoDomain;

	/** 查询司机信息明细
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query driverUserInfo Mx start");
		if(getSessionUser()==null){
			return "loginMx";
		}
		String deleteOrModifyFlag = driverUserInfoDomain.getDeleteOrModifyFlag();
		driverUserInfoDomain = driverUserInfoService.queryDriverUserInfoMxById(driverUserInfoDomain.getId());
		if ("0".equals(deleteOrModifyFlag)) {
			driverUserInfoDomain.setDeleteOrModifyFlag("0");
		}
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
