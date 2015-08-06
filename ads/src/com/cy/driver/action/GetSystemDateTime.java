package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.common.util.DateUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 获取系统当前时间 yyyy-MM-dd hh:mm:ss
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller("getSystemDateTime")
public class GetSystemDateTime extends WebBaseAction{

    @RequestMapping(value = "/getSystemDateTime")
    @ResponseBody
	public JSonResponse execute() {
        String currTime = DateUtil.getCurrentDateTime();
        return JSonResponse.makeHasContentJSonRespone("1", "获取系统当前时间成功", currTime);
	}
}
