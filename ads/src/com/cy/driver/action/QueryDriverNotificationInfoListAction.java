package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.DriverNotificationInfoDomain;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
/**
 * 消息列表查询
 * @date 2014-6-11
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller("queryDriverNotificationInfoListAction")
public class QueryDriverNotificationInfoListAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/queryDriverNotificationInfoListAction")
    @ResponseBody
    @Log(type = 19)
	public JSonResponse execute(String driverId, String fromSize, String listSize) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			List<DriverNotificationInfoDomain> list = (List<DriverNotificationInfoDomain>) driverUserCargoInfoService.
															queryDriverNotificationInfo(driverId,fromSize,listSize);
			if(list != null){
				if(list.size() == 0){
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				}else{
                    if (log.isInfoEnabled()) {
                        log.info("查询成功, 找到" + list.size() + "条数据");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "查询成功", list);
				}
			}else{
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
			log.error("消息列表查询出错 - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
		}

	}

}
