package com.cy.swp.action;

import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.service.MatchingService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by nixianjing on 15/1/9.
 */
@Scope("prototype")
@Controller("matchingAction")
public class MatchingAction extends BaseAction {


    @Resource
    private MatchingService matchingService;

    @RequestMapping("/matchingTextMassges.jspx")
    @ResponseBody
    public JSonRespone matchingTextMassges() {
        boolean result = false;
        try{
            if(matchingService.matchingDriverNote()) {
                return JSonRespone.makeHasContentJSonRespone("1", "导入货源匹配车辆成功！");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return JSonRespone.makeHasContentJSonRespone("-1", "导入货源匹配车辆出现错误！");
    }


}
