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

public class UpdateDriverUserInfoAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8608582522669276573L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {		
		
	}

	public String exec() {
		try {
			DriverUserInfoBo bo = new DriverUserInfoBo();
			StringBuilder sb = new StringBuilder();
			String id = request.getParameter("driverId"),
				   driverName = request.getParameter("driverName"),
				    carNumber = request.getParameter("carNumber"),
					carLength = request.getParameter("carLength"),
					//carTypes = request.getParameter("carTypes"),
					carPlateType = request.getParameter("carPlateType"),
					carBarType = request.getParameter("carBarType"),
					carCubage = request.getParameter("carCubage"),
					carWeight = request.getParameter("carWeight");
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
			log2Db(id);
			
			if(StringUtils.isBlank(driverName)){
				sendResponseToJson("-8", "请输入司机姓名.");
				return ERROR;
			} else {
				bo.setName(driverName);
			}
			
			if(StringUtils.isBlank(carNumber)){
				sendResponseToJson("-8", "请输入车辆牌照号码.");
				return ERROR;
			} else {
				bo.setCarNumber(carNumber);
			}
			
			if(StringUtils.isBlank(carLength)){
				sendResponseToJson("-8", "请输入车长.");
				return ERROR;
			} else {
				bo.setCarLength(carLength);
				sb.append(carLength).append(" ");
			}
			
			if(StringUtils.isNotBlank(carCubage)){
				bo.setCarCubage(carCubage);
			}
			
			if(StringUtils.isNotBlank(carWeight)){
				bo.setCarWeight(carWeight);
			}
			
			if(StringUtils.isNotBlank(carPlateType)){
				bo.setCarPlateType(carPlateType);
				sb.append(carPlateType).append(" ");
			} 
			if(StringUtils.isNotBlank(carBarType)){
				bo.setCarBarType(carBarType);
				sb.append(carBarType);
			}
			bo.setCarTypes(sb.toString());
			
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
				sendResponseToJson("1", "修改信息成功.");
				logger.info("修改信息成功");
			} else {
				sendResponseToJson("0", "修改信息失败.");
				logger.info("修改信息失败.");
			}
		} catch (IOException e) {
			try {
				sendResponseToJson("-8", e.getMessage());
				logger.error(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("updateDriverUserInfoAction");
		bo.setOperationType(16);
		bo.setRemark("修改Driver信息");
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
