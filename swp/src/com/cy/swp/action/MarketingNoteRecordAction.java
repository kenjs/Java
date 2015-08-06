package com.cy.swp.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.service.MarketingNoteRecordService;
import com.cy.swp.service.SystemNoteTemplateService;

@Scope("prototype")
@Controller("marketingNoteRecordAction")
public class MarketingNoteRecordAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private SystemNoteTemplateService systemNoteTemplateService;
	
	@Resource
	private MarketingNoteRecordService marketingNoteRecordService;
	
	/**
	 * 打开营销发送短信
	 * @param view
	 * @return
	 */
	@RequestMapping("/openAddNotePage.jspx")
    protected ModelAndView openAddNotePage(ModelAndView view) {
		if (this.getSessionUser() == null) {
			view.setViewName("redirect:/");
            return view;
        }
		view.setViewName("note/addNote");
		return view;
	}
	
	
	@RequestMapping("/querySystemNoteTemplateHtml.jspx")
	@ResponseBody
    protected JSonRespone querySystemNoteTemplateHtml(Integer businessType,Integer sendType) {
		if (this.getSessionUser() == null) {
			return JSonRespone.makeHasContentJSonRespone("-1", "请登录！");
        }
		String jsonNoteHtml = systemNoteTemplateService.querySystemNoteTemplateHtnl(businessType, sendType);
		return JSonRespone.makeHasContentJSonRespone("0", "查询成功", jsonNoteHtml);
	} 
	
	
	@RequestMapping("/addMarketingNoteRecord.jspx")
	@ResponseBody
    protected JSonRespone addMarketingNoteRecord(String mobilephone,String content) {
		if (this.getSessionUser() == null) {
			return JSonRespone.makeHasContentJSonRespone("-1", "请登录！");
        }
		if(StringUtils.isEmpty(mobilephone)) {
			return JSonRespone.makeHasContentJSonRespone("1", "手机号码不能为空！");
		}
		if(StringUtils.isEmpty(content)) {
			return JSonRespone.makeHasContentJSonRespone("2", "短信内容不能为空！");
		}
		marketingNoteRecordService.addMarketingNoteRecordInfo(mobilephone, content, getSessionUser().getId());
		return JSonRespone.makeHasContentJSonRespone("0", "短信发送成功");
	} 

}
