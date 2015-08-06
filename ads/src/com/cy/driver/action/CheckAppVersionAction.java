package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.constants.Constants;
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
 * 检查版本是否有更新
 * @date 2014-6-10
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("checkAppVersionAction")
public class CheckAppVersionAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;
	
    @RequestMapping(value = "/checkNewVersion")
    @ResponseBody
    @Log(type = 3)
	public JSonResponse execute(String driverId, String currentVersion, String os, String innerversion) throws Exception{
		try {
			if(StringUtils.isBlank(os)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "手机系统类型未获取");
			}

            int type = 1;
            if (os.equalsIgnoreCase(Constants.OS_WP)) {
                type = 3;
            } else if (os.equalsIgnoreCase(Constants.OS_IOS)) {
                type = 2;
            }

            Object object = driverUserCargoInfoService.checkVersion(driverId, currentVersion, type, innerversion);

            return JSonResponse.makeHasContentJSonRespone("1","已是最新", object);
		} catch (Exception e) {
			log.error("检查APP版本时出错-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "版本检查出错了，请稍后重试。");
		}

	}

}
