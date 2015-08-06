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
import java.util.List;

/**
 * 司机订单列表
 * @date 2014-6-5
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("transactionInfoListAction")
public class TransactionInfoListAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoService transactionInfoService;

	@RequestMapping(value = "/transactionInfoListAction")
    @ResponseBody
    @Log(type = 33)
	public JSonResponse execute(String driverId, String fromSize, String listSize) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			//分页相关
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			List<TransactionInfoDomain> list = (List<TransactionInfoDomain>) transactionInfoService.
												selectDriverOrderList(driverId,fromSize,listSize);
			if(list != null){
				if(list.size() == 0){
					//log.info("未找到符合条件的信息");
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
                    return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				}else{
					//log.info("查找成功, 共找到"+ list.size() +"条数据");
                    if (log.isInfoEnabled()) {
                        log.info("查找成功, 共找到"+ list.size() +"条数据");
                    }
                    return JSonResponse.makeHasContentJSonRespone("1", "查找成功.", list);
				}
			}else {
				//log.info("未找到符合条件的信息");
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}			
		} catch (Exception e) {
            log.error("TransactionInfoListAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
