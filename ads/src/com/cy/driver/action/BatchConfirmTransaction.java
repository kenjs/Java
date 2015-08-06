package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.dao.TransactionInfoDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * 批量确认订单
 * Created by haoy on 14/12/12.
 */
@Scope("prototype")
@Controller("batchConfirmTransaction")
public class BatchConfirmTransaction extends AuthenticationAction {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoDao transactionInfoDao;

    @RequestMapping(value = "/batchConfirmTransaction")
    @ResponseBody
    public JSonResponse execute(String driverId, String transactionId, String tradeStart) {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            if (StringUtils.isBlank(tradeStart)) {
                return JSonResponse.makeHasContentJSonRespone("-8", "订单状态不能为空");
            }

            if (StringUtils.isNotBlank(transactionId)) {
                String[] ids = transactionId.split(",");
                List<String> list = new ArrayList<String>(Arrays.asList(ids));
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tradeStart", tradeStart);
                map.put("list", list);
                transactionInfoDao.batchUpdateTransactionStatue(map);
            }
            return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
        } catch (Exception e) {
            log.error("订单批量确认失败-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "订单批量确认失败, 请重试。");
        }

    }
}
