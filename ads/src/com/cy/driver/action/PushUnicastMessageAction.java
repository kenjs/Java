package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.AndroidPushMessageService;
import com.cy.driver.service.OperationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 单播消息推送
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller("pushUnicastMessageAction")
public class PushUnicastMessageAction extends WebBaseAction{

    @Resource
    private OperationLogService operationLogService;

    @Resource
	private AndroidPushMessageService androidPushMessageService;

	private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/pushUnicastMessage")
    @ResponseBody
    @Log(type = 44)
	public JSonResponse execute() throws Exception{

		int messageType = 0;
		String message;
		message = "push unicast message at:";
		long channelId = 4286118129471105886L;
		String userId = "610467242227781875";
		String res = androidPushMessageService.pushUnicastMessage(messageType, message, channelId, userId);
		try {
			return JSonResponse.makeHasContentJSonRespone(res, message);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
            return JSonResponse.makeHasContentJSonRespone("-8", e.getMessage());
		}

	}

}
