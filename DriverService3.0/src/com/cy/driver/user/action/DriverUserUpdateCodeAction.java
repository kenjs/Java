package com.cy.driver.user.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.util.ValidateUtil;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;
import com.cy.driver.user.service.LoginUserInfoService;

/**
 * 修改手机号
 * @author haoyong
 *
 */
public class DriverUserUpdateCodeAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3798784308004844108L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private LoginUserInfoService loginUserInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String id = request.getParameter("driverId");
			String newCode = request.getParameter("code");
			
			if(StringUtils.isBlank(id)) {
				sendResponseToJson("-9", "司机不存在");
				return ERROR;
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
			
			if(StringUtils.isBlank(newCode)) {
				sendResponseToJson("-8", "请输入新号码");
				return ERROR;
			}
			if(! ValidateUtil.validateTelePhone(newCode)) {
				sendResponseToJson("-8", "手机号码格式错误");
				return ERROR;
			}
			boolean resBool = loginUserInfoService.checkUserAccountExist(newCode);
			if(resBool) {
				sendResponseToJson("0", "此号码已注册过");
				return ERROR;
			}
			DriverUserInfoBo bo = new DriverUserInfoBo();
			bo.setId(Integer.parseInt(id));
			bo.setNewCode(newCode);
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 0) {
				sendResponseToJson("0", "修改手机号失败");
				return ERROR;
			}
			sendResponseToJson("1", "修改手机号成功");
		} catch (Exception e) {
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
		bo.setOperationName("driverUserUpdateCode");
		bo.setOperationType(6);
		bo.setRemark("修改手机号");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

	public void setLoginUserInfoService(LoginUserInfoService loginUserInfoService) {
		this.loginUserInfoService = loginUserInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
