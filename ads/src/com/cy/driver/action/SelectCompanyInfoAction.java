package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.CompanyInfoDomain;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
@Scope("prototype")
@Controller("selectCompanyInfoAction")
public class SelectCompanyInfoAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

	@RequestMapping(value = "/selectCompanyInfo")
    @ResponseBody
    @Log(type = 21)
	public JSonResponse execute(String driverId, String userId) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if("1".equals(userId)) {
				//log.info("未找到符合条件的信息");
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                //sendResponseToJson("0", "未找到符合条件的信息");
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}

			CompanyInfoDomain domain = driverUserCargoInfoService.selectConpanyInfoById(userId);
			if(domain != null) {
				//log.info("查询公司信息成功");
                if (log.isInfoEnabled()) {
                    log.info("查询公司信息成功");
                }
                //sendResponseToJson("1", "查询公司信息成功",domain);
                return JSonResponse.makeHasContentJSonRespone("1", "查询公司信息成功", domain);
			} else {
				//log.info("未找到符合条件的信息");
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                //sendResponseToJson("0", "未找到符合条件的信息");
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("SelectCompanyInfoAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}
	}

}
