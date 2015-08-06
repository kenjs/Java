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
import java.sql.SQLException;

/**
 * Created by haoy on 2014/10/28.
 */
@Scope("prototype")
@Controller("queryPactVipDriverNumAction")
public class QueryPactVipDriverNumAction extends AuthenticationAction {

    @Resource
    private DriverUserCargoInfoService driverUserCargoInfoService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/queryUnconfirmedVipDriverNum")
    @ResponseBody
    public JSonResponse execute(String driverId) throws SQLException {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            int num = driverUserCargoInfoService.queryPactVipDriverNum(driverId);

            return JSonResponse.makeHasContentJSonRespone("1","查找成功",num);
        } catch (Exception e) {
            log.error("QueryPactVipDriverNumAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
        }
    }
}
