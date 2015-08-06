package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.service.NearByCargoListService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 找货
 * Created by Hao.yong on 2015/1/15.
 */
@Scope("prototype")
@Controller("searchCargoAction")
public class SearchCargoAction extends AuthenticationAction {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private NearByCargoListService nearByCargoListService;

    @RequestMapping(value = "/searchCargoAction")
    @ResponseBody
    @Log(type = 83)
    @Count(tableNames = {"t_count_driver_user_busi","t_count_system_busi"},
    columns = {"supply_finds","total_supply_finds"},
    operaType = {0,0})
    public JSonResponse searchCargoAction(OrderCargoInfoDomain domain) {
        try {
            authentication(domain.getDriverId());
            if (!isOk) {
                return jSonResponse;
            }

            if(StringUtils.isNotBlank(domain.getStartTime())){
                if (DateUtil.isEarly(domain.getStartTime(), DateUtil.formatDate2Str(new Date()))) {
                    return  JSonResponse.makeHasContentJSonRespone("-8", "装货时间不能晚于当前时间");
                }
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
            log.error("SearchCargoAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
        }
    }
}
