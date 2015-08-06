package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.bo.TransactionLastInfoBo;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.dao.OrderCargoInfoDao;
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

@Scope("prototype")
@Controller("cancelConfirmUseCarAction")
public class CancelConfirmUseCarAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private TransactionInfoService transactionInfoService;
    @Resource
	private OrderCargoInfoDao orderCargoInfoDao;

	@RequestMapping(value = "/cancelConfirmUseCarAction")
    @ResponseBody
    @Log(type = 36)
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
			TransactionLastInfoBo bo = new TransactionLastInfoBo();
			bo.setTransactionId(id);		
			
			//如果交易已经完成或取消或异常取消或web端取消
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
			}else{
				transactionInfoService.addTransactionLastInfo(bo);

                if (log.isInfoEnabled()) {
                    log.info("修改成功.");
                }

				if("6".equals(tradeStart)) {
					map.put("cargoFlag", "0");
					orderCargoInfoDao.updateCargoInfo(map);
				}
                return JSonResponse.makeHasContentJSonRespone("1", "修改成功");
            }
		} catch (Exception e) {
			log.error("CancelConfirmUseCarAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统异常，请稍后重试。");
		}

	}

}
