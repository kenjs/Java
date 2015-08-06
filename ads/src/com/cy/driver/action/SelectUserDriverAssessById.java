package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverUserAssessInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Scope("prototype")
@Controller("selectUserDriverAssessById")
public class SelectUserDriverAssessById extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserAssessInfoService driverUserAssessInfoService;

    @RequestMapping(value = "/selectUserDriverAssessById")
    @ResponseBody
    @Log(type = 48)
	public JSonResponse execute(String driverId, String transactionId) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(transactionId)) {

				return JSonResponse.makeHasContentJSonRespone("-8", "交易不存在");
			}
			int count = driverUserAssessInfoService.selectDriverUserAssess(transactionId);
			if(count == 0) {
				return JSonResponse.makeHasContentJSonRespone("0", "此交易尚未被评价");
			} else {
				return JSonResponse.makeHasContentJSonRespone("1", "此交易已被评价");
			}
		} catch (Exception e) {
            log.error("SelectUserDriverAssessById.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
