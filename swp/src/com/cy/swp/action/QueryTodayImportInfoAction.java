package com.cy.swp.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.domain.PageInfo;
import com.cy.swp.service.OrderCargoInfoService;


/**
 * 查询今日工作动态
 * @author zdy
 *
 */
@Scope("prototype")
@Controller("queryTodayImportInfoAction")
public class QueryTodayImportInfoAction extends BaseAction {
    private static final long serialVersionUID = 6601989558296876680L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource 
    private OrderCargoInfoService orderCargoInfoService;
    
    @RequestMapping("/queryTodayImportInfo.jspx")
    protected ModelAndView execMethod(OrderCargoInfoDomain orderCargoInfoDomain,String flag,String mark) throws Exception {
    	ModelAndView modelAndView=new ModelAndView();
    	if (getSessionUser() == null) {
    		modelAndView.setViewName("login");//LOGIN;
            return modelAndView;
        }
    	logger.debug("query today import info begin . userId=[{}]",
                getSessionUser().getId());
    	 //参数验证
        if (orderCargoInfoDomain == null) {
            orderCargoInfoDomain = new OrderCargoInfoDomain();
        }
        if (orderCargoInfoDomain.getPageInfo() == null || "0".equals(flag)) {//0是点击搜索按钮
            orderCargoInfoDomain.setPageInfo(new PageInfo());
        }
        
        //两个层（今天和历史）的分页
        OrderCargoInfoDomain orderCargoInfoDomains=new OrderCargoInfoDomain();
        orderCargoInfoDomains.setPageInfo(new PageInfo());
        
    	
        if(StringUtils.isEmpty(orderCargoInfoDomain.getMark())){//mark==0表示今天导入的
        	orderCargoInfoDomain.setMark("0");
        	//logger.debug("parameter error,not null or ''");
        }
        //数据库中查询
        List<OrderCargoInfoDomain> list=orderCargoInfoService.queryTodayImportInfo(String.valueOf(getSessionUser().getId()), orderCargoInfoDomain);
        orderCargoInfoDomain.setList(list);
        modelAndView.addObject("mark",mark);
        modelAndView.addObject("orderCargoInfoDomains",orderCargoInfoDomains);
        modelAndView.addObject("orderCargoInfoDomain",orderCargoInfoDomain);
    	modelAndView.setViewName("index/index");
    	return modelAndView;
    }
}
