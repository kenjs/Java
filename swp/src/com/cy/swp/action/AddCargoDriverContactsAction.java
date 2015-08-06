package com.cy.swp.action;

import javax.annotation.Resource;

import com.cy.swp.service.MarketingNoteRecordService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.swp.bo.MarketingCargoDriverContacts;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.push.BaiduPush;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.service.CompanyAndDriverContactsService;
import com.cy.swp.service.ImportOrderCargoService;

/**
 * 添加货源司机电话回访记录
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("addCargoDriverContactsAction")
public class AddCargoDriverContactsAction extends BaseAction{
	 private static final long serialVersionUID = 669803459457232348L;
	 private Logger logger = LoggerFactory.getLogger(getClass());

	 @Resource
	 private CompanyAndDriverContactsService companyAndDriverContactsService;

	@Resource
	private MarketingNoteRecordService marketingNoteRecordService;
	 
	 @RequestMapping("/addCargoDriverContacts.jspx")
	 @ResponseBody
	protected JSonRespone execMethod(MarketingCargoDriverContacts marketingCargoDriverContacts) throws Exception {
		//判断是否登陆
	        if (getSessionUser() == null) {
	            return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
	        }
	        logger.debug("add cargo driver contacts begin. userId=[{}]",
	                getSessionUser().getId());
	        
	        //企业短信
		 	StringBuilder netoCompanyStr = new StringBuilder();
		 	StringBuilder driverPh = new StringBuilder();
	        String companyPh = "";
	        netoCompanyStr.append("已帮您找到车辆：");
	        int j = 0;
	        for(int i = 0; i < marketingCargoDriverContacts.getMarketingCargoDriverContactsArry().length; i++) {
	        	if(!Constants.DRIVER_REPLYRESULT_Four_UNKNOWN_KEY.equals(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getReplyResult())) {
	        		if(Constants.DRIVER_REPLYRESULT_INTENTION_KEY.equals(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getReplyResult())) {
	        			j++;
	        			if(j<3) {
	        				netoCompanyStr.append(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getCarNumber());
	        				netoCompanyStr.append("/");
	        				netoCompanyStr.append(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getDriverPhone());
	        				netoCompanyStr.append("，");
	        				companyPh = marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getCompanyPhone();
	        				driverPh.append(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getDriverPhone());
	        				driverPh.append(",");
	        			}
	        			//司机推送
						StringBuilder netoDriverStr = new StringBuilder();
	        	        netoDriverStr.append("请点击在搜索找货中输入公司名称：");
	        	        netoDriverStr.append(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getCompanyName());
	        	        netoDriverStr.append("查找货源");
	        	        //开始推送
	        	        if(StringUtils.isNotEmpty(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getBaiduChannelId())) {
							if(StringUtils.isNotEmpty(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getBaiduChannelId()) && StringUtils.isNotEmpty(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getBaiduUserId())){
								try{
									BaiduPush.pushUnicastNotification(Long.valueOf(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getBaiduChannelId()),
											marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getBaiduUserId(), "【快到网】已帮您匹配到货源！", netoDriverStr.toString());
								}catch (Exception e) {
									e.printStackTrace();
								}
							}
	        	        }
	        		}
	        		MarketingCargoDriverContacts driverContacts = new MarketingCargoDriverContacts();
		        	driverContacts.setAssistId(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getAssistId());
		        	driverContacts.setDriverUserId(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getDriverUserId());
		        	driverContacts.setReplyResult(marketingCargoDriverContacts.getMarketingCargoDriverContactsArry()[i].getReplyResult());
					driverContacts.setAssisterName(getSessionUser().getName());
					companyAndDriverContactsService.addCargoDriverContacts(driverContacts);
	        	}
	        }
	        netoCompanyStr.append("请登录www.56top.cn定车【快到网】");
	        //开始发送短信
	        if(j>0) {
				/**
				 *发送短信，向企业
				 * @param type 发送对象类别 0企业 1司机
				 * @param remark 备注
				 * @param telephone 发送手机
				 * @param content 发送内容
				 * @param useFor
				 * @return 用途：1 导入货源配车有司机发送给企业的短信 2 导入货源配车无司机发送给企业的短信
				 */
				marketingNoteRecordService.setNoteSendRecordInfo("0",driverPh.toString(),companyPh,netoCompanyStr.toString(),"2");
	        }
	        return JSonRespone.makeHasContentJSonRespone("0", "success");
	        
	 }
}
