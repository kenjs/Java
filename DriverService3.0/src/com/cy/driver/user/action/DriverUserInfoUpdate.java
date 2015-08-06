package com.cy.driver.user.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverUserInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;

public class DriverUserInfoUpdate extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			DriverUserInfoBo bo = new DriverUserInfoBo();
			String id = request.getParameter("driverId");
			String mobileBrand = request.getParameter("mobileBrand");
			String operatingSystemVersionNnumber = request.getParameter("operatingSystemVersionNnumber");
			String mobilePhoneModel = request.getParameter("mobilePhoneModel");
			String noIimei = request.getParameter("noIimei");
			if(StringUtils.isBlank(id)){
				sendResponseToJson("-9", "用户不存在.");
				logger.info("用户不存在.");
				return ERROR;
			} else {
				bo.setId(Integer.parseInt(id));
			}
			int accFlag = operationLogService.checkUser(id);
			if(accFlag == 1) {
				logger.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				logger.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			
			bo.setId(Integer.parseInt(id));
			bo.setMobileBrand(mobileBrand);
			bo.setOperatingSystemVersionNnumber(operatingSystemVersionNnumber);
			bo.setMobilePhoneModel(mobilePhoneModel);
			bo.setNoIimei(noIimei);
			
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
				sendResponseToJson("1", "修改信息成功.");
				logger.info("修改信息成功");
			} else {
				sendResponseToJson("0", "修改信息失败.");
				logger.info("修改信息失败.");
			}
		} catch (Exception e) {
			try {
				sendResponseToJson("-8", e.getMessage());
				logger.error(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
		return SUCCESS;
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
