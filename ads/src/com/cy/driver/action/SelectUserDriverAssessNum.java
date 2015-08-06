package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.AssessDomain;
import com.cy.driver.service.DriverUserAssessInfoService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 查询用户对司机的评价信息
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectUserDriverAssessNum")
public class SelectUserDriverAssessNum extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserAssessInfoService driverUserAssessInfoService;

    @RequestMapping(value = "/selectUserDriverAssessNum")
    @ResponseBody
    @Log(type = 63)
	public JSonResponse execute(String driverId) throws Exception {
		try {
			authentication(driverId);
			if (!isOk) {
				return jSonResponse;
			}

			List<AssessDomain> list = driverUserAssessInfoService.selectUserDriverAssessNum(driverId);
			JSONObject json = new JSONObject();
			if(list != null) {
				int length = list.size();
				switch(length) {
					case 0:
						json.accumulate("highOpinion", "0");
						json.accumulate("average", "0");
						json.accumulate("poor", "0");
						break;
					case 1:
						String level = list.get(0).getScore();
						if("3".equals(level)) {
							json.accumulate("highOpinion", list.get(0).getLevel());
							json.accumulate("average", "0");
							json.accumulate("poor", "0");
						} else if("6".equals(level)) {
							json.accumulate("highOpinion", "0");
							json.accumulate("average", list.get(0).getLevel());
							json.accumulate("poor", "0");
						} else if("9".equals(level)) {
							json.accumulate("highOpinion", "0");
							json.accumulate("average", "0");
							json.accumulate("poor", list.get(0).getLevel());
						}
						break;
					case 2:
						String one = list.get(0).getScore(),two = list.get(1).getScore();
						if("6".equals(one) && "3".equals(two)) {
							json.accumulate("highOpinion", list.get(1).getLevel());
							json.accumulate("average", list.get(0).getLevel());
							json.accumulate("poor", "0");
						} else if("9".equals(one) && "3".equals(two)) {
							json.accumulate("highOpinion", list.get(1).getLevel());
							json.accumulate("average", "0");
							json.accumulate("poor", list.get(0).getLevel());
						} else if("9".equals(one) && "6".equals(two)) {
							json.accumulate("highOpinion", "0");
							json.accumulate("average", list.get(1).getLevel());
							json.accumulate("poor", list.get(0).getLevel());
						}
						break;
					case 3: case 4:
						json.accumulate("highOpinion", list.get(2).getLevel());
						json.accumulate("average", list.get(1).getLevel());
						json.accumulate("poor", list.get(0).getLevel());
						break;
				}
			}
			return JSonResponse.makeHasContentJSonRespone("1", "查询用户对司机的评价信息成功", json.toString());
		} catch (Exception e) {
			log.error("SelectUserDriverAssessNum.class - " + e.getMessage());
			return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
