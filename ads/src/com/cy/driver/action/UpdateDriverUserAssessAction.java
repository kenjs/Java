package com.cy.driver.action;

import com.cy.driver.bo.DriverUserAssessInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverUserAssessInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 修改货源评价(尚未开放)
 * @date 2014-6-9
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("updateDriverUserAssessAction")
public class UpdateDriverUserAssessAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserAssessInfoService driverUserAssessInfoService;

    @RequestMapping(value = "/updateDriverUserAssessAction")
    @ResponseBody
    @Log(type = 43)
	public JSonResponse execute(String driverId, DriverUserAssessInfoBo bo) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(bo.getId() == 0){
				if (log.isInfoEnabled()) {
                    log.info("该条评价不存在");
                }

				return JSonResponse.makeHasContentJSonRespone("-8", "该条评价不存在");
			}
			int i = driverUserAssessInfoService.updateDriverUserAssessInfo(bo);
			if(i == 0){

                if (log.isInfoEnabled()) {
                    log.info("修改货源评价失败");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "修改货源评价失败");
			}else{
                if (log.isInfoEnabled()) {
                    log.info("修改货源评价成功");
                }
				return JSonResponse.makeHasContentJSonRespone("1", "修改货源评价成功");
			}
		} catch (Exception e) {
            log.error("UpdateDriverUserAssessAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统出错, 请稍后重试。");
		}

	}

}