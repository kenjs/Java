package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.action.WebBaseAction;
import com.cy.driver.service.CallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by haoy on 2015/3/9.
 */
@Scope("prototype")
@Controller("callOutAction")
public class CallOutAction extends WebBaseAction {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Resource
    private CallService callService;

    @RequestMapping(value = "/call")
    @ResponseBody
    public JSonResponse execute(String driverId) {
        try {
            callService.call("", "");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "呼叫失败");
        }
        return JSonResponse.makeHasContentJSonRespone("1", "正在呼叫");
    }
}
