package com.cy.driver.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.domain.DriverUserInfoDomain;
import com.cy.driver.user.service.DriverUserCargoInfoService;

public class DownloadImage extends BaseJsonAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2515354456531290089L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;
	
	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	private String downFileName; 
	private InputStream inputStream;
	
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
						fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
						setDownFileName(fileName);
						inputStream = new FileInputStream(new File(filePath));
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("2".equals(fileType)) {
					if(domain.getIdentityLicenseNumContrary() != null) {
						filePath = domain.getIdentityLicenseNumContrary();
						fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
						setDownFileName(fileName);
						inputStream = new FileInputStream(new File(filePath));
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("3".equals(fileType)) {
					if(domain.getDriversLicense() != null) {
						filePath = domain.getDriversLicense();
						fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
						setDownFileName(fileName);
						inputStream = new FileInputStream(new File(filePath));
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("4".equals(fileType)) {
					if(domain.getDrivingLicense() != null) {
						filePath = domain.getDrivingLicense();
						fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
						setDownFileName(fileName);
						inputStream = new FileInputStream(new File(filePath));
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("5".equals(fileType)) {
					if(domain.getOperatingLicense() != null) {
						filePath = domain.getOperatingLicense();
						fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
						setDownFileName(fileName);
						inputStream = new FileInputStream(new File(filePath));
					} else {
						sendResponseToJson("0", "暂无此图片");
						return ERROR;
					}
				} else if("6".equals(fileType)) {
					if(domain.getHeadPortrait() != null) {
						filePath = domain.getHeadPortrait();
						fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
						setDownFileName(fileName);
						inputStream = new FileInputStream(new File(filePath));
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

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("downloadImage");
		bo.setOperationType(49);
		bo.setRemark("图片下载");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

}
