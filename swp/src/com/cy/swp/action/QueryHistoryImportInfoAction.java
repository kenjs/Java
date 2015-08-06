package com.cy.swp.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.domain.PageInfo;
import com.cy.swp.service.OrderCargoInfoService;

/**
 * 查询历史匹配不成功的有效货源对应的公司信息
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("queryHistoryImportInfoAction")
public class QueryHistoryImportInfoAction extends BaseAction{
	 private static final long serialVersionUID = 669803459457232348L;
	 private Logger logger = LoggerFactory.getLogger(getClass());
	
	 @Resource
	 private OrderCargoInfoService orderCargoInfoService;
	 
	 @RequestMapping("/queryHistoryImportInfo.jspx")
	protected ModelAndView execMethod(OrderCargoInfoDomain orderCargoInfoDomains,String flag,String mark) throws Exception {
		 ModelAndView modelAndView=new ModelAndView();
	    	if (getSessionUser() == null) {
	    		modelAndView.setViewName("login");//LOGIN;
	            return modelAndView;
	        }
	        logger.debug("query history import company info  begin. userId=[{}]",
	                getSessionUser().getId());
	        
	        //参数验证
	        if (orderCargoInfoDomains == null) {
	        	orderCargoInfoDomains = new OrderCargoInfoDomain();
	        }
	        if (orderCargoInfoDomains.getPageInfo() == null || "0".equals(flag)) {//0是点击搜索按钮
	        	orderCargoInfoDomains.setPageInfo(new PageInfo());
	        }
	        
	        //两个table也中列表的分页
	        OrderCargoInfoDomain orderCargoInfoDomain=new OrderCargoInfoDomain();
	        orderCargoInfoDomain.setPageInfo(new PageInfo());
	    	
	        orderCargoInfoDomains.setMark(mark);
	        orderCargoInfoDomains.setIsMatchSuccess(Constants.BRIDGING_SUCCESS);
	        orderCargoInfoDomains.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
	        
	        //数据库中查询
	        List<OrderCargoInfoDomain> list=orderCargoInfoService.queryTodayImportInfo(String.valueOf(getSessionUser().getId()), orderCargoInfoDomains);
	        orderCargoInfoDomains.setList(list);
	        modelAndView.addObject("mark",mark);
	        modelAndView.addObject("orderCargoInfoDomains",orderCargoInfoDomains);
	        modelAndView.addObject("orderCargoInfoDomain",orderCargoInfoDomain);
	    	modelAndView.setViewName("index/index");
	    	return modelAndView;
	}
}