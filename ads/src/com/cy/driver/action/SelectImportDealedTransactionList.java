package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.TransactionInfoDomain;
import com.cy.driver.service.QuoteInfoService;
import org.apache.commons.lang.StringUtils;
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

/*
 * 我的记录（已经成交的导入的订单）
 */
@Scope("prototype")
@Controller("selectImportDealedTransactionList")
public class SelectImportDealedTransactionList extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private QuoteInfoService quoteInfoService;

	@RequestMapping(value = "/selectImportDealedTransactionList")
    @ResponseBody
    @Log(type = 71)
	public JSonResponse execMethod(String driverId, String fromSize, String listSize) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			//分页相关
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";

            Map<String, Object> map = new HashMap<String, Object>();
			map.put("driverId", driverId);			
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			@SuppressWarnings("unchecked")
			List<TransactionInfoDomain> list = (List<TransactionInfoDomain>) quoteInfoService.
																selectImportDealedTransactionList(map);
			if(list != null){
				if(list.size() == 0){
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				} else {
                    if (log.isInfoEnabled()) {
                        log.info("查找成功, 共找到" + list.size() + "条数据. ");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "查找成功", list);
				}
			} else {

                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("SelectImportDealedTransactionList.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}
	}

}
