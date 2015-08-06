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
 * 检查版本是否有更新
 * @date 2014-6-10
 * @author haoyong
 *
 */
public class CheckAppVersionAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3037976787328092655L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OperationLogService operationLogService;
	private String androidNewVersion;
	private String androidNewVersionMeta; 
	private String androidNewVersionForceUpdate;
	private String versionCode;
	
	private String iosNewVersion;
	private String iosNewVersionMeta;
	
	private String appDatabaseUpdate;
	
	protected void execMethod() throws Exception {

	}

	public String exec() {
		try {			
			String userDriverId = request.getParameter("userDriverId");
			
			String currentVersion = request.getParameter("currentVersion");//当前版本
			String os = request.getParameter("os");//操作系统
			if(StringUtils.isBlank(currentVersion)) {
				sendResponseToJson("-8", "APP当前安装版本未知");
				return ERROR;
			}
			if(StringUtils.isBlank(os)) {
				sendResponseToJson("-8", "手机系统类型未知");
				return ERROR;
			}
			
			log2Db(userDriverId);
			if( os.equalsIgnoreCase(Constants.OS_ANDROID) ){
				if( currentVersion.equalsIgnoreCase(androidNewVersion) ){
					String json = "{\"result\":\"1\",\"errorMessage\":\"无版本更新\",\"newVersion\":\"" +
							androidNewVersion + "\",\"content\":{\"force\":\"" + androidNewVersionForceUpdate + "\",\"code\":\"" +
						versionCode + "\",\"meta\":\"" + androidNewVersionMeta + "\",\"appDatabaseUpdate\":\"" + appDatabaseUpdate + "\"}}";
					log.info("return json=[{}]",new Object[]{json});
					sendResponseToJsonObject(json);
//					sendResponseToJson("1", "当前版本已是最新");
					log.info("当前版本已是最新");
				}else{
					String json = "{\"result\":\"1\",\"errorMessage\":\"有版本更新\",\"newVersion\":\"" +
							androidNewVersion + "\",\"content\":{\"force\":\"" + androidNewVersionForceUpdate + "\",\"code\":\"" +
						versionCode + "\",\"meta\":\"" + androidNewVersionMeta + "\",\"appDatabaseUpdate\":\"" + appDatabaseUpdate + "\"}}";
					log.info("return json=[{}]",new Object[]{json});
					sendResponseToJsonObject(json);
				}
			}else if (os.equalsIgnoreCase(Constants.OS_IOS)){
				if( currentVersion.equalsIgnoreCase(iosNewVersion)){
					sendResponseToJson("1", "当前版本已是最新");
					log.info("当前版本已是最新");
				}else{
					String result = sendResponseToJson("1", iosNewVersionMeta);
					log.info("return json=[{}]",new Object[]{result});
				}
			}			
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("checkNewVersion");
		bo.setOperationType(3);
		bo.setRemark("版本更新检查");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public String getAndroidNewVersion() {
		return androidNewVersion;
	}

	public void setAndroidNewVersion(String androidNewVersion) {
		this.androidNewVersion = androidNewVersion;
	}

	public String getAndroidNewVersionMeta() {
		return androidNewVersionMeta;
	}

	public void setAndroidNewVersionMeta(String androidNewVersionMeta) {
		this.androidNewVersionMeta = androidNewVersionMeta;
	}

	public String getAndroidNewVersionForceUpdate() {
		return androidNewVersionForceUpdate;
	}

	public void setAndroidNewVersionForceUpdate(String androidNewVersionForceUpdate) {
		this.androidNewVersionForceUpdate = androidNewVersionForceUpdate;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getIosNewVersion() {
		return iosNewVersion;
	}

	public void setIosNewVersion(String iosNewVersion) {
		this.iosNewVersion = iosNewVersion;
	}

	public String getIosNewVersionMeta() {
		return iosNewVersionMeta;
	}

	public void setIosNewVersionMeta(String iosNewVersionMeta) {
		this.iosNewVersionMeta = iosNewVersionMeta;
	}

	public String getAppDatabaseUpdate() {
		return appDatabaseUpdate;
	}

	public void setAppDatabaseUpdate(String appDatabaseUpdate) {
		this.appDatabaseUpdate = appDatabaseUpdate;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
