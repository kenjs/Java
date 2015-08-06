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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据订单id查询
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectReceiptListByTransactionId")
public class SelectReceiptListByTransactionId extends AuthenticationAction{


	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private TransactionReceiptPathService transactionReceiptPathService;
	
	@RequestMapping(value = "/selectReceiptListByTransactionId")
    @ResponseBody
    @Log(type = 69)
	public JSonResponse execMethod(String driverId, String transactionId, String type) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			Map<String,String> map = new HashMap<String, String>();
			map.put("transactionId", transactionId);
			map.put("type", type);
			
			List<TransactionReceiptPathDomain> list = transactionReceiptPathService.selectReceiptListByTransactionId(map);
			if(list != null){
				if(list.size() == 0){
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
                    return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				}else {
                    if (log.isInfoEnabled()) {
                        log.info("查询成功");
                    }
                    return JSonResponse.makeHasContentJSonRespone("1", "查询成功!", list);
				}
			}else {
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
			log.error("SelectReceiptListByTransactionId.class - " + e.getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}
	}

}
