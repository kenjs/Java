package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.QuoteInfoBo;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.QuoteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 新增货源报价
 * @date 2014-6-9
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("addQuoteInfoAction")
public class AddQuoteInfoAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private QuoteInfoService quoteInfoService;

    @RequestMapping(value = "/addQuoteInfoAction")
    @ResponseBody
    @Log(type = 39)
    @Count(tableNames = "t_count_driver_user_busi", columns = "price_quotes")
	public JSonResponse execute(QuoteInfoBo bo) throws Exception{
		try {
            authentication(String.valueOf(bo.getDriverId()));

            if (!isOk) {
                return jSonResponse;
            }
			
            return quoteInfoService.driverQuote(bo);
		} catch (Exception e) {
			log.error("报价出错了-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "出错了，请稍后重试。");
		}

	}
}
