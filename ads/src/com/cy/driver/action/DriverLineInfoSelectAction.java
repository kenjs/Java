package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.DriverLineInfoDomain;
import com.cy.driver.service.DriverLineInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 查找营运线路
 * @date 2014-6-3
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("driverLineInfoSelectAction")
public class DriverLineInfoSelectAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverLineInfoService driverLineInfoService;

    @RequestMapping(value = "/driverLineInfoSelect")
    @ResponseBody
    @Log(type = 25)
	public JSonResponse execute(String driverId) throws Exception {
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
			if(list != null){
				if(list.size() == 0){
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				}else {
                    if (log.isInfoEnabled()) {
                        log.info("查找成功, 一共找到"+ list.size() +"数据");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "查找成功.", list);
				}
			}else {
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
			log.error("查找运营线路出错." + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
		}

	}

}
