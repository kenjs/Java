package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.OrderCargoInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 根据司机线路查找符合条件的货物数量
 * @date 2014-6-5
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectCargoNumByDriverLineAction")
public class SelectCargoNumByDriverLineAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private OrderCargoInfoService orderCargoInfoService;

    @RequestMapping(value = "/selectCargoNumByDriverLineAction")
    @ResponseBody
    @Log(type = 27)
	public JSonResponse execute(String driverId) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			int count = orderCargoInfoService.selectCargoNumByDriverLine(driverId);
			if(count == 0){
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			} else {
                if (log.isInfoEnabled()) {
                    log.info("查找成功");
                }
				return JSonResponse.makeHasContentJSonRespone("1", "查找成功", count);
			}
		} catch (Exception e) {
            log.error("SelectCargoNumByDriverLineAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}