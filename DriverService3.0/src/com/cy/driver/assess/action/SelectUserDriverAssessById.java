package com.cy.driver.assess.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.assess.service.DriverUserAssessInfoService;
import com.cy.driver.operationLog.service.OperationLogService;

public class SelectUserDriverAssessById extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3507091340338352087L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserAssessInfoService driverUserAssessInfoService;
	private OperationLogService operationLogService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在");
				sendResponseToJson("-9","司机不存在");
				return ERROR;
			}
			
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(driverId);
			
			String transactionId = request.getParameter("transactionId");
			if(StringUtils.isBlank(transactionId)) {
				sendResponseToJson("-8", "交易不存在");
				return ERROR;
			}
			int count = driverUserAssessInfoService.selectDriverUserAssess(transactionId);
			if(count == 0) {
				sendResponseToJson("0", "此交易尚未被评价");
			} else {
				sendResponseToJson("1", "此交易已被评价");
			}			
		} catch (IOException e) {
			log.error(e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectUserDriverAssessById");
		bo.setOperationType(48);
		bo.setRemark("根据交易id查找用户对司机是否已评价");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setDriverUserAssessInfoService(
			DriverUserAssessInfoService driverUserAssessInfoService) {
		this.driverUserAssessInfoService = driverUserAssessInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
