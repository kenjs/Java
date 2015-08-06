package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.service.OrderCargoInfoService;
import org.apache.commons.lang.StringUtils;
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
 * 查找货源
 * @date 2014-6-4
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectCargoListAction")
public class SelectCargoListAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private OrderCargoInfoService orderCargoInfoService;


    @RequestMapping(value = "/selectCargoListAction")
    @ResponseBody
    @Log(type = 26)
    @Count(tableNames = {"t_count_driver_user_busi","t_count_system_busi"}, columns = {"supply_finds","total_supply_finds"},
           operaType = {0,0})
	public JSonResponse execute(String driverId, String startTime, String endTime, String startProvince, String endProvince,
                               String startCity, String endCity, String carType, String carLength, String companyName,
                               String fromSize, String listSize) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            if(StringUtils.isBlank(startTime)){
                if (log.isInfoEnabled()) {
                    log.info("查找失败, 用户没有选择装货时间");
                }
                return  JSonResponse.makeHasContentJSonRespone("-8", "用户没有选择装货时间");
            }

			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";

            if (StringUtils.isNotBlank(carLength)) {
                carLength = carLength.trim().replaceAll(" ","");
            }

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("startProvince", startProvince);
            map.put("endProvince", endProvince);
            map.put("startCity", startCity);
            map.put("endCity", endCity);
            map.put("carType", carType);
            map.put("carLength", carLength);
            map.put("companyName", companyName);
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			List<OrderCargoInfoDomain> list = orderCargoInfoService.selectCargoList(map);
			if(list != null){
				if(list.size() == 0){
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				}else{
                    if (log.isInfoEnabled()) {
                        log.info("查找成功, 共找到"+ list.size() +"条数据");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "查找成功", list);
				}
			}else{
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("SelectCargoListAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
