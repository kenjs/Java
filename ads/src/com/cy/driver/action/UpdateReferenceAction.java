package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.domain.DriverUserInfoDomain;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by haoy on 2015/1/28.
 */
@Scope("prototype")
@Controller("updateReferenceAction")
public class UpdateReferenceAction extends AuthenticationAction {

    @RequestMapping(value = "/updateReference")
    @ResponseBody
    public JSonResponse reReference(int driverId) {
        if (driverId == 0) {
            return JSonResponse.makeHasContentJSonRespone("0", "司机ID不能为空");
        }
        return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
    }
}
