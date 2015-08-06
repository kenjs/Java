package com.cy.driver.action;

import com.cy.driver.bo.DriverUserAssessInfoBo;
import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.countevent.Count;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.DriverUserAssessInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 新增货源评价
 * @date 2014-6-9
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("addNewDriverUserAssessAction")
public class AddNewDriverUserAssessAction extends AuthenticationAction{
// ------------------------------ FIELDS ------------------------------

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private DriverUserAssessInfoService driverUserAssessInfoService;

// -------------------------- OTHER METHODS --------------------------

    @RequestMapping(value = "/addNewDriverUserAssessAction")
    @ResponseBody
    @Log(type = 42)
    @Count(tableNames = {"t_count_web_user_busi","t_count_web_user_busi", "t_count_web_user_busi","t_count_driver_user_busi"},
            columns = {"good_evaluates", "general_evaluates" , "bad_evaluates", "no_assessment_orders"},
            operaType = {0,0,0,1})
    public JSonResponse execute(String driverId, DriverUserAssessInfoBo bo) throws Exception{
		try {

            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(bo.getAssessEvaluateScore() + "")){
				return JSonResponse.makeHasContentJSonRespone("-8", "请选择评分");
			} else {
				if(bo.getAssessEvaluateScore() == 9
													&& StringUtils.isBlank(bo.getAssess())) {
					return JSonResponse.makeHasContentJSonRespone("-8", "请说明差评原因");
				}
			}
			if(StringUtils.isBlank(bo.getCargoId() + "")){
				return JSonResponse.makeHasContentJSonRespone("-8", "货物不存在");
			}

			if(StringUtils.isBlank(bo.getUserId() + "")){
				return JSonResponse.makeHasContentJSonRespone("-8", "用户不存在");
			}
			if(StringUtils.isBlank(bo.getTransactionId() + "")){
				return JSonResponse.makeHasContentJSonRespone("-8", "交易订单不存在");
			}

			int key = driverUserAssessInfoService.selectAssessNum(bo);
			if(key == 0) {
				int i = driverUserAssessInfoService.addNewDriverUserAssessInfo(bo);
				if(i == 0){
                    if (log.isInfoEnabled()) {
                        log.info("评价货源失败");
                    }
					return JSonResponse.makeHasContentJSonRespone("-8", "评价货源失败");
				}else{
                    if (log.isInfoEnabled()) {
                        log.info("评价货源成功");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "评价货源成功");
				}
			} else {
				bo.setId(key);
				int j = driverUserAssessInfoService.updateDriverUserAssessInfo(bo);
				if(j == 0){
                    if (log.isInfoEnabled()) {
                        log.info("修改货源评价失败");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "修改货源评价失败");
				}else{
                    if (log.isInfoEnabled()) {
                        log.info("修改货源评价成功");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "修改货源评价成功");
				}
			}
		} catch (Exception e) {
			log.error("货源评价出错了-" + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "出错了，请稍后重试。");
		}
	}
}
