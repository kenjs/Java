package com.cy.dcts.driverCar.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.driverCar.service.IDriverUserCarInfoService;

/**
 * 根据司机Id查看详细页面
 * @author nxj
 *
 */
public class OpenDriverDetailedAction  extends BasePageAction {

	private static final long serialVersionUID = -2285899537224162777L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IDriverUserCarInfoService driverUserCarInfoService;

	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if (getSessionUser() == null) {
			return LOGIN;
		}
		DriverUserInfoDomain  driverUserInfoDomain = getDriverUserInfoDomain();
		//判断id不能为空
		if(StringUtils.isEmpty(driverUserInfoDomain.getId())) {
			return ERROR;
		}
		driverUserInfoDomain = driverUserCarInfoService.queryDriverUserInfoDomainById(driverUserInfoDomain.getId());
		this.request.setAttribute("driverUserInfoDomain",driverUserInfoDomain);
		return SUCCESS;
	}
	

	private DriverUserInfoDomain getDriverUserInfoDomain() {
		DriverUserInfoDomain driverUserInfoDomain = new DriverUserInfoDomain();
		if(StringUtils.isNotEmpty(this.request.getParameter("driverId"))) { 
			driverUserInfoDomain.setId(this.request.getParameter("driverId"));
		}
		return  driverUserInfoDomain;
	}
	
	public void setDriverUserCarInfoService(
			IDriverUserCarInfoService driverUserCarInfoService) {
		this.driverUserCarInfoService = driverUserCarInfoService;
	}
}
