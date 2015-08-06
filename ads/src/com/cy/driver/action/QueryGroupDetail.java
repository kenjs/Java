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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询分组详情
 * Created by haoy on 2014/12/12.
 */
@Scope("prototype")
@Controller("queryGroupDetail")
public class QueryGroupDetail extends AuthenticationAction {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private TransactionInfoDao transactionInfoDao;

    @RequestMapping(value = "/queryGroupDetail")
    @ResponseBody
    public JSonResponse execute(String driverId, String companyId) {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            Map<String, String> map = new HashMap<String, String>();
            map.put("driverId", driverId);
            map.put("companyId", companyId);
            List<TransactionInfoDomain> list = transactionInfoDao.queryGroupDetail(map);
            if (list.size() == 0) {
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
            }
            return JSonResponse.makeHasContentJSonRespone("1", "查找成功", list);
        } catch (Exception e) {
            log.error("QueryGroupDetail.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
        }
    }
}
