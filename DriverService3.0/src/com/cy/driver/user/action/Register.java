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
import com.cy.driver.user.service.LoginUserInfoService;
/**
 * 注册
 * @author haoyong
 *
 */
public class Register extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6806105896454171892L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private LoginUserInfoService loginUserInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		
		try {
			String phoneNumber = request.getParameter("code");//注册手机号
			String checkCode = request.getParameter("checkCode");//验证码校验
			
			if(StringUtils.isBlank(phoneNumber)){
				log.info("注册时，用户没有输入手机号码");
				sendResponseToJson("-8", "请输入手机号码");
				return ERROR;
			}
			
			if(! ValidateUtil.validateTelePhone(phoneNumber)) {
				sendResponseToJson("-8", "手机号码格式错误");
				return ERROR;
			}
			
			if(StringUtils.isBlank(checkCode)) {
				sendResponseToJson("-8", "验证码错误");
				return ERROR;
			}
			
			boolean  bolCodeCheck = Boolean.valueOf(checkCode);
			if(!bolCodeCheck){
				log.info("验证码错误");
				sendResponseToJson("-8", "验证码错误");
				return ERROR;
			}
			
			boolean numberExist = loginUserInfoService.checkUserAccountExist(phoneNumber);
			if(numberExist) {
				log.info("手机号码：" + phoneNumber + "已经注册。");
				sendResponseToJson("0", "该号码已注册");
				return ERROR;
			}
			
			String mobileBrand = request.getParameter("mobileBrand");
			String operatingSystemVersionNnumber = request.getParameter("operatingSystemVersionNnumber");
			String mobilePhoneModel = request.getParameter("mobilePhoneModel");
			String noIimei = request.getParameter("noIimei");
			
			DriverUserInfoBo bo = new DriverUserInfoBo();
			bo.setCode(phoneNumber);
			if(StringUtils.isNotBlank(mobileBrand) || StringUtils.isNotBlank(mobilePhoneModel)
					|| StringUtils.isNotBlank(operatingSystemVersionNnumber) || StringUtils.isNotBlank(noIimei)) {
				
				bo.setMobileBrand(mobileBrand);
				bo.setOperatingSystemVersionNnumber(operatingSystemVersionNnumber);
				bo.setMobilePhoneModel(mobilePhoneModel);
				bo.setNoIimei(noIimei);				
			}
			
			int i = loginUserInfoService.addDriverUserInfo(bo);
			if(i != 0) {
				log.info("注册成功");
				sendResponseToJson("1", "注册成功",i);
			} else {
				log.info("注册失败");
				sendResponseToJson("-8", "注册失败");
			}
			log2Db(i + "");
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
		bo.setOperationName("register");
		bo.setOperationType(1);
		bo.setRemark("注册");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setLoginUserInfoService(LoginUserInfoService loginUserInfoService) {
		this.loginUserInfoService = loginUserInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
