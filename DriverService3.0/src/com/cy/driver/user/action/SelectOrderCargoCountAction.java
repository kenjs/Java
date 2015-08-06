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
 * 符合预约货源量
 * @date 2014-5-29
 * @author haoyong
 *
 */
public class SelectOrderCargoCountAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762911316291057681L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
	}

	public String exec() {
		try {
			//参数： 司机id
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
			
			int count = driverUserCargoInfoService.selectSuitCargoCount(driverId);
			if(count == 0) {
				logger.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
			} else {
				logger.info("符合预约的货源共"+count+"条");
				sendResponseToJson("1", "查找符合预约的货源成功",count);
			}
		} catch (IOException e) {
			logger.error("查找符合预约的货源出错" + e.getMessage());
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
		bo.setOperationName("selectOrderCargoCountAction");
		bo.setOperationType(13);
		bo.setRemark("符合预约的货源量");
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
