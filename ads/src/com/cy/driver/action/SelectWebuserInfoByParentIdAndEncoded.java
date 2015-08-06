package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.WebUserInfoDomain;
import com.cy.driver.service.WebUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据parentId和encoded查询
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectWebuserInfoByParentIdAndEncoded")
public class SelectWebuserInfoByParentIdAndEncoded extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private WebUserInfoService webUserInfoService;

	@RequestMapping(value = "/selectWebuserInfoByParentIdAndEncoded")
	@ResponseBody
    @Log(type = 65)
	protected JSonResponse execMethod(String driverId, String parentId, String encoded) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if(StringUtils.isBlank(parentId) || StringUtils.isBlank(encoded)) {
				//sendResponseToJson("-8", "所属物流企业或者编码代码不能为空");
				return JSonResponse.makeHasContentJSonRespone("-8", "所属物流企业或者编码代码不能为空");
			}

			Map<String,String> map = new HashMap<String,String>();
			map.put("parentId", parentId);
			map.put("encoded", encoded);

			WebUserInfoDomain domain = webUserInfoService.selectWebuserInfoByParentIdAndEncoded(map);
			if(domain == null) {
				//sendResponseToJson("0", "未找到符合条件的信息");
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
			//sendResponseToJson("1", "查找成功",domain);
            return JSonResponse.makeHasContentJSonRespone("1", "查找成功", domain);
		} catch (Exception e) {
			log.error("SelectWebuserInfoByParentIdAndEncoded.class - " + e.getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
