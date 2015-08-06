package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description APP首页预约数量货源数量信息
 * @author 		haoy
 *
 */
@Scope("prototype")
@Controller("queryHomePageNumInfo")
public class QueryHomePageNumInfo extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

	@RequestMapping(value = "/queryHomePageNumInfo")
    @ResponseBody
    @Log(type = 27)
	public JSonResponse execute(String driverId) throws Exception {

        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            String json = driverUserCargoInfoService.initHomePageNum(driverId);


            return JSonResponse.makeHasContentJSonRespone("1", "查找成功", json);

        } catch (Exception e) {
            log.error("QueryHomePageNumInfo.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
        }
	}

}
