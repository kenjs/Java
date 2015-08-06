package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.PactDriverInfo;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.PactDriverInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description 合同车源状态修改
 * @author 		haoy
 * @since		2014-8-27
 *
 */
@Scope("prototype")
@Controller("updatePactDriverInfo")
public class UpdatePactDriverInfo extends AuthenticationAction{
	
	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private PactDriverInfoService pactDriverInfoService;

    @RequestMapping(value = "/updatePactDriverInfo")
    @ResponseBody
    @Log(type = 75)
    public JSonResponse execMethod(String driverId, String id, String pactStart) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			PactDriverInfo info = new PactDriverInfo();

			if(StringUtils.isBlank(id)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "必须参数合同车源ID缺失");
			}
			
			if(StringUtils.isBlank(pactStart)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "必须参数合同车源状态缺失");
			}
			
			info.setId(Integer.parseInt(id));
			info.setPactStart(pactStart);
			
			int rst = pactDriverInfoService.updatePactDriverInfo(info);
			if(rst == 1) {
				return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
			} else {
				return JSonResponse.makeHasContentJSonRespone("0", "操作失败");
			}
		} catch (Exception e) {
			log.error("UpdatePactDriverInfo.class - " + e.getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "系统出错, 请稍后重试。");
		}
	}

}
