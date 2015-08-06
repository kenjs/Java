package com.cy.swp.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cy.swp.bo.DriverUserInfo;
import com.cy.swp.service.DriverUserInfoService;

/**
 * 打开司机反馈框（同时展示出司机的信息）
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("openDriverFeedbackAction")
public class OpenDriverFeedbackAction extends BaseAction{
	 private static final long serialVersionUID = 669803459457232348L;
	 private Logger logger = LoggerFactory.getLogger(getClass());
	 @Resource
	 private DriverUserInfoService driverUserInfoService;
	 
	 @RequestMapping("/openDriverFeedback.jspx")
	protected ModelAndView execMethod(DriverUserInfo driverUserInfo,String assistId) throws Exception {
		 ModelAndView modelAndView=new ModelAndView();
	    	if (getSessionUser() == null) {
	    		modelAndView.setViewName("login");//LOGIN;
	            return modelAndView;
	        }
	        logger.debug("open driver feedback begin. userId=[{}]",
	                getSessionUser().getId());
	        
	        if(StringUtils.isEmpty(driverUserInfo.getId())){
	        	 logger.debug("open driver feedback parameter error!");
	        	return null;
	        }
	        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        //根据Id查询司机信息
	        driverUserInfo= driverUserInfoService.queryDriverUserInfoById(driverUserInfo.getId());
	        modelAndView.addObject("assistId",assistId);
	        modelAndView.addObject(driverUserInfo);
	        modelAndView.addObject("crruteTime",format.format(new Date()));
	        modelAndView.setViewName("driver/driverFeedbackRecord");
		 return modelAndView;
	 }
}
