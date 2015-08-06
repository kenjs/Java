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
/**
 * 上传身份证
 * @author haoyong
 *
 */
public class UploadIdentityNumberAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6535263072105964864L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String id = request.getParameter("driverId"),
					identityNumber = request.getParameter("identityLicenseNum");
			
			if(StringUtils.isBlank(id)){
				sendResponseToJson("-9", "用户不存在.");
				log.warn("用户不存在.");
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
			
			if(StringUtils.isBlank(identityNumber)){
				sendResponseToJson("-8", "用户身份证号码没有输入");
				log.warn("用户身份证号码没有输入");
				return ERROR;
			}
			if(! ValidateUtil.validateIdentityLicenseNum(identityNumber)) {
				sendResponseToJson("-8", "身份证号码格式不正确");
				log.warn("身份证号码格式不正确");
				return ERROR;
			}
			DriverUserInfoBo bo = new DriverUserInfoBo();
			bo.setId(Integer.parseInt(id));
			bo.setIdentityLicenseNum(identityNumber);
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
				sendResponseToJson("1", "用户身份证修改成功.");
				log.info("修改信息成功");
			} else {
				sendResponseToJson("0", "用户身份证修改失败.");
				log.info("修改信息失败.");
			}
		} catch (Exception e) {
			try {
				sendResponseToJson("-8", e.getMessage());
				log.error(e.getMessage());
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
	
	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("uploadIdentityNumber");
		bo.setOperationType(58);
		bo.setRemark("上传身份证");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
}
