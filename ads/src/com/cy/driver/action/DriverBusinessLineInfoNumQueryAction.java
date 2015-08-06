package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverBusinessLineInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 查询预约
 * 2014-6-11
 * @author haoyong 
 *
 */
@Scope("prototype")
@Controller("driverBusinessLineInfoNumQueryAction")
public class DriverBusinessLineInfoNumQueryAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverBusinessLineInfoService driverBusinessLineInfoService;

    @RequestMapping(value = "/driverBusinessLineInfoNumQueryAction")
    @ResponseBody
    @Log(type = 11)
	public JSonResponse execute(String driverId)throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			int num = driverBusinessLineInfoService.selectDriverBusinessLineInfoCount(driverId);
			if(num == 0) {
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("查询成功, 共找到" + num + "条预约");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "查询成功", num);
			}
		} catch (Exception e) {
			log.error("driver line Query Info fail."+ e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
		}

	}

}
