package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.domain.WebUserInfoDomain;
import com.cy.driver.service.OrderCargoInfoService;
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
 * 货单确认
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("receiptVerifyAction")
public class ReceiptVerifyAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private TransactionInfoService transactionInfoService;
    @Resource
	private OrderCargoInfoService orderCargoInfoService;

	@RequestMapping(value = "/receiptVerify")
    @ResponseBody
    @Log(type = 67)
	public JSonResponse execMethod(String driverId, String deployUserId, String transactionId, String shipperCompanyCode, String shipperCompanyId) throws Exception {
		try {

			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if(StringUtils.isBlank(transactionId)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "交易ID不能为空. ");
			}

			WebUserInfoDomain domain = null;//公司信息domain
			
			if(StringUtils.isNotBlank(deployUserId) && StringUtils.isNotBlank(shipperCompanyCode)) {
				domain = transactionInfoService.selectShipperCompanyId(deployUserId, shipperCompanyCode);
			}
			
			//如果订单已经完成或取消或已卸货或异常取消或web端取消, 无法继续操作
			Map<String,Long> mapStart = transactionInfoService.selectTransactionStartById(transactionId);
			if(mapStart != null) {
				if(mapStart.containsKey("tradeStart")) {
					long start = mapStart.get("tradeStart");
					if(start == 5) {
                        if (log.isInfoEnabled()) {
                            log.info("id为" + transactionId + "的交易已经完成，无法取消");
                        }
						return JSonResponse.makeHasContentJSonRespone("0", "该交易已经完成。");
					}
					if(start == 6) {
                        if (log.isInfoEnabled()) {
                            log.info("id为" + transactionId + "的交易已经取消");
                        }
						return JSonResponse.makeHasContentJSonRespone("0", "该交易已经取消");
					}
					if(start == 7) {
                        if (log.isInfoEnabled()) {
                            log.info("id为" + transactionId + "的交易司机已卸货，无法取消");
                        }
						return JSonResponse.makeHasContentJSonRespone("0", "该交易司机已卸货。");
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
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", transactionId);
			map.put("driverId", driverId);
			map.put("tradeStart", "3");
			map.put("shipperCompanyCode", shipperCompanyCode);
			
			if(domain != null && StringUtils.isBlank(shipperCompanyId)) {
				
				if("0".equals(domain.getParentId())) {
					map.put("contactTelephone", domain.getMobilephone());
					map.put("contactName", domain.getName());
				} else {
					map.put("contactTelephone", domain.getContactTelephone());
					map.put("contactName", domain.getContactName());
				}
				
				shipperCompanyId = domain.getId();
			}
			
			map.put("shipperCompanyId", shipperCompanyId);
			
			int result = transactionInfoService.updateTransactionInfoById(map);
			if(result == 0){
                if (log.isInfoEnabled()) {
                    log.info("货单确认失败.");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "货单确认失败");
			}else{
				String requestStartTime = DateUtil.getCurrentDateTime();				
				map.put("cargoFlag", "1");
				map.put("requestStartTime", requestStartTime);
				orderCargoInfoService.updateCargoInfo(map);

                if (log.isInfoEnabled()) {
                    log.info("货单确认成功.");
                }
                return JSonResponse.makeHasContentJSonRespone("1", "货单确认成功");
			}
		} catch (Exception e) {
			log.error("ReceiptVerifyAction.class - " + e.getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "货单确认失败。");
		}
	}

}
