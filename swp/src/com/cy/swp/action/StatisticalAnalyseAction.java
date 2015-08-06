package com.cy.swp.action;

import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.domain.MarketingDriverInfoDomain;
import com.cy.swp.service.MarketingUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.*;
import java.util.Map;

/**
 * 统计分析action
 * Created by wyh on 2015/1/6.
 */
@Scope("prototype")
@Controller("statisticalAnalyseAction")
public class StatisticalAnalyseAction extends BaseAction{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MarketingUserInfoService marketingUserInfoService;

    /**
     * 进入统计分析页面
     * @author wyh
     */
    @RequestMapping("/enterStatisticalAnalyse.jspx")
    public ModelAndView enterStatisticalAnalyse(ModelAndView view,MarketingDriverInfoDomain info) throws Exception  {
        String menuAId = Constants.MENU_AID_COUNT_ANALYSE;//菜单id
        view.addObject("menuAId", menuAId);
        try {
            MarketingUserInfo user = getSessionUser();
            if (user == null) {
                view.setViewName("login");
                return view;
            }
            Map<String, Object> rtMap = marketingUserInfoService.enterStatisticalAnalyseServ(info);
            if("1".equals(rtMap.get("result").toString())){
                view.addObject("errorCode", "1");
                view.addObject("errorMessage", rtMap.get("message").toString());
            }
        } catch (Exception e) {
            view.addObject("errorCode", "1");
            view.addObject("errorMessage", "系统出错！");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        view.addObject("info", info);
        view.setViewName("reportForms/statisticalAnalyse");
        return view;
    }
}
