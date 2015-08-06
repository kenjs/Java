package com.cy.driver.action;


import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.service.NearByCargoListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Haoyong on 2015/1/14.
 */
@Scope("prototype")
@Controller("nearByCargoListAction")
public class NearByCargoListAction extends AuthenticationAction {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private NearByCargoListService nearByCargoListService;

    @RequestMapping(value = "/queryNearByCargoList")
    @ResponseBody
    @Log(type = 84)
    public JSonResponse queryNearByCargoList(OrderCargoInfoDomain domain) {
        try {
            authentication(domain.getDriverId());
            if (!isOk) {
                return jSonResponse;
            }

            List<OrderCargoInfoDomain> list = nearByCargoListService.searchCargo(domain);
            if(list.size() == 0){
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
                return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
            }
            if (log.isInfoEnabled()) {
                log.info("查找成功, 共找到"+ list.size() +"条数据");
            }
            return JSonResponse.makeHasContentJSonRespone("1", "查找成功", list);
        } catch (Exception e) {
            log.error("查询出错-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8","查询出错, 请稍后重试。");
        }
    }

}
