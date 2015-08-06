package com.cy.driver.action;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.service.DriverUserCargoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Scope("prototype")
@Controller("driverUserInfoUpdate")
public class DriverUserInfoUpdate extends AuthenticationAction{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserCargoInfoService driverUserCargoInfoService;

    @RequestMapping(value = "/driverUserInfoUpdate")
    @ResponseBody
    public JSonResponse execute(DriverUserInfoBo bo) throws Exception{
		try {
            authentication(String.valueOf(bo.getId()));
            if (!isOk) {
                return jSonResponse;
            }
			int i = driverUserCargoInfoService.updateDriverUserInfo(bo);
			if(i == 1){
                if (log.isInfoEnabled()) {
                    log.info("修改信息成功");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "修改信息成功.");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("修改信息失败.");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "修改信息失败.");
			}
		} catch (Exception e) {
			log.error("DriverUserInfoUpdate.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统异常，请重试。");
		} 

	}

}
