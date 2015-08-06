package com.cy.swp.action;

import java.util.List;

import javax.annotation.Resource;

import com.cy.swp.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.domain.PageInfo;
import com.cy.swp.service.DriverUserInfoService;
import com.cy.swp.service.OrderCargoInfoService;

/**
 * 货找车
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("queryCargoMateDriverAction")
public class QueryCargoMateDriverAction extends BaseAction {
    private static final long serialVersionUID = 6601989558296876680L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private DriverUserInfoService driverUserInfoService;
    @Resource
    private OrderCargoInfoService orderCargoInfoService;

    @RequestMapping("/queryCargoMateDriver.jspx")
	@ResponseBody
    protected JSonRespone execMethod(DriverUserInfoDomain driverUserInfoDomain,String flag,String mark,String companyPhone,String companyName) throws Exception {
    	if (getSessionUser() == null) {
    		return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
        }
    	logger.debug("query cargo mate driver begin . userId=[{}]",
                getSessionUser().getId());
    	//参数验证
    	 //设置参数
        if (driverUserInfoDomain == null) {
            driverUserInfoDomain = new DriverUserInfoDomain();
        }
        if (driverUserInfoDomain.getPageInfo() == null || "0".equals(flag)) {//0是点击搜索按钮
            driverUserInfoDomain.setPageInfo(new PageInfo());
        }
		OrderCargoInfo orderCargoInfo = orderCargoInfoService.queryOrderCargoInfoById(driverUserInfoDomain.getCargoId());
		//OrderCargoInfo orderCargoInfo = new OrderCargoInfo();
        List<DriverUserInfoDomain> list =driverUserInfoService.queryCargoMateDriverDomainInfo(driverUserInfoDomain,getSessionUser());
        StringBuffer html1 = new StringBuffer();
		StringBuffer html2 = new StringBuffer();
        html1.append("<thead ><tr>");
        html1.append("<td width='25' style='text-align:center;'>序号</td>");
        html1.append("<td width='190' colspan='2' style='text-align:center;'>司机信息</td>");
		html1.append("<td width='160' style='text-align:center;'>车型</td>");

        html1.append("<td width='160' style='text-align:center;'>当前位置</td>");
        html1.append("<td width=180' colspan='2' style='text-align:center;'>配车登记</td>");
        html1.append("</tr></thead>");
        for(int i = 0; i < list.size(); i++) {
        	html1.append("<tr>");
        	html1.append("<td style='text-align:center;'>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].driverUserId\" value=\""+list.get(i).getId()+"\"/>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].assistId\" value=\""+driverUserInfoDomain.getAssistId()+"\"/>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].driverPhone\" value=\""+list.get(i).getCode()+"\"/>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].companyPhone\" value=\""+companyPhone+"\"/>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].carNumber\" value=\""+list.get(i).getCarNumber()+"\"/>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].companyName\" value=\""+companyName+"\"/>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].baiduChannelId\" value=\""+list.get(i).getBaiduChannelId()+"\"/>");
        	html1.append("<input type=\"hidden\" name=\"marketingCargoDriverContactsArry["+i+"].baiduUserId\" value=\""+list.get(i).getBaiduUserId()+"\"/>");
        	html1.append((i+1));
        	html1.append("</td>");
        	
        	html1.append("<td width='90' style='text-align:center;' title=\""+list.get(i).getDriverLine()+"\">");
        	html1.append("<span class='icon'>&nbsp;</span>"+list.get(i).getCarNumber());
        	html1.append("</td>");
        	html1.append("<td width='100' style='text-align:center;'>");
        	html1.append("姓名:"+list.get(i).getName());
        	html1.append("</br>");
        	html1.append(list.get(i).getCode());
        	html1.append("</td>");

			html1.append("<td style='text-align:center;' title=\""+list.get(i).getCarTypes()+"\">");
			if(StringUtils.isNotEmpty(list.get(i).getCarTypes())){
				html1.append(list.get(i).getCarTypes());
			}
			html1.append("</td>");

        	html1.append("<td style='text-align:center;' title=\""+list.get(i).getLastLocation()+"\">");
        	html1.append(list.get(i).getLastLocation());
        	html1.append("</td>");
        	
        	html1.append("<td width='90'>");
        	html1.append("<input type='radio'");
        	html1.append(" name='marketingCargoDriverContactsArry["+i+"].replyResult' value='"+Constants.DRIVER_REPLYRESULT_THREE_UNKNOWN_KEY+"' />"+Constants.DRIVER_REPLYRESULT_THREE_UNKNOWN_VALUE);
        	html1.append("</br>");
        	html1.append("<input type='radio' onclick=\"setDriverContacts('"+list.size()+"','"+i+"');\" ");
        	html1.append(" name='marketingCargoDriverContactsArry["+i+"].replyResult' value='"+Constants.DRIVER_REPLYRESULT_INTENTION_KEY+"' />"+Constants.DRIVER_REPLYRESULT_INTENTION_VALUE);
        	html1.append("</td>");
        	html1.append("<td width='90' style='text-align:center;'>");
        	html1.append("<input type='radio' checked ");
        	html1.append(" name='marketingCargoDriverContactsArry["+i+"].replyResult' value='"+Constants.DRIVER_REPLYRESULT_Four_UNKNOWN_KEY+"' />"+Constants.DRIVER_REPLYRESULT_FOUR_UNKNOWN_VALUE);
        	html1.append("</br>");
        	html1.append("<input type='radio'");
        	html1.append(" name='marketingCargoDriverContactsArry["+i+"].replyResult' value='"+Constants.DRIVER_REPLYRESULT_NOT_INTENTION_KEY+"' />"+Constants.DRIVER_REPLYRESULT_NOT_INTENTION_VALUE);
        	html1.append("</td>");
        	html1.append("</tr>");
        }
		html2.append("<tr>");
		html2.append("<td>"+orderCargoInfo.getCargoName()+"</td>");
		html2.append("<td>"+orderCargoInfo.getContactName()+"</td>");
		html2.append("<td>"+ DateUtil.parseDayDataFrom(orderCargoInfo.getRequestStartTime())+"</td>");
		html2.append("<td><div class=\"ght\">");
		html2.append(orderCargoInfo.getStartProvince());
		html2.append("--");
		html2.append(orderCargoInfo.getStartCity());
		html2.append("<br></div></td>");
		html2.append("<td><div class=\"ght\">");
		html2.append(orderCargoInfo.getEndProvince());
		html2.append("--");
		html2.append(orderCargoInfo.getEndCity());
		html2.append("<br></div></td>");
		html2.append("<td>");
		html2.append(orderCargoInfo.getCargoWeight());
		html2.append("</td>");
		html2.append("<td>"+orderCargoInfo.getCargoCubage()+"</td>");
		html2.append("<td>");
		if(StringUtils.isNotEmpty(orderCargoInfo.getRequestCarLength())) {
			html2.append(orderCargoInfo.getRequestCarLength());
			html2.append("  ");
		}
		if(StringUtils.isNotEmpty(orderCargoInfo.getRequestCarBarType())) {
			html2.append(orderCargoInfo.getRequestCarBarType());
			html2.append("  ");
		}
		if(StringUtils.isNotEmpty(orderCargoInfo.getRequestCarPlateType())) {
			html2.append(orderCargoInfo.getRequestCarPlateType());
			html2.append("  ");
		}
		html2.append("</td>");
		html2.append("</tr>");

		driverUserInfoDomain.setHtml1(html1.toString());
		driverUserInfoDomain.setHtml2(html2.toString());



        return JSonRespone.makeHasContentJSonRespone("0", "success",driverUserInfoDomain);
    }
}
