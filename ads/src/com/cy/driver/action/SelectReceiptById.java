package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.TransactionReceiptPathDomain;
import com.cy.driver.service.TransactionReceiptPathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 根据id查询
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectReceiptById")
public class SelectReceiptById extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private TransactionReceiptPathService transactionReceiptPathService;
	
	@RequestMapping(value = "/selectReceiptById")
    @ResponseBody
    @Log(type = 70)
	public JSonResponse execMethod(String driverId, String id) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			TransactionReceiptPathDomain domain = transactionReceiptPathService.selectReceiptById(id);
			if(domain != null){
                if (log.isInfoEnabled()) {
                    log.info("查询成功");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "查询成功!", domain);
			}else {
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("SelectReceiptById.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}
	}

}
