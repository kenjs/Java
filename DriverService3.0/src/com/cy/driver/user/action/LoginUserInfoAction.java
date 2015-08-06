package com.cy.driver.user.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.domain.DriverUserInfoDomain;
import com.cy.driver.user.service.DriverUserCargoInfoService;
import com.cy.driver.user.service.LoginUserInfoService;
/**
 * APP用户登陆
 * @date 2014-5-23
 * @author haoyong
 *
 */
public class LoginUserInfoAction extends BaseJsonAction{

	private static final long serialVersionUID = 9134879066454805088L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private LoginUserInfoService loginUserInfoService;
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}

	public void setLoginUserInfoService(LoginUserInfoService loginUserInfoService) {
		this.loginUserInfoService = loginUserInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

	public String exec() {
		try {
			//登陆参数{code：用户名,pwd: 密码}
			String code  = request.getParameter("code");
			String pwd = request.getParameter("password");
			if(StringUtils.isBlank(code )){
				this.sendResponseToJson("-8", "登陆账户为空");
				logger.warn("登陆没有输入登陆账号. ");
				return ERROR;
			}
			
			boolean accountExist = loginUserInfoService.checkUserAccountExist(code );
			if(!accountExist) {
				logger.warn("账户不存在，请先注册.");
				sendResponseToJson("0", "账户不存在");
				return ERROR;
			}
			
			boolean freezeAccount = loginUserInfoService.chkFreezeAccount(code);
			if(freezeAccount) {
				sendResponseToJson("-7", "您已被冻结，请先联系管理员解冻！");
				return ERROR;
			}
			
			DriverUserInfoDomain domain = loginUserInfoService.checkLogin(code , pwd);
			if(domain == null){
				this.sendResponseToJson("0", "密码错误");
				logger.warn("登陆失败, 密码错误.");
				return ERROR;
			} 
			
			String mobileBrand = request.getParameter("mobileBrand");
			String operatingSystemVersionNnumber = request.getParameter("operatingSystemVersionNnumber");
			String mobilePhoneModel = request.getParameter("mobilePhoneModel");
			String noIimei = request.getParameter("noIimei");
			if(StringUtils.isNotBlank(mobileBrand) || StringUtils.isNotBlank(mobilePhoneModel)
					|| StringUtils.isNotBlank(operatingSystemVersionNnumber) || StringUtils.isNotBlank(noIimei)) {
				DriverUserInfoBo bo = new DriverUserInfoBo();
				
				bo.setId(domain.getId());
				bo.setMobileBrand(mobileBrand);
				bo.setOperatingSystemVersionNnumber(operatingSystemVersionNnumber);
				bo.setMobilePhoneModel(mobilePhoneModel);
				bo.setNoIimei(noIimei);
				
				driverUserCargoInfoService.updateDriverUserInfo(bo);
			}
			
			this.sendResponseToJson("1", "登录成功.",domain);
			logger.info("登陆成功.");
			log2Db(domain.getId() + "");
		} catch (Exception e) {
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
		bo.setOperationName("loginUserInfoAction");
		bo.setOperationType(2);
		bo.setRemark("登录");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
}
