
package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.TransactionInfoDomain;
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
 * 订单详情
 * @date 2014-6-5
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("transactionInfoDetailAction")
public class TransactionInfoDetailAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoService transactionInfoService;

    @RequestMapping(value = "/transactionInfoDetailAction")
    @ResponseBody
    @Log(type = 34)
	public JSonResponse execute(String driverId, String id) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }
            
			if(StringUtils.isBlank(id)){
                if (log.isInfoEnabled()) {
                    log.info("交易不存在");
                }
                return JSonResponse.makeHasContentJSonRespone("-8", "交易不存在");
			}
			TransactionInfoDomain domain = transactionInfoService.selectDriverOrderDetail(id);
			if(domain != null){
				//log.info("查找成功");
                if (log.isInfoEnabled()) {
                    log.info("查找成功");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "查找成功.", domain);
			}else{
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息 ");
                }

				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}			
		} catch (Exception e) {
            log.error("TransactionInfoDetailAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
