package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.OrderCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 某条货源被多少次标注为货已走
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectDriverCargoAssessNumByCargoId")
public class SelectDriverCargoAssessNumByCargoId extends AuthenticationAction{

    @Resource
	private OrderCargoInfoService orderCargoInfoService;

	private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/selectDriverCargoAssessNumByCargoId")
    @ResponseBody
    @Log(type = 60)
	public JSonResponse execute(String driverId,String cargoId) throws Exception{
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if(StringUtils.isBlank(cargoId)) {
                if (log.isInfoEnabled()) {
                    log.info("货源ID为空");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "货源ID为空");
			}
			int res = orderCargoInfoService.selectDriverCargoAssessNum(cargoId);
			return JSonResponse.makeHasContentJSonRespone("1", "该条货源已被" + res + "次点评为‘货已走’", res);

		} catch (Exception e) {
			log.error("SelectDriverCargoAssessNumByCargoId.class - " + e.getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
