package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.TransactionLastInfoBo;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.TransactionInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 新增交易状态历史
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("addTransactionLastInfo")
public class AddTransactionLastInfo extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private TransactionInfoService transactionInfoService;

	@RequestMapping(value = "/addTransactionLastInfo")
    @ResponseBody
    @Log(type = 54)
	public JSonResponse execute(String driverId, TransactionLastInfoBo bo) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(bo.getCargoId())) {
                if (log.isInfoEnabled()) {
                    log.info("货源不存在");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "货源不存在");
			}
			if(StringUtils.isBlank(bo.getTransactionId())) {
                if (log.isInfoEnabled()) {
                    log.info("交易不存在");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "交易不存在");
			}

			int key = transactionInfoService.addTransactionLastInfo(bo);
			if(key != 0) {
                if (log.isInfoEnabled()) {
                    log.info("新增交易状态历史成功");
                }
				return JSonResponse.makeHasContentJSonRespone("1", "新增交易状态历史成功");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("新增交易状态历史失败");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "新增交易状态历史失败");
			}
		} catch (Exception e) {
			log.error("新增交易状态历史出错-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "出错了，请稍后重试。");
		}

	}

}
