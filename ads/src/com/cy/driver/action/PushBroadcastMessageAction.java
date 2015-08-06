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
 * 推送广播消息
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller("pushBroadcastMessageAction")
public class PushBroadcastMessageAction extends WebBaseAction{

    @Resource
    private AndroidPushMessageService androidPushMessageService;

    @Resource
    private OperationLogService operationLogService;

	private Logger log = LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "/pushBroadcastMessage")
    @ResponseBody
    @Log(type = 46)
	public JSonResponse execute() throws Exception{

		int messageType = 0;
		String message = "push broadcast message";
		
		String res = androidPushMessageService.pushBroadcastMessage(messageType, message);
		try {
			return JSonResponse.makeHasContentJSonRespone(res, message);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
            return JSonResponse.makeHasContentJSonRespone("-8", e.getMessage());
		}

	}

}
