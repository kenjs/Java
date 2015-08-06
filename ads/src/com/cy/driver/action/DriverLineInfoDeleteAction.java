package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverLineInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 删除营运线路
 * @since  2014-6-3
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverLineInfoDeleteAction")
public class DriverLineInfoDeleteAction extends AuthenticationAction {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverLineInfoService driverLineInfoService;

    @RequestMapping(value = "/driverLineInfoDelete")
    @ResponseBody
    @Log(type = 23)
	public JSonResponse execute(String driverId, String id) throws Exception {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            if (StringUtils.isBlank(id)) {
                if (log.isInfoEnabled()) {
                    log.info("未找到要删除的运营线路.");
                }
                return JSonResponse.makeHasContentJSonRespone("-8", "未找到要删除的运营线路.");
            }
            int i = driverLineInfoService.deleteDriverLineInfo(id);
            if (i == 0) {
                if (log.isInfoEnabled()) {
                    log.info("删除运营线路失败.");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "删除运营线路失败.");
            }

            if (log.isInfoEnabled()) {
                log.info("删除运营线路成功.");
            }
            return JSonResponse.makeHasContentJSonRespone("1", "删除运营线路成功.");
        } catch (Exception e) {
            log.error("删除运营线路出错." + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "删除运营线路出错.");
        }

    }
}
