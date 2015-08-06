package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.dao.TransactionInfoDao;
import com.cy.driver.domain.TransactionInfoDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 按公司分组查询当前司机的订单信息
 * Created by haoy on 2014/12/12.
 */
@Scope("prototype")
@Controller("queryDriverUserTransactionInfo")
public class QueryDriverUserTransactionInfo extends AuthenticationAction {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoDao transactionInfoDao;

    @RequestMapping(value = "/queryDriverUserTransactionInfo")
    @ResponseBody
    public JSonResponse execute(String driverId) {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            List<TransactionInfoDomain> list = transactionInfoDao.queryTransactionGroupByCompany(driverId);
            if (list.size() == 0) {
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
            }
            return JSonResponse.makeHasContentJSonRespone("1", "查找成功", list);
        } catch (Exception e) {
            log.error("QueryDriverUserTransactionInfo.class" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
        }
    }
}
