package com.cy.version;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.constants.Constants;
import com.cy.driver.operationLog.service.OperationLogService;

/**
 * App下载
 * @date 2014-6-10
 * @author haoyong 
 *
 */
public class AppDowloadAction extends BaseJsonAction{
	
	private String androidDownloadFilepath;
	private String androidDownloadFilename;
	
	private String iosDownloadFilepath;
	private String iosDownloadFilename;
	
	private OperationLogService operationLogService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 622588683893071220L;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public String exec() {
		try {
			log2Db("");
			String os = request.getParameter("os");//手机系统
			String filePath = "";
			String realPath = request.getContextPath();
			log2Db("");
			if(os.equalsIgnoreCase(Constants.OS_ANDROID)){
				//realPath = realPath.substring(0, realPath.lastIndexOf("/"));
				filePath = androidDownloadFilepath + androidDownloadFilename;
				response.sendRedirect(realPath + filePath);
				sendResponseToJson("1", "Andriod版本有更新",filePath);				
				log.info("OS=[{}],AppPath=[{}]",new Object[]{os,filePath});
				return SUCCESS;
			}else if(os.equalsIgnoreCase(Constants.OS_IOS)){
				filePath = iosDownloadFilepath + iosDownloadFilename;
				response.sendRedirect(realPath + filePath);
				sendResponseToJson("1", "IOS版本有更",filePath);				
				log.info("OS=[{}],AppPath=[{}]",new Object[]{os,filePath});
				return SUCCESS;
			}
			sendResponseToJson("0", "没有可下载文件");
			log.info("OS=[{}],AppPath=[{}]",new Object[]{os,filePath});			
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return ERROR;
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("downloadNewVersion");
		bo.setOperationType(4);
		bo.setRemark("版本下载");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public String getAndroidDownloadFilepath() {
		return androidDownloadFilepath;
	}

	public void setAndroidDownloadFilepath(String androidDownloadFilepath) {
		this.androidDownloadFilepath = androidDownloadFilepath;
	}

	public String getAndroidDownloadFilename() {
		return androidDownloadFilename;
	}

	public void setAndroidDownloadFilename(String androidDownloadFilename) {
		this.androidDownloadFilename = androidDownloadFilename;
	}

	public String getIosDownloadFilepath() {
		return iosDownloadFilepath;
	}

	public void setIosDownloadFilepath(String iosDownloadFilepath) {
		this.iosDownloadFilepath = iosDownloadFilepath;
	}

	public String getIosDownloadFilename() {
		return iosDownloadFilename;
	}

	public void setIosDownloadFilename(String iosDownloadFilename) {
		this.iosDownloadFilename = iosDownloadFilename;
	}

	protected void execMethod() throws Exception {
		
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
