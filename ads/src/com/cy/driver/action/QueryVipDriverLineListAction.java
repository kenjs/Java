package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.VipDriverLineInfoDomain;
import com.cy.driver.service.PactDriverInfoService;
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
 * @description vip车源线路
 * @author 		haoy
 * @since 		2014-9-15
 *
 */
@Scope("prototype")
@Controller("queryVipDriverLineListAction")
public class QueryVipDriverLineListAction extends AuthenticationAction{


	private Logger log = LoggerFactory.getLogger(getClass());
	
    @Resource
	private PactDriverInfoService pactDriverInfoService;

    @RequestMapping(value = "/queryVipDriverLineListAction")
    @ResponseBody
    @Log(type = 76)
	public JSonResponse execMethod(String driverId, String pactDriverId, String fromSize, String listSize) throws Exception {

		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";

            Map<String, String> map = new HashMap<String, String>();
            //map.put("driverId", driverId);
            map.put("pactDriverId",pactDriverId);
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			
			List<VipDriverLineInfoDomain> list = pactDriverInfoService.queryVipDriverLineList(map);
			if(list != null) {
				if(list.size() == 0) {
					return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				} else {
					return JSonResponse.makeHasContentJSonRespone("1", "查找成功", list);
				}
			} else {
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
			log.error("QueryVipDriverLineListAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
		}
	}
}
