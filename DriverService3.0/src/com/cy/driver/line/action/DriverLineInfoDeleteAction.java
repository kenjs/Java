package com.cy.driver.line.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.line.service.DriverLineInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 删除营运线路
 * @date 2014-6-3
 * @author haoyong
 *
 */
public class DriverLineInfoDeleteAction extends BaseJsonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2314093942823890685L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverLineInfoService driverLineInfoService;
	
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
			
			String id = request.getParameter("id");
			if(StringUtils.isBlank(id)) {
				sendResponseToJson("-8", "未找到要删除的运营线路.");
				logger.info("未找到要删除的运营线路.");
				return ERROR;
			}
			int i = driverLineInfoService.deleteDriverLineInfo(id);
			if(i == 0){
				sendResponseToJson("0", "删除运营线路失败.");
				logger.info("删除运营线路失败.");
				return ERROR;
			}
			sendResponseToJson("1", "删除运营线路成功.");
			logger.info("删除运营线路成功.");
		} catch (IOException e) {
			logger.error("删除运营线路出错." + e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setDriverLineInfoService(DriverLineInfoService driverLineInfoService) {
		this.driverLineInfoService = driverLineInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("driverLineInfoDeleteAction");
		bo.setOperationType(23);
		bo.setRemark("删除营运线路");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
