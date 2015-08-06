package com.cy.driver.baidunyunpush.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.util.DateUtil;
import com.cy.driver.baidunyunpush.service.AndroidPushMessageService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 单播消息推送
 * @author Administrator
 *
 */
public class PushUnicastMessageAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3386848799171352387L;

	private AndroidPushMessageService androidPushMessageService;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		
		log2Db("");
		int messageType = 0;
		String message = "";
		message = "push unicast message at:";
		long channelId = 4286118129471105886L;
		String userId = "610467242227781875";
		String res = androidPushMessageService.pushUnicastMessage(messageType, message, channelId, userId);
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
		bo.setOperationName("pushUnicastMessage");
		bo.setOperationType(44);
		bo.setRemark("单播消息推送");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
