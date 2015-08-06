package com.cy.driver.action;

import com.cy.driver.bo.DriverCargoAssessInfoBo;
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
 * 点评货源
 * @date 2014-6-11
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("commentCargoInfoAction")
public class CommentCargoInfoAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private OrderCargoInfoService orderCargoInfoService;

    @RequestMapping(value = "/commentCargoInfoAction")
    @ResponseBody
    @Log(type = 31)
	public JSonResponse execute(String driverId, String cargoId, String type, String assessInfo) throws Exception{
		DriverCargoAssessInfoBo bo = new DriverCargoAssessInfoBo();

		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(cargoId)) {
                if (log.isInfoEnabled()) {
                    log.info("货源不存在. ");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "货源不存在");
			} else {
				bo.setCargoId(Integer.parseInt(cargoId));
			}
			if(StringUtils.isBlank(type)) {
                if (log.isInfoEnabled()) {
                    log.info("评价类型不存在. ");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "评价类型不存在");
			} else {
				bo.setType(Integer.parseInt(type));
				if("0".equals(type) && StringUtils.isBlank(assessInfo)) {
                    if (log.isInfoEnabled()) {
                        log.info("请说明原因. ");
                    }
					return JSonResponse.makeHasContentJSonRespone("-8", "请说明原因");
				}
			}
			bo.setAssessInfo(assessInfo);
            bo.setDriverId(Integer.parseInt(driverId));
			
			int checkI = orderCargoInfoService.selectByDriverAndCargoId(driverId, cargoId);
			if(checkI == 0) {			
				int i = orderCargoInfoService.commentCargoInfo(bo);
				if(i != 0){
                    if (log.isInfoEnabled()) {
                        log.info("点评货源成功. ");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "点评货源成功");
				}else{
                    if (log.isInfoEnabled()) {
                        log.info("点评货源失败. ");
                    }
					return JSonResponse.makeHasContentJSonRespone("-8", "点评货源失败");
				}
			} else {
				int updateI = orderCargoInfoService.updateAssess(bo);
				if(updateI != 0){
                    if (log.isInfoEnabled()) {
                        log.info("修改点评货源成功. ");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "修改点评货源成功");
				}else{
                    if (log.isInfoEnabled()) {
                        log.info("修改点评货源失败. ");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "修改点评货源失败");
				}
			}
		} catch (Exception e) {
			log.error("点评货源失败-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "点评货源失败，请重试。");
		}

	}

}
