package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.LoginUserInfoService;
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
 * 百度云推送设置channelID和userID 
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("setBaiduPushIdAction")
public class SetBaiduPushIdAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private LoginUserInfoService loginUserInfoService;

    @RequestMapping(value = "/setBaiduPushId")
    @ResponseBody
    @Log(type = 62)
	public JSonResponse execute(String driverId, String baiduChannelId, String baiduUserId, String registrationId) throws Exception{
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			if (StringUtils.isBlank(registrationId) && StringUtils.isBlank(baiduChannelId) && StringUtils.isBlank(baiduUserId)) {
				return JSonResponse.makeHasContentJSonRespone("-8", "百度推送或极光推送设置参数不能为空");
			}

			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtils.isNotBlank(baiduChannelId)) {
				map.put("baiduChannelId", Long.parseLong(baiduChannelId));
			}			
			map.put("baiduUserId", baiduUserId);
			map.put("registrationId", registrationId);
			map.put("id", driverId);
			int i = loginUserInfoService.updateBaiduPushId(map);
			if(i != 0) {
				//sendResponseToJson("1", "设置成功");
                return JSonResponse.makeHasContentJSonRespone("1", "设置成功");
			} else {
				//sendResponseToJson("0", "设置失败");
                return JSonResponse.makeHasContentJSonRespone("0", "设置失败");
			}
		} catch (Exception e) {
			log.error("SetBaiduPushIdAction.class - " + e.getCause().getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "系统出错。");
		}

	}

}
