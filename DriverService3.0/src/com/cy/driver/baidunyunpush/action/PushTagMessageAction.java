package com.cy.driver.baidunyunpush.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.baidunyunpush.service.AndroidPushMessageService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 推送组播消息
 * @author Administrator
 *
 */
public class PushTagMessageAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1327277552078404716L;

	private AndroidPushMessageService androidPushMessageService;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		
		log2Db("");
		int messageType = 0;
		String message = "push tag message";
		String tagName = "driver";
		
		String res = androidPushMessageService.pushTagMessage(messageType, message, tagName);
		try {
			sendResponseToJson(res, message);
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setAndroidPushMessageService(
			AndroidPushMessageService androidPushMessageService) {
		this.androidPushMessageService = androidPushMessageService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("pushTagMessage");
		bo.setOperationType(45);
		bo.setRemark("组播消息推送");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
