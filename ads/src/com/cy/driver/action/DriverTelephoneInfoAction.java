package com.cy.driver.action;

import com.cy.driver.bo.DriverTelephoneInfo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverTelephoneInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 司机手机信息
 * Created by haoy on 2014/9/25.
 */
@Scope("prototype")
@Controller("driverTelephoneInfoAction")
public class DriverTelephoneInfoAction extends AuthenticationAction{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverTelephoneInfoService driverTelephoneInfoService;

    @RequestMapping(value = "/driverTelephoneInfoAction")
    @ResponseBody
    @Log(type = 80)
    public JSonResponse execMethod(String driverId, DriverTelephoneInfo driverTelephoneInfo) throws Exception {
        authentication(driverId);
        if (!isOk) {
            return jSonResponse;
        }

        try {
            int rst = driverTelephoneInfoService.saveUserMobilePhoneInfo(driverTelephoneInfo);

            if (rst == 0) {
                return JSonResponse.makeHasContentJSonRespone("0", "操作失败");
            } else {
                return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
            }
        } catch (Exception e) {
            log.error("DriverTelephoneInfoAction.class" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统异常，请重试。");
        }
    }

}
