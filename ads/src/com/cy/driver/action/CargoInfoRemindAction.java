package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * 货源消息提醒
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("cargoInfoRemindAction")
public class CargoInfoRemindAction extends AuthenticationAction {

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/cargoInfoRemind")
    @ResponseBody
    @Log(type = 59)
    public JSonResponse execute(String driverId, String nearByModifyTime, String businesslineModifyTime, String driverLineModifyTime) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			Map<String,String> map = new HashMap<String,String>();
			if(StringUtils.isBlank(nearByModifyTime)) {
				nearByModifyTime = DateUtil.getNowStr();
			}
			if(StringUtils.isBlank(businesslineModifyTime)) {
				businesslineModifyTime = DateUtil.getNowStr();
			}
			if(StringUtils.isBlank(driverLineModifyTime)) {
				driverLineModifyTime = DateUtil.getNowStr();
			}
			map.put("driverId", driverId);
			map.put("nearByModifyTime", nearByModifyTime);
			map.put("businesslineModifyTime", businesslineModifyTime);
			map.put("driverLineModifyTime", driverLineModifyTime);
			
			String jsonStr = driverUserCargoInfoService.cargoInfoRemind(map);
			return JSonResponse.makeHasContentJSonRespone("1", "查询成功", jsonStr);
		} catch (Exception e) {
			log.error("CargoInfoRemindAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "出错了，请重试。");
		}

	}

}
