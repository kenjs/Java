package com.cy.driver.user.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;

public class UpdateOldUserInfoAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3705797603470619278L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			DriverUserInfoBo bo = new DriverUserInfoBo();		
			String id = request.getParameter("driverId"),
					newCode = request.getParameter("newCode");					
			if(StringUtils.isBlank(id)){
				sendResponseToJson("-9", "用户不存在.");
				log.info("用户不存在.");
				return ERROR;
			} else {
				bo.setId(Integer.parseInt(id));
			}
			int accFlag = operationLogService.checkUser(id);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(id);
			
			bo.setCode(newCode);
			bo.setNewOrOldAppUser("0");
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
				sendResponseToJson("1", "修改信息成功.");
				log.info("修改信息成功");
			} else {
				sendResponseToJson("0", "修改信息失败.");
				log.info("修改信息失败.");
			}
		} catch (Exception e) {			
			e.printStackTrace();
			try {
				sendResponseToJson("-8", e.getMessage());
				log.error(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return SUCCESS;
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("updateOldUserInfo");
		bo.setOperationType(61);
		bo.setRemark("修改老用户信息");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
