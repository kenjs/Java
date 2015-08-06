package com.cy.swp.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.swp.bo.MarketingCargoAssist;
import com.cy.swp.bo.MarketingCargoCompanyContacts;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.MarketingCargoAssistDomain;
import com.cy.swp.service.MarketingCargoAssistService;

/**
 * 保存公司和该公司下的货源回复记录
 * 1.货源电话记录是修改
 * 2.公司电话记录：该公司有过记录就修改，没有就新增
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("saveCompAndCargoContactsAction")
public class SaveCompAndCargoContactsAction extends BaseAction{
	
	
	private static final long serialVersionUID = 669803459457232348L;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private MarketingCargoAssistService marketingCargoAssistService;
	
 
	//
	@RequestMapping("/saveCompAndCargoContacts.jspx")
	@ResponseBody
	protected JSonRespone execMethod(MarketingCargoAssist marketingCargoAssist) throws Exception {
		//判断是否登陆
	        if (getSessionUser() == null) {
	            return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
	        }
	        logger.debug("save company and cargo contacts begin. userId=[{}]",
	                getSessionUser().getId());
	        String restl = marketingCargoAssistService.saveCompAndCargoContacts(marketingCargoAssist,String.valueOf(getSessionUser().getId()));
	        if("1".equals(restl)) {//虚假货源、货源已走
	        	return JSonRespone.makeHasContentJSonRespone("0", "success");
	        }else if("2".equals(restl)) {//货源还在
	        	return JSonRespone.makeHasContentJSonRespone("0", "success");
	        }
	        return JSonRespone.makeHasContentJSonRespone("-1", "error","操作失败!");
	        
	 }
	 
	/**
	 * 登记企业联系 
	 * @param marketingCargoCompanyContacts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveCompanyContacts.jspx")
	@ResponseBody
	protected JSonRespone saveCompanyContacts(String contactMobilephone,String replyResult,String remark) throws Exception {
		//判断是否登陆
	        if (getSessionUser() == null) {
	            return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
	        }
	        logger.debug("save company and cargo contacts begin. userId=[{}]",
	                getSessionUser().getId());
	        if(StringUtils.isEmpty(contactMobilephone)){//公司联系人的电话不为空
	        	return JSonRespone.makeHasContentJSonRespone("2", "参数错误");
	        }
	        MarketingCargoCompanyContacts marketingCargoCompanyContacts = new MarketingCargoCompanyContacts();
	        marketingCargoCompanyContacts.setContactMobilephone(contactMobilephone);
	        marketingCargoCompanyContacts.setReplyResult(replyResult);
			marketingCargoCompanyContacts.setRemark(remark);
	        marketingCargoCompanyContacts.setAssisterId(String.valueOf(getSessionUser().getId()));
		    marketingCargoCompanyContacts.setAssisterName(getSessionUser().getName());
	        marketingCargoAssistService.saveCompContacts(marketingCargoCompanyContacts,String.valueOf(getSessionUser().getId()));
	        return JSonRespone.makeHasContentJSonRespone("0", "success","企业登记成功！");
	 }
	 
	 

}
