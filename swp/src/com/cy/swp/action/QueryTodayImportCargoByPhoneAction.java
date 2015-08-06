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

import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.service.OrderCargoInfoService;

/**
 * 根据导入的联系人号码查询该人的货源信息
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("queryTodayImportCargoByPhoneAction")
public class QueryTodayImportCargoByPhoneAction extends BaseAction{
	 private static final long serialVersionUID = 669803459457232348L;
	 private Logger logger = LoggerFactory.getLogger(getClass());
	
	 @Resource
	 private OrderCargoInfoService orderCargoInfoService;
	 
	 @RequestMapping("/queryTodayImportCargoByPhone.jspx")
	 @ResponseBody
	protected JSonRespone execMethod(OrderCargoInfoDomain orderCargoInfoDomain) throws Exception {
		 //判断是否登陆
	        if (getSessionUser() == null) {
	            return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
	        }
	        logger.debug("query today import cargo by phone begin. userId=[{}]",
	                getSessionUser().getId());
	        
	        //参数验证
	        if(orderCargoInfoDomain==null||
	        		StringUtils.isEmpty(orderCargoInfoDomain.getContactMobilephone())||
	        		StringUtils.isEmpty(orderCargoInfoDomain.getMark())){
	        	//return JSonRespone.makeHasContentJSonRespone("2", "参数错误");
	        }
	        List<OrderCargoInfoDomain> cargoList=orderCargoInfoService.queryTodayImportCargoInfoByPhone(String.valueOf(getSessionUser().getId()), orderCargoInfoDomain);
	        StringBuffer html = new StringBuffer();
	        html.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
			for(int i=0;i<cargoList.size();i++){
				String color = "";
				if("有".equals(cargoList.get(i).getMatchDriverCount())) {
					color = "style='color:red;'";
				}
				String varmatc = "已联系";
				if("0".equals(cargoList.get(i).getIsMatchSuccess()) && (cargoList.get(i).getCargoResult() == null || "".equals(cargoList.get(i).getCargoResult()) || "1".equals(cargoList.get(i).getCargoResult()))) {
					varmatc = "待配车"; 
				}
				html.append("<tr "+color+">");
				html.append("<td width=\"30\">"+(i+1)+"</td>");
				html.append("<td width=\"80\">"+cargoList.get(i).getCargoName()+"</td>");
				html.append("<td width=\"120\">联系人："+cargoList.get(i).getContactName()+"</td>");
				html.append("<td width=\"140\">装货时间："+cargoList.get(i).getRequestStartTime()+"</td>");
				html.append("<td width=\"220\">"+cargoList.get(i).getStartProCityCounty()+"→"+cargoList.get(i).getEndProCityCounty()+"</td>");
				html.append("<td width=\"60\">"+cargoList.get(i).getMatchDriverCount()+"</td>");
				html.append("<td width=\"80\">"+varmatc+"</td>");
				html.append("</tr>");
				
			}
			html.append("</table>");
	        
	        
	        
		 return JSonRespone.makeHasContentJSonRespone("0", "success",html);
	}
}
