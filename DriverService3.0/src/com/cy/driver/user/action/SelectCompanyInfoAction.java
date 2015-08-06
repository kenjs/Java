package com.cy.driver.user.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.domain.CompanyInfoDomain;
import com.cy.driver.user.service.DriverUserCargoInfoService;

public class SelectCompanyInfoAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2413658914412786640L;

	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	
	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在. ");
				sendResponseToJson("-9", "司机不存在");
				return ERROR;
			}
			
			String userId = request.getParameter("userId");
			
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
			
			if(StringUtils.isBlank(userId)) {
				log.info("用户不存在");
				sendResponseToJson("-9", "用户不存在");
				return LOGIN;
			}
			CompanyInfoDomain domain = driverUserCargoInfoService.selectConpanyInfoById(userId);
			if(domain != null) {
				log.info("查询公司信息成功");
				sendResponseToJson("1", "查询公司信息成功",domain);
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

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}
		
	private OperationLogService operationLogService;

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectCompanyInfo");
		bo.setOperationType(21);
		bo.setRemark("查找公司信息");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
