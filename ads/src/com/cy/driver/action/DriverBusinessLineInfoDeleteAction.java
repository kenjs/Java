package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverBusinessLineInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 删除预约
 * 2014-5-28
 * @author haoyong 
 *
 */
@Scope("prototype")
@Controller("driverBusinessLineInfoDeleteAction")
public class DriverBusinessLineInfoDeleteAction extends AuthenticationAction{
	
	private Logger log = LoggerFactory. getLogger(getClass());

    @Resource
	private DriverBusinessLineInfoService driverBusinessLineInfoService;

    @RequestMapping(value = "/driverBusinessLineInfoDeleteAction")
    @ResponseBody
    @Log(type = 9)
	public JSonResponse execute(String driverId, String id) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(id)){
				return JSonResponse.makeHasContentJSonRespone("-8", "预约不存在");
			}
			int i = driverBusinessLineInfoService.deleteDriverBusinessLineInfo(id);
			if(i == 1){
                if (log.isInfoEnabled()) {
                    log.info("删除预约成功.");
                }
				return JSonResponse.makeHasContentJSonRespone("1", "删除预约成功");
			}
			else {
                if (log.isInfoEnabled()) {
                    log.info("删除预约失败.");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "删除预约失败");
			}
		} catch (Exception e) {
			log.error("预约线路删除出错 - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "删除预约线路出错.");
		}

	}

}
