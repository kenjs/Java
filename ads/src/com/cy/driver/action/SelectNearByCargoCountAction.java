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
 * 附近货源量
 * @date 2014-5-29
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectNearByCargoCountAction")
public class SelectNearByCargoCountAction extends AuthenticationAction{

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DriverUserCargoInfoService driverUserCargoInfoService;
	
	@RequestMapping(value = "/selectNearByCargoCountAction")
    @ResponseBody
    @Log(type = 12)
	public JSonResponse execute(String driverId) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			int count = driverUserCargoInfoService.selectNearByCargoCount(driverId);
			if(count == 0) {
				//sendResponseToJson("0", "未找到符合条件的信息");
                if (logger.isInfoEnabled()) {
                    logger.info("未找到符合条件的信息");
                }

                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
            } else {
				//sendResponseToJson("1", "查找附近货源成功",count);
                if (logger.isInfoEnabled()) {
                    logger.info("查找附近货源成功");
                }

                return JSonResponse.makeHasContentJSonRespone("1", "查找附近货源成功", count);
            }
		} catch (Exception e) {
            logger.error("SelectNearByCargoCountAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}
	}

}
