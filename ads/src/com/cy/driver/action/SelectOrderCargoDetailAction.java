package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.OrderCargoInfoDomain;
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
 *  货源详情查询
 * @date 2014-6-6
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectOrderCargoDetailAction")
public class SelectOrderCargoDetailAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private OrderCargoInfoService orderCargoInfoService;

    @RequestMapping(value = "/selectOrderCargoDetailAction")
    @ResponseBody
    @Log(type = 29)
	public JSonResponse execute(String driverId, String id) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(id)){
                if (log.isInfoEnabled()) {
                    log.info("货源不存在. ");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "货源不存在");
			}
			OrderCargoInfoDomain domain = orderCargoInfoService.selectCargoDetailById(driverId,id);
			if(domain != null){
                if (log.isInfoEnabled()) {
                    log.info("查询成功. ");
                }
				return JSonResponse.makeHasContentJSonRespone("1", "查询成功", domain);
			}else{
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("SelectOrderCargoDetailAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
