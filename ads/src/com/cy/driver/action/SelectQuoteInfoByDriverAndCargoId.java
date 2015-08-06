package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.QuoteInfoDomain;
import com.cy.driver.service.QuoteInfoService;
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
 * 根据货源id和司机id查找报价信息 
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectQuoteInfoByDriverAndCargoId")
public class SelectQuoteInfoByDriverAndCargoId extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private QuoteInfoService quoteInfoService;

	@RequestMapping(value = "/selectQuoteInfoByDriverAndCargoId")
    @ResponseBody
    @Log(type = 52)
	public JSonResponse execute(String driverId, String cargoId) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			if(StringUtils.isBlank(cargoId)) {
                if (log.isInfoEnabled()) {
                    log.info("货源不存在");
                }
				return JSonResponse.makeHasContentJSonRespone("-8", "货源不存在");
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cargoId", cargoId);
			map.put("driverId", driverId);
			QuoteInfoDomain domain = quoteInfoService.selectQuoteInfoByDriverAndCargoId(map);
			if(domain != null) {
                if (log.isInfoEnabled()) {
                    log.info("根据货源id和司机id查找报价信息 成功");
                }
				return JSonResponse.makeHasContentJSonRespone("1", "报价信息查找成功", domain);
			} else {
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("SelectQuoteInfoByDriverAndCargoId.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
