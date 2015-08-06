package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 提交认证
 * Created by haoy on 2015/1/21.
 */
@Scope("prototype")
@Controller("submitCertificationAction")
public class SubmitCertificationAction extends AuthenticationAction {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/submitCertification")
    @ResponseBody
    public JSonResponse submitCertification(String driverId, String identityLicenseNum) {
        try {
            authentication(driverId); //用户验证
            if (!isOk) {
                return jSonResponse;
            }
            return driverUserCargoInfoService.submitCertification(driverId, identityLicenseNum);
        } catch (Exception e) {
            log.error("认证提交过程中发生错误，错误信息-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "认证提交失败，请重试。");
        }
    }
}
