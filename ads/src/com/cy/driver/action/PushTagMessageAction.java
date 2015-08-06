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
 * 推送组播消息
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller("pushTagMessageAction")
public class PushTagMessageAction extends WebBaseAction{

    @Resource
	private AndroidPushMessageService androidPushMessageService;

    @Resource
    private OperationLogService operationLogService;

	private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/pushTagMessage")
    @ResponseBody
    @Log(type = 45)
	public JSonResponse execute() throws Exception{

		int messageType = 0;
		String message = "push tag message";
		String tagName = "driver";
		
		String res = androidPushMessageService.pushTagMessage(messageType, message, tagName);
		try {
			return JSonResponse.makeHasContentJSonRespone(res, message);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
            return JSonResponse.makeHasContentJSonRespone("-8", e.getMessage());
		}

	}

}
