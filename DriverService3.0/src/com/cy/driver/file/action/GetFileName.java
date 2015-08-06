package com.cy.driver.file.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.domain.DriverUserInfoDomain;
import com.cy.driver.user.service.DriverUserCargoInfoService;

public class GetFileName extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6970944348986776388L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		String driverId = request.getParameter("driverId");
		String fileType = request.getParameter("fileType");
		String filePath = "";
		String fileName = "";		
		try {
			if(StringUtils.isBlank(driverId)) {
				sendResponseToJson("-9", "司机不存在");
				return ERROR;
			}
			
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
						
			if(StringUtils.isBlank(fileType)) {		
				sendResponseToJson("-8", "请确认要下载的图片类型");
				return ERROR;
			} 
			DriverUserInfoDomain domain = driverUserCargoInfoService.selectUserBasicInfo(driverId);		
			if(domain != null) {
				if("1".equals(fileType)) {
					if(domain.getIdentityLicenseNumFront() != null) {
						filePath = domain.getIdentityLicenseNumFront();
						fileName = getFileName(filePath);	
						if(StringUtils.isNotBlank(fileName)) {
							sendResponseToJson("1", "司机身份证正面照片图片名称为" + fileName,fileName);
						} else {
							sendResponseToJson("0", "没有找到图片");
							return ERROR;
						}
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("2".equals(fileType)) {
					if(domain.getIdentityLicenseNumContrary() != null) {
						filePath = domain.getIdentityLicenseNumContrary();
						fileName = getFileName(filePath);		
						if(StringUtils.isNotBlank(fileName)) {
							sendResponseToJson("1", "司机身份证反面照片图片名称为" + fileName,fileName);
						} else {
							sendResponseToJson("0", "没有找到图片");
							return ERROR;
						}
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("3".equals(fileType)) {
					if(domain.getDriversLicense() != null) {
						filePath = domain.getDriversLicense();
						fileName = getFileName(filePath);		
						if(StringUtils.isNotBlank(fileName)) {
							sendResponseToJson("1", "驾驶证路径图片名称为" + fileName,fileName);
						} else {
							sendResponseToJson("0", "没有找到图片");
							return ERROR;
						}
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("4".equals(fileType)) {
					if(domain.getDrivingLicense() != null) {
						filePath = domain.getDrivingLicense();
						fileName = getFileName(filePath);		
						if(StringUtils.isNotBlank(fileName)) {
							sendResponseToJson("1", "行驶证路径图片名称为" + fileName,fileName);
						} else {
							sendResponseToJson("0", "没有找到图片");
							return ERROR;
						}
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("5".equals(fileType)) {
					if(domain.getOperatingLicense() != null) {
						filePath = domain.getOperatingLicense();
						fileName = getFileName(filePath);		
						if(StringUtils.isNotBlank(fileName)) {
							sendResponseToJson("1", "营运证路径图片名称为" + fileName,fileName);
						} else {
							sendResponseToJson("0", "没有找到图片");
							return ERROR;
						}
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("6".equals(fileType)) {
					if(domain.getHeadPortrait() != null) {
						filePath = domain.getHeadPortrait();
						fileName = getFileName(filePath);		
						if(StringUtils.isNotBlank(fileName)) {
							sendResponseToJson("1", "用户头像图片名称为" + fileName,fileName);
						} else {
							sendResponseToJson("0", "没有找到图片");
							return ERROR;
						}
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				}				
			} else {
				sendResponseToJson("0", "未找到该用户有关的信息");
			}
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

	private String getFileName(String filePath) {
		File file = new File(filePath);
		if(file.exists() && file.isFile()) {
			return file.getName();
		}
		return null;
	}
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("getFileName");
		bo.setOperationType(50);
		bo.setRemark("获取图片名称");
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
