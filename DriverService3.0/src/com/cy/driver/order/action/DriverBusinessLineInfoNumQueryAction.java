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
 * 查询预约
 * 2014-6-11
 * @author haoyong 
 *
 */
public class DriverBusinessLineInfoNumQueryAction extends BaseJsonAction{

	private static final long serialVersionUID = -4324250654308179141L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverBusinessLineInfoService driverBusinessLineInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			//查询参数
			String driverId = request.getParameter("driverId");
						
			if(StringUtils.isBlank(driverId)){
				sendResponseToJson("-9", "用户不存在.");
				logger.info("用户不存在");
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
			
			int num = driverBusinessLineInfoService.selectDriverBusinessLineInfoCount(driverId);
			if(num == 0) {
				this.sendResponseToJson("0", "未找到符合条件的信息");
				logger.info("未找到符合条件的信息");
			} else {
				this.sendResponseToJson("1", "查询成功",num);
				logger.info("查询成功, 共找到" + num + "条预约");
			}
		} catch (IOException e) {
			logger.error("driver line Query Info fail."+ e.getMessage());
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
		bo.setOperationName("driverBusinessLineInfoNumQueryAction");
		bo.setOperationType(11);
		bo.setRemark("查询预约数目");
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
