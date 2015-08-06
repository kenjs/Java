package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.TransactionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Scope("prototype")
@Controller("selectOrderNumAction")
public class SelectOrderNumAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoService transactionInfoService;

    @RequestMapping(value = "/selectMyOrderNum")
    @ResponseBody
    @Log(type = 56)
	public JSonResponse execute(String driverId) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			int num = transactionInfoService.selectMyOrderNumber(driverId);
			if(num == 0) {
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }

				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("查找成功, 共找到"+ num +"条数据");
                }

				return JSonResponse.makeHasContentJSonRespone("1", "查找成功.", num);
			}
		} catch (Exception e) {
            log.error("SelectOrderNumAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
