package com.cy.driver.user.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.domain.DriverUserInfoDomain;
import com.cy.driver.user.service.DriverUserCargoInfoService;

/**
 * 用户基本信息查询
 * @date 2014-6-4
 * @author haoyong
 *
 */
public class UserBasicInformationQueryAction extends BaseJsonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4136152394656316968L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			//用户登录手机号
			String code = request.getParameter("driverId");
						
			if(StringUtils.isBlank(code)){
				log.info("用户没有登录.");
				sendResponseToJson("-9", "用户没有登录");
				return ERROR;
			}
			
			int accFlag = operationLogService.checkUser(code);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(code);
			
			DriverUserInfoDomain domain = driverUserCargoInfoService.selectUserBasicInfo(code);
			if(domain != null){
				log.info("查找用户基本信息成功.");
				sendResponseToJson("1", "查找用户基本信息成功",domain);
			} else {
				log.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
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
		bo.setOperationName("userBasicInformationQueryAction");
		bo.setOperationType(17);
		bo.setRemark("用户基本信息查询");
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
