package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.service.OrderCargoInfoService;
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
 * 根据司机线路查找符合条件的货物
 * @date 2014-6-5
 * @author haoyong
 *
 */
@Scope("prototype")
@Controller("selectCargoListByDriverLineAction")
public class SelectCargoListByDriverLineAction extends AuthenticationAction{

	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
	private OrderCargoInfoService orderCargoInfoService;

    @RequestMapping(value = "/selectCargoListByDriverLineAction")
    @ResponseBody
    @Log(type = 28)
	public JSonResponse execute(String driverId, String fromSize, String listSize) throws Exception{
		try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

			//分页相关
//			String fromSize = request.getParameter("fromSize");
//			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			List<OrderCargoInfoDomain> list = orderCargoInfoService.selectCargoListByDriverLine(driverId,fromSize,listSize);
			if(list != null){
				if(list.size() == 0){
                    if (log.isInfoEnabled()) {
                        log.info("未找到符合条件的信息");
                    }
					return JSonResponse.makeHasContentJSonRespone("0", "未找到符合条件的信息");
				}else {
                    if (log.isInfoEnabled()) {
                        log.info("查找成功，共找到"+ list.size() +"条数据");
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
            log.error("SelectCargoListByDriverLineAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "查询出错, 请稍后重试。");
		}

	}

}
