package com.cy.driver.action;

import com.cy.driver.bo.JSonResponse;
import com.cy.driver.common.syslog.Log;
import com.cy.driver.service.PactDriverInfoService;
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
 * Created by Administrator on 2014/9/16.
 */
@Scope("prototype")
@Controller("updateVipDriverLineAction")
public class UpdateVipDriverLineAction extends AuthenticationAction{

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private PactDriverInfoService pactDriverInfoService;

    @RequestMapping(value = "/updateVipDriverLineAction")
    @ResponseBody
    @Log(type = 77)
    public JSonResponse execMethod(String driverId, String id, String start) throws Exception {
        try {
            authentication(driverId);
            if (!isOk) {
                return jSonResponse;
            }

            if(StringUtils.isBlank(id)) {
                return  JSonResponse.makeHasContentJSonRespone("-8", "必须参数合同车源ID缺失");
            }

            if(StringUtils.isBlank(start)) {
                return  JSonResponse.makeHasContentJSonRespone("-8", "必须参数合同车源状态缺失");
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",id);
            map.put("start" ,start);

            int rst = pactDriverInfoService.updateVipDriverLineInfo(map);
            if(rst == 1) {
                return JSonResponse.makeHasContentJSonRespone("1", "操作成功");
            } else {
                return JSonResponse.makeHasContentJSonRespone("0", "操作失败");
            }
        } catch (Exception e) {
            log.error("UpdateVipDriverLineAction.class - " + e.getMessage());
            return JSonResponse.makeHasContentJSonRespone("-8", "系统出错, 请稍后重试。");
        }
    }

}
