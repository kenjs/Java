package com.cy.driver.user.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;
/**
 * 附近货源量
 * @date 2014-5-29
 * @author haoyong
 *
 */
public class SelectNearByCargoCountAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181851820759759950L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	protected void execMethod() throws Exception {	
	}

	public String exec() {
		try {
			//司机id
			String driverId = request.getParameter("driverId");
						
			if(StringUtils.isBlank(driverId)){
				sendResponseToJson("-9","用户不存在");
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
			
			int count = driverUserCargoInfoService.selectNearByCargoCount(driverId);
			if(count == 0) {
				sendResponseToJson("0", "未找到符合条件的信息");
				logger.info("未找到符合条件的信息");
			} else {
				sendResponseToJson("1", "查找附近货源成功",count);
				logger.info("查找附近货源成功");
			}
		} catch (IOException e) {
			logger.error("查找附近货源出错" + e.getMessage());
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
		bo.setOperationName("selectNearByCargoCountAction");
		bo.setOperationType(12);
		bo.setRemark("附近货源量");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

}
