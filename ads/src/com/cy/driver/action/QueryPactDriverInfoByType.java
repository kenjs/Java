package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.PactDriverInfoDomain;
import com.cy.driver.service.PactDriverInfoService;
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
 * @description 合同车源查询
 * @author 		haoy
 * @since 		2014-8-27
 *
 */
@Scope("prototype")
@Controller("queryPactDriverInfoByType")
public class QueryPactDriverInfoByType extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private PactDriverInfoService pactDriverInfoService;

	@RequestMapping(value = "/queryPactDriverInfoByType")
    @ResponseBody
    @Log(type = 74)
	public JSonResponse execMethod(String driverId, String userId, String pactStart, String pactType,
                                  String fromSize, String listSize) throws Exception {

		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			Map<String, String> map = new HashMap<String, String>();
			map.put("driverId", driverId);
            map.put("userId",userId);
			map.put("pactStart", pactStart);
			map.put("pactType", pactType);
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);

			List<PactDriverInfoDomain> list = pactDriverInfoService.selectPactDriverInfoList(map);
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
			log.error("QueryPactDriverInfoByType.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询失败，请重试。");
		}
	}

}
