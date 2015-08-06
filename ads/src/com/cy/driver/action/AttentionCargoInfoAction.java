package com.cy.driver.action;

import com.cy.driver.bo.DriverCargoCollectInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.OrderCargoInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 关注货源
 * @date 2014-6-11
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("attentionCargoInfoAction")
public class AttentionCargoInfoAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private OrderCargoInfoService orderCargoInfoService;

    @RequestMapping(value = "/attentionCargoInfoAction")
    @ResponseBody
    @Log(type = 30)
	public JSonResponse execute(String driverId, String cargoId) throws Exception {
		DriverCargoCollectInfoBo bo = new DriverCargoCollectInfoBo();
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            bo.setDriverId(Integer.parseInt(driverId));
			if(StringUtils.isBlank(cargoId)){
                if (log.isInfoEnabled()) {
                    log.info("货源不存在. ");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "货源不存在");
			} else {
				bo.setCargoId(Integer.parseInt(cargoId));
			}
			//判断给条货物是否已关注
			boolean isAtten = orderCargoInfoService.cargoIsAttention(driverId, cargoId);
			if(isAtten) {
                if (log.isInfoEnabled()) {
                    log.info("ID为 " + cargoId + "的货物已经关注");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "您已经关注此条货源");
			}
			int i = orderCargoInfoService.attentionCargoInfo(bo);
			if(i != 0){
                if (log.isInfoEnabled()) {
                    log.info("关注货源成功. ");
                }
				return JSonResponse.makeHasContentJSonRespone("1", "关注货源成功");
			}else{
                if (log.isInfoEnabled()) {
                    log.info("关注货源失败. ");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "关注货源失败");
			}
		} catch (Exception e) {
			log.error("关注货源失败-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "关注货源失败，请重试。");
		}

	}

}
