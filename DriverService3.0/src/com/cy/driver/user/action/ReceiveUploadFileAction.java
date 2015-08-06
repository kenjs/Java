package com.cy.driver.user.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;
/**
 * 接收上传文件
 * @author haoyong
 *
 */
public class ReceiveUploadFileAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3891740206865317177L;
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private Logger log = LoggerFactory.getLogger(getClass());
	// 上传文件  
    private File file;// 拦截器会为你在缓冲区创建临时文件，这是临时文件对象  
    private String fileContentType;// 头域中的值  
    private String fileFileName;// 报文体中的name  
    private String imgPath;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		DriverUserInfoBo bo = new DriverUserInfoBo();
		String path = "";
		StringBuilder sb = new StringBuilder();
		sb.append(imgPath);
		try {
			String id = request.getParameter("driverId");
						
			String type = request.getParameter("type");//
			if(StringUtils.isBlank(id)) {
				log.info("上传图片时找不到用户");
				sendResponseToJson("-9", "找不到用户");
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
			
			if(StringUtils.isBlank(type)) {
				log.info("上传图片不知道图片类型");
				sendResponseToJson("-8", "请确认图片类型");
				return ERROR;
			}
			sb.append("APP").append("/");
			sb.append(id).append("/").append(fileFileName);
			path = sb.toString();
			bo.setId(Integer.parseInt(id));
			if("1".equals(type)) {
				bo.setIdentityLicenseNumFront(path);
			} else if("2".equals(type)) {
				bo.setIdentityLicenseNumContrary(path);
			} else if("3".equals(type)) {
				bo.setDriversLicense(path);
			} else if("4".equals(type)) {
				bo.setDrivingLicense(path);
			} else if("5".equals(type)) {
				bo.setOperatingLicense(path);
			} else if("6".equals(type)) {
				bo.setHeadPortrait(path);
			}
			//要把用户修改为未验证
			bo.setAuditFlag("0");
			bo.setSubmitType("1");
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1) {
				log.info("上传图片成功");
				sendResponseToJson("1", "上传图片成功");
				uploadFile(path);
			} else {
				log.info("上传图片失败");
				sendResponseToJson("0", "上传图片失败");
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
	
	private void uploadFile(String path) throws Exception{       
        File currFile = new File(path);  
        log.info("接收到的文件存放路径======>" + currFile.getAbsolutePath());  
        FileUtils.copyFile(this.file, currFile);// struts2提供的工具类，意思是把缓存区文件放到哪里   
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}
    
	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("uploadFile");
		bo.setOperationType(20);
		bo.setRemark("图片上传");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
