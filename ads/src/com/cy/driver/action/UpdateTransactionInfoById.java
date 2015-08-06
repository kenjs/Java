package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.TransactionLastInfoBo;
import com.cy.driver.common.countevent.Count;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 更改订单状态（卸货）
 * @date 2014-6-11
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("updateTransactionInfoById")
public class UpdateTransactionInfoById extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoService transactionInfoService;

    @RequestMapping(value = "/unloadCargoAction")
    @ResponseBody
    @Log(type = 35)
    @Count(tableNames = {"t_count_driver_user_busi","t_count_driver_user_busi","t_count_driver_user_busi"},
            columns = {"transactions", "no_assessment_orders","no_confirm_orders"})
	public JSonResponse execute(String driverId, String id, String tradeStart) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(id)){
                if (log.isInfoEnabled()) {
                    log.info("交易不存在.");
                }
                return JSonResponse.makeHasContentJSonRespone("-8", "交易不存在");				
			}
			if(StringUtils.isBlank(tradeStart)){
				return JSonResponse.makeHasContentJSonRespone("-8", "交易状态未知");
			}
			//如果订单已经完成或取消或已卸货或异常取消或web端取消, 无法继续操作
			Map<String,Long> mapStart = transactionInfoService.selectTransactionStartById(id);
			if(mapStart != null) {
				if(mapStart.containsKey("tradeStart")) {
					long start = mapStart.get("tradeStart");
					if(start == 5) {						
                        if (log.isInfoEnabled()) {
                            log.info("id为" + id + "的交易已经完成，无法取消");
                        }
                        return JSonResponse.makeHasContentJSonRespone("0", "该交易已经完成，无法取消。");
					}
					if(start == 6) {						
                        if (log.isInfoEnabled()) {
                            log.info("id为" + id + "的交易已经取消");
                        }
                        return JSonResponse.makeHasContentJSonRespone("0", "该交易已经取消");
					}
					if(start == 7) {						
                        if (log.isInfoEnabled()) {
                            log.info("id为" + id + "的交易司机已卸货，无法取消");
                        }
                        return JSonResponse.makeHasContentJSonRespone("0", "该交易司机已卸货，无法取消。");
					}
				}
				if(mapStart.containsKey("tradeCancelOrigin")) {
					long origin = mapStart.get("tradeCancelOrigin");
					if(origin == 1) {						
                        if (log.isInfoEnabled()) {
                            log.info("id为" + id + "的交易已经被司机取消");
                        }
                        return JSonResponse.makeHasContentJSonRespone("0", "该交易已经被司机取消");
					}
					if(origin == 2 ) {						
                        if (log.isInfoEnabled()) {
                            log.info("id为" + id + "的交易已经被货主取消");
                        }
                        return JSonResponse.makeHasContentJSonRespone("0", "该交易已经被货主取消");
					}
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("tradeStart", tradeStart);
			int i = transactionInfoService.updateTransactionInfoById(map);
			if(i == 0){
                if (log.isInfoEnabled()) {
                    log.info("修改失败.");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "修改失败");
				//log.info("修改失败.");
			}else{
                if (log.isInfoEnabled()) {
                    log.info("修改成功.");
                }
                
                TransactionLastInfoBo bo = new TransactionLastInfoBo();
    			bo.setTransactionId(id);
                transactionInfoService.addTransactionLastInfo(bo);
                
                return JSonResponse.makeHasContentJSonRespone("1", "修改成功");
				//log.info("修改成功.");
			}
		} catch (Exception e) {
            log.error("UpdateTransactionInfoById.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统出错, 请稍后重试。");
		}
	}

}
