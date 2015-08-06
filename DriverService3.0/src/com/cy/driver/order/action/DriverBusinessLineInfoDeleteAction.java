package com.cy.driver.order.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.order.service.DriverBusinessLineInfoService;
/**
 * 删除预约
 * 2014-5-28
 * @author haoyong 
 *
 */
public class DriverBusinessLineInfoDeleteAction extends BaseJsonAction{
	
	private static final long serialVersionUID = -7719969461937611825L;
	private Logger logger = LoggerFactory. getLogger(getClass());
	private DriverBusinessLineInfoService driverBusinessLineInfoService;
	private OperationLogService operationLogService;

	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)){
				logger.info("司机不存在. ");
				sendResponseToJson("-9", "司机不存在");
				return ERROR;
			} 
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				logger.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				logger.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(driverId);
			
			//参数
			String id = request.getParameter("id");			
			if(StringUtils.isBlank(id)){
				sendResponseToJson("-8", "预约不存在");
				return ERROR;
			}
			int i = driverBusinessLineInfoService.deleteDriverBusinessLineInfo(id);
			if(i == 1){
				logger.info("删除预约成功.");
				this.sendResponseToJson("1", "删除预约成功");
			}
			else {
				logger.info("删除预约失败.");
				this.sendResponseToJson("0", "删除预约失败");
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
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
		bo.setOperationName("driverBusinessLineInfoDeleteAction");
		bo.setOperationType(9);
		bo.setRemark("删除预约");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setDriverBusinessLineInfoService(
			DriverBusinessLineInfoService driverBusinessLineInfoService) {
		this.driverBusinessLineInfoService = driverBusinessLineInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
