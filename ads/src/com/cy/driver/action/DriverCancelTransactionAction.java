package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.TransactionLastInfoBo;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 司机异常取消订单
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverCancelTransactionAction")
public class DriverCancelTransactionAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoService transactionInfoService;

	@RequestMapping(value = "/driverCancelTransaction")
    @ResponseBody
    @Log(type = 55)
	public JSonResponse execute(String driverId, String transactionId, String cancelReason, String note) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(transactionId)) {
                if (log.isInfoEnabled()) {
                    log.info("交易不存在");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "交易不存在");
			}
			//如果订单状态已完成或异常取消或web端取消
			Map<String,Long> mapStart = transactionInfoService.selectTransactionStartById(transactionId);
			if(mapStart != null) {
				if(mapStart.containsKey("tradeStart")) {
					if(mapStart.get("tradeStart") == 5) {
                        if (log.isInfoEnabled()) {
                            log.info("id为" + transactionId + "的交易已经完成，无法取消");
                        }
						return JSonResponse.makeHasContentJSonRespone("0", "该交易已经完成，无法取消。");
					}
				}
				if(mapStart.containsKey("tradeCancelOrigin")) {
					long origin = mapStart.get("tradeCancelOrigin");
					if(origin == 1) {
                        if (log.isInfoEnabled()) {
                            log.info("id为" + transactionId + "的交易已经被司机取消");
                        }
						return JSonResponse.makeHasContentJSonRespone("0", "该交易已经被司机取消");
					}
					if(origin == 2 ) {
                        if (log.isInfoEnabled()) {
                            log.info("id为" + transactionId + "的交易已经被货主取消");
                        }
						return JSonResponse.makeHasContentJSonRespone("0", "该交易已经被货主取消");
					}
				}
			}
			
			TransactionInfoDomain domain = transactionInfoService.selectDriverOrderDetail(transactionId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", transactionId);
//			map.put("tradeStart", "6");
//			map.put("orderStart", "1");
			map.put("tradeCancelOrigin", "1");
			int i = transactionInfoService.updateTransactionInfoById(map);
			if(i != 0) {
                if (log.isInfoEnabled()) {
                    log.info("司机取消订单成功");
                }

				TransactionLastInfoBo bo = new TransactionLastInfoBo();
				bo.setTransactionId(transactionId);
				bo.setRemark(cancelReason + ";" + note);
				bo.setCargoId(domain.getCargoId());
				bo.setDriverId(domain.getDriverId());
				transactionInfoService.addTransactionLastInfo(bo);

                return JSonResponse.makeHasContentJSonRespone("1", "司机取消订单成功");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("司机取消订单失败");
                }

				return JSonResponse.makeHasContentJSonRespone("0", "司机取消订单失败");
			}
		} catch (Exception e) {
			log.error("司机取消订单失败 - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统出错，请重试。");
		}

	}

}
