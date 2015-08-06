package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.DriverUserInfoDomain;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户基本信息查询
 * @date 2014-6-4
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("UserBasicInformationQueryAction")
public class UserBasicInformationQueryAction extends AuthenticationAction {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/userBasicInformationQueryAction")
    @ResponseBody
    @Log(type = 17)
	public JSonResponse execute(String driverId) {
		try {

            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			DriverUserInfoDomain domain = driverUserCargoInfoService.selectUserBasicInfo(driverId);
			if(domain != null){
                if (log.isInfoEnabled()) {
                    log.info("查找用户基本信息成功.");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "查找用户基本信息成功", domain);
			} else {
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("UserBasicInformationQueryAction.class - " + e.getCause().getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统出错，请稍后重试。");
		}
	}

}
