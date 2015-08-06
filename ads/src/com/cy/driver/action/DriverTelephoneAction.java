package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 司机拨打电话
 * Created by haoy on 2014/9/23.
 */
@Scope("prototype")
@Controller("driverTelephoneAction")
public class DriverTelephoneAction extends AuthenticationAction{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/driverTelephoneAction")
    @ResponseBody
    @Log(type = 78)
    @Count(tableNames = {"t_count_driver_user_busi","t_count_driver_user_busi"}, columns = {"call_cargo_num", "call_transaction_num"})
    public JSonResponse execMethod(String driverId, String type) throws Exception {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            if (StringUtils.isBlank(type)) {
                return JSonResponse.makeHasContentJSonRespone("-8", "类型不能为空");
            }

            int rst = driverUserCargoInfoService.driverCall(driverId, type);
            if (rst == 1) {
                return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
            } else {
                return JSonResponse.makeHasContentJSonRespone("-8", "操作失败");
            }
        } catch (Exception e) {
            log.error("DriverTelephoneAction.class" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统异常，请重试。");
        }
    }

}
