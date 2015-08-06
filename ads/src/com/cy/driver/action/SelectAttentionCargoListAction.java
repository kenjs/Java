package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.service.QuoteInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 我关注的货源
 * @date 2014-6-11
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectAttentionCargoListAction")
public class SelectAttentionCargoListAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private QuoteInfoService quoteInfoService;

    @RequestMapping(value = "/selectAttentionCargoListAction")
    @ResponseBody
    @Log(type = 40)
	public JSonResponse execute(String driverId, String fromSize, String listSize) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            //分页相关
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			List<OrderCargoInfoDomain> list = (List<OrderCargoInfoDomain>) quoteInfoService.selectAttentionCargoList(driverId,fromSize,listSize);
			if(list != null){
				if(list.size() == 0){
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				}else{
                    if (log.isInfoEnabled()) {
                        log.info("查找成功,共找到" + list.size() + "条数据");
                    }
					return JSonResponse.makeHasContentJSonRespone("1", "查找成功", list);
				}
			}else{
                if (log.isInfoEnabled()) {
                    log.info("未找到符合条件的信息");
                }
				return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {
            log.error("SelectAttentionCargoListAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
