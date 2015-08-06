package com.cy.swp.action;

import java.util.ArrayList;
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

import com.cy.swp.bo.MarketingCargoCompanyContacts;
import com.cy.swp.common.constants.Constants;
import com.cy.swp.common.util.DateUtil;
import com.cy.swp.common.util.JSonRespone;
import com.cy.swp.domain.DriverUserInfoDomain;
import com.cy.swp.domain.OrderCargoInfoDomain;
import com.cy.swp.service.CompanyAndDriverContactsService;
import com.cy.swp.service.DriverUserInfoService;
import com.cy.swp.service.OrderCargoInfoService;

/**
 * 打开记录货源反馈结果页面
 * @author zdy
 *
 */

@Scope("prototype")
@Controller("openCompanyFeedbackRecordAction")
public class OpenCompanyFeedbackRecordAction extends BaseAction{
	 private static final long serialVersionUID = 669803459457232348L;
	 private Logger logger = LoggerFactory.getLogger(getClass());
	 
	 @Resource
	 private OrderCargoInfoService orderCargoInfoService;
	 
	 
	 @Resource
	 private CompanyAndDriverContactsService companyAndDriverContactsService;
	 
	 
	 @Resource
	 private DriverUserInfoService DriverUserInfoService;
	 
	 
	 @RequestMapping("/openCompanyFeedbackRecord.jspx")
	 @ResponseBody
	 protected JSonRespone execMethod(OrderCargoInfoDomain orderCargoInfoDomain) throws Exception {
		 if (getSessionUser() == null) {
	    		return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
	        }
	        logger.debug("open company feedback begin. userId=[{}]",
	                getSessionUser().getId());
	        //参数验证
	        if(orderCargoInfoDomain==null||
	        		StringUtils.isEmpty(orderCargoInfoDomain.getContactMobilephone())){
	        	logger.debug("open company feedback parameter error!");
	        }
	        StringBuffer html = new StringBuffer();
	        List<OrderCargoInfoDomain> list=orderCargoInfoService.queryTodayImportCargoInfoByPhone(String.valueOf(getSessionUser().getId()), orderCargoInfoDomain);
	        html.append("<thead >");
	        html.append("<tr>");
	        html.append("<input type='hidden' name='contactMobilephone' id='contactMobilephone' value='"+orderCargoInfoDomain.getContactMobilephone()+"'/>");
		    html.append("<td width='80px' style='text-align:center;'></td>");
		 	html.append("<td width='170px'>货物信息</td>");
		    html.append("<td width='50px' style='text-align:center;'>重量</td>");
		 	html.append("<td width='50px' style='text-align:center;'>体积</td>");
		 	html.append("<td width='100px' style='text-align:center;'>车型要求</td>");
	        html.append("<td width='180x' style='text-align:center;'>货源登记</td>");
	        html.append("<td width='120x' colspan='2' style='text-align:center;'>匹配车辆</td>");
	        html.append("</tr>");
	        html.append("</thead>");
	        for(int i = 0 ;i<list.size();i++) {
	        	if(Constants.CARGO_REPLYRESULT_EXIST_KEY.equals(list.get(i).getCargoResult()) && (null == list.get(i).getContactDriverCount() || "0".equals(list.get(i).getContactDriverCount()))) {
	        		html.append("<tr>");
	        		
		        	html.append("<td title='"+list.get(i).getCargoName()+"' style='text-align:center; width:80px;' >");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].id' id='assistId' value='"+list.get(i).getAssistId()+"'/>");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].cargoId' id='cargoId' value='"+list.get(i).getId()+"'/>");
		        	html.append(list.get(i).getCargoName());
		        	html.append("</td>");      
			        
		        	html.append("<td style='text-align:center; width:170px;'>");
		        	html.append("发货时间:"+list.get(i).getRequestStartTime());
		        	html.append("<br/>");
		        	if(null != list.get(i).getStartProvince()) {
		        		html.append(list.get(i).getStartProvince());
		        	}
		        	if(null != list.get(i).getStartCity()) {
		        		html.append(list.get(i).getStartCity());
		        	}
		        	if(null != list.get(i).getStartCounty()) {
		        		html.append(list.get(i).getStartCounty());
		        	}
		        	html.append("→");
		        	if(null != list.get(i).getEndProvince()) {
		        		html.append(list.get(i).getEndProvince());
		        	}
		        	if(null != list.get(i).getEndCity()){
		        		html.append(list.get(i).getEndCity());
		        	}
		        	if(null != list.get(i).getEndCounty()) {
		        		html.append(list.get(i).getEndCounty());
		        	}
		        	html.append("</td>");

					html.append("<td style='text-align:center;'>");
					html.append(list.get(i).getCargoWeight());
					html.append("</td>");
					html.append("<td style='text-align:center;'>");
					html.append(list.get(i).getCargoCubage());
					html.append("</td>");
					html.append("<td style='text-align:center;'>");
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarLength())) {
						html.append(list.get(i).getRequestCarLength());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarBarType())) {
						html.append(list.get(i).getRequestCarBarType());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarPlateType())) {
						html.append(list.get(i).getRequestCarPlateType());
						html.append(" ");
					}
					html.append("</td>");
		        	
		        	html.append("<td width='180px' style='text-align:center;'>");
		        	//-1虚假货源
		        	html.append("<input type='radio' onclick=\"setCargoHxYz('"+list.get(i).getAssistId()+"','"+Constants.CARGO_REPLYRESULT_NONENTITY_KEY+"','"+list.get(i).getId()+"')\" ");
		        	html.append(" name='cargoAssistArray["+i+"].cargoResult' value='"+Constants.CARGO_REPLYRESULT_NONENTITY_KEY+"' id='RadioGroup1_0' />"+Constants.CARGO_REPLYRESULT_NONENTITY_VALUE);
		        	//2货已走
		        	html.append("<input type='radio' onclick=\"setCargoHxYz('"+list.get(i).getAssistId()+"','"+Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY+"','"+list.get(i).getId()+"')\" ");
		        	html.append(" name='cargoAssistArray["+i+"].cargoResult' value='"+Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY+"' id='RadioGroup1_0' />"+Constants.CARGO_REPLYRESULT_HAD_COVERED_VALUE);
		        	html.append("</br>");
		        	//1货还在
		        	html.append("<input type='radio' onclick=\"setCargoHxYz('"+list.get(i).getAssistId()+"','"+Constants.CARGO_REPLYRESULT_EXIST_KEY+"','"+list.get(i).getId()+"')\" ");
		        	html.append("checked");
		        	html.append(" name='cargoAssistArray["+i+"].cargoResult' value='"+Constants.CARGO_REPLYRESULT_EXIST_KEY+"' id='RadioGroup1_0' />"+Constants.CARGO_REPLYRESULT_EXIST_VALUE);
		        	
		        	html.append("</td>");
		        	
		        	html.append("<td width='50px' style='text-align:center;'>"+list.get(i).getMatchDriverCount()+"</td>");
		        	html.append("<td width='70px' style='text-align:center;'><a href=\"javascript:getCarCompand('"+list.get(i).getId()+"','"+list.get(i).getAssistId()+"','"+orderCargoInfoDomain.getMark()+"','1','"+list.get(i).getCompanyName()+"');\">配车</a></td>");
		            html.append("</tr>");
	        	}else if("".equals(list.get(i).getCargoResult()) || null == list.get(i).getCargoResult()) {
	        		html.append("<tr>");
		        	html.append("<td title='"+list.get(i).getCargoName()+"' style='text-align:center; width:80px;' >");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].id' id='assistId' value='"+list.get(i).getAssistId()+"'/>");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].cargoId' id='cargoId' value='"+list.get(i).getId()+"'/>");
		        	html.append(list.get(i).getCargoName());
		        	html.append("</td>");      
			        
		        	html.append("<td style='text-align:center; width:170px;'>");
		        	html.append("发货时间:"+list.get(i).getRequestStartTime());
		        	html.append("<br/>");
		        	if(null != list.get(i).getStartProvince()) {
		        		html.append(list.get(i).getStartProvince());
		        	}
		        	if(null != list.get(i).getStartCity()) {
		        		html.append(list.get(i).getStartCity());
		        	}
		        	if(null != list.get(i).getStartCounty()) {
		        		html.append(list.get(i).getStartCounty());
		        	}
		        	html.append("→");
		        	if(null != list.get(i).getEndProvince()) {
		        		html.append(list.get(i).getEndProvince());
		        	}
		        	if(null != list.get(i).getEndCity()){
		        		html.append(list.get(i).getEndCity());
		        	}
		        	if(null != list.get(i).getEndCounty()) {
		        		html.append(list.get(i).getEndCounty());
		        	}
		        	html.append("</td>");

					html.append("<td style='text-align:center;'>");
					html.append(list.get(i).getCargoWeight());
					html.append("</td>");
					html.append("<td style='text-align:center;'>");
					html.append(list.get(i).getCargoCubage());
					html.append("</td>");
					html.append("<td style='text-align:center;'>");
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarLength())) {
						html.append(list.get(i).getRequestCarLength());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarBarType())) {
						html.append(list.get(i).getRequestCarBarType());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarPlateType())) {
						html.append(list.get(i).getRequestCarPlateType());
						html.append(" ");
					}
					html.append("</td>");
		        	
		        	html.append("<td width='180px' style='text-align:center;'>");
		        	html.append("<input type='radio' onclick=\"setCargoHxYz('"+list.get(i).getAssistId()+"','"+Constants.CARGO_REPLYRESULT_NONENTITY_KEY+"','"+list.get(i).getId()+"')\" ");
		        	html.append(" name='cargoAssistArray["+i+"].cargoResult' value='"+Constants.CARGO_REPLYRESULT_NONENTITY_KEY+"' id='RadioGroup1_0' />"+Constants.CARGO_REPLYRESULT_NONENTITY_VALUE);
		        	html.append("<input type='radio' onclick=\"setCargoHxYz('"+list.get(i).getAssistId()+"','"+Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY+"','"+list.get(i).getId()+"')\" ");
		        	html.append(" name='cargoAssistArray["+i+"].cargoResult' value='"+Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY+"' id='RadioGroup1_0' />"+Constants.CARGO_REPLYRESULT_HAD_COVERED_VALUE);
		        	html.append("<br />");
		        	html.append("<input type='radio' onclick=\"setCargoHxYz('"+list.get(i).getAssistId()+"','"+Constants.CARGO_REPLYRESULT_EXIST_KEY+"','"+list.get(i).getId()+"')\" ");
		        	html.append(" name='cargoAssistArray["+i+"].cargoResult' value='"+Constants.CARGO_REPLYRESULT_EXIST_KEY+"' id='RadioGroup1_0' />"+Constants.CARGO_REPLYRESULT_EXIST_VALUE);
		        	html.append("</td>");
		        	
		        	html.append("<td width='50px' style='text-align:center;'>"+list.get(i).getMatchDriverCount()+"</td>");
		        	html.append("<td width='70px' style='text-align:center;'><a href=\"javascript:getCarCompand('"+list.get(i).getId()+"','"+list.get(i).getAssistId()+"','"+orderCargoInfoDomain.getMark()+"','2','"+list.get(i).getCompanyName()+"');\">配车</a></td>");
		            html.append("</tr>");
	        	}
	        }
	        orderCargoInfoDomain.setHtml1(html.toString());
	        logger.debug("open company feedback parameter success!!!");
	        return JSonRespone.makeHasContentJSonRespone("0", "success",orderCargoInfoDomain);
	 }
	 
	 
	 
	 @RequestMapping("/queyrCompanyCargoyList.jspx")
	 @ResponseBody
	 protected JSonRespone queyrCompanyCargoyList(OrderCargoInfoDomain orderCargoInfoDomain) throws Exception {
		 if (getSessionUser() == null) {
	    		return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
	        }
	        logger.debug("open company feedback begin. userId=[{}]",
	                getSessionUser().getId());
	        //参数验证
	        if(orderCargoInfoDomain==null||
	        		StringUtils.isEmpty(orderCargoInfoDomain.getContactMobilephone())){
	        	logger.debug("open company feedback parameter error!");
	        }
	        StringBuffer html = new StringBuffer();
	        List<OrderCargoInfoDomain> list = orderCargoInfoService.queryTodayImportCargoInfoByPhone(String.valueOf(getSessionUser().getId()), orderCargoInfoDomain);
	        html.append("<thead >");
	        html.append("<tr>");
	        html.append("<input type='hidden' name='contactMobilephone' id='contactMobilephone' value='"+orderCargoInfoDomain.getContactMobilephone()+"'/>");
		 	html.append("<td width='80px' style='text-align:center;'></td>");
		 	html.append("<td width='170px'>货物信息</td>");
		    html.append("<td width='50px' style='text-align:center;'>重量</td>");
		    html.append("<td width='50px' style='text-align:center;'>体积</td>");
		    html.append("<td width='100px' style='text-align:center;'>车型要求</td>");
	        html.append("<td width='100x' style='text-align:center;'>货源登记</td>");
	        html.append("<td width='190x' style='text-align:center;'>匹配车辆</td>");
	        html.append("</tr>");
	        html.append("</thead>");
	        for(int i = 0 ;i<list.size();i++) {
	        	List<DriverUserInfoDomain> driverCarList = new ArrayList<DriverUserInfoDomain>();
	        	if(Constants.CARGO_REPLYRESULT_EXIST_KEY.equals(list.get(i).getCargoResult()) && !"0".equals(list.get(i).getContactDriverCount()) && null !=list.get(i).getContactDriverCount()) {
	        		//查询出匹配的车辆
	        		driverCarList = DriverUserInfoService.queryCarByAssisterId(list.get(i).getAssistId());
	        		html.append("<tr>");
	        		
		        	html.append("<td rowSpan='"+driverCarList.size()+"' title='"+list.get(i).getCargoName()+"' style='text-align:center; width:80px;' >");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].id' id='assistId' value='"+list.get(i).getAssistId()+"'/>");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].cargoId' id='cargoId' value='"+list.get(i).getId()+"'/>");
		        	html.append(list.get(i).getCargoName());
		        	html.append("</td>");      
			        
		        	html.append("<td rowSpan='"+driverCarList.size()+"' style='text-align:center; width:170px;'>");
		        	html.append("发货时间:"+list.get(i).getRequestStartTime());
		        	html.append("<br/>");
		        	if(null != list.get(i).getStartProvince()) {
		        		html.append(list.get(i).getStartProvince());
		        	}
		        	if(null != list.get(i).getStartCity()) {
		        		html.append(list.get(i).getStartCity());
		        	}
		        	if(null != list.get(i).getStartCounty()) {
		        		html.append(list.get(i).getStartCounty());
		        	}
		        	html.append("→");
		        	if(null != list.get(i).getEndProvince()) {
		        		html.append(list.get(i).getEndProvince());
		        	}
		        	if(null != list.get(i).getEndCity()){
		        		html.append(list.get(i).getEndCity());
		        	}
		        	if(null != list.get(i).getEndCounty()) {
		        		html.append(list.get(i).getEndCounty());
		        	}
		        	html.append("</td>");
					html.append("<td rowSpan='"+driverCarList.size()+"' style='text-align:center;'>");
					html.append(list.get(i).getCargoWeight());
					html.append("</td>");
					html.append("<td rowSpan='"+driverCarList.size()+"' style='text-align:center;'>");
					html.append(list.get(i).getCargoCubage());
					html.append("</td>");
					html.append("<td rowSpan='"+driverCarList.size()+"' style='text-align:center;'>");
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarLength())) {
						html.append(list.get(i).getRequestCarLength());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarBarType())) {
						html.append(list.get(i).getRequestCarBarType());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarPlateType())) {
						html.append(list.get(i).getRequestCarPlateType());
						html.append(" ");
					}
					html.append("</td>");
		        	
		        	html.append("<td rowSpan='"+driverCarList.size()+"' width='100px' style='text-align:center;'>");
		        	//-1虚假货源
		        	if(Constants.CARGO_REPLYRESULT_NONENTITY_KEY.equals(list.get(i).getCargoResult())) {
		        		html.append(Constants.CARGO_REPLYRESULT_NONENTITY_VALUE);
		        	}
		        	//2货已走
		        	if(Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY.equals(list.get(i).getCargoResult())) {
		        		html.append(Constants.CARGO_REPLYRESULT_HAD_COVERED_VALUE);
		        	}
		        	//1货还在
		        	if(Constants.CARGO_REPLYRESULT_EXIST_KEY.equals(list.get(i).getCargoResult())) {
		        		html.append(Constants.CARGO_REPLYRESULT_EXIST_VALUE);
		        	}
		        	
		        	//3无效号码
		        	if(Constants.CARGO_REPLYRESULT_HAD_THERR_COVERED_KEY.equals(list.get(i).getCargoResult())) {
		        		html.append(Constants.CARGO_REPLYRESULT_HAD_THERR_COVERED_VALUE);
		        	}
		        	
		        	html.append("</td>");
		        	if(driverCarList.size() == 0) {
		        		html.append("<td width='190px' style='text-align:center;'></td>");
		        	}else {
		        		html.append("<td width='190px' style='text-align:center; position:relative;'><span class='icon'>&nbsp;</span>");
		        		html.append("<a href=\"#\" onmouseover=\"setOnmouseover('divA"+i+"0')\" onmouseout=\"setOnmouseout('divA"+i+"0')\">");
		        		html.append(driverCarList.get(0).getCarNumber());
		        		html.append("</a>");
		        		html.append("<div id=\"divA"+i+"0\" class=\"solre\" style=\"display:none;\">");
		        		html.append("<ul>");
		        		html.append("<li>司机姓名："+driverCarList.get(0).getName()+" &nbsp;&nbsp;&nbsp;司机号码："+driverCarList.get(0).getCode()+"</li>");
		        		html.append("<li>运营线路："+driverCarList.get(0).getDriverLine()+"</li>");
		        		html.append("<li>当前位置："+driverCarList.get(0).getLastLocation()+"</li>");
		        		html.append("<li>定位时间："+driverCarList.get(0).getLastTime()+"</li>");
		        		html.append("</ul>");
		        		html.append("</div>");
		        		html.append("</td>");
		        	}
		            html.append("</tr>");
		            for(int j = 1;j<driverCarList.size();j++) {
		            	html.append("<tr>");
		            	html.append("<td width='190px' style='text-align:center; position:relative;'><span class='icon'>&nbsp;</span>");
		        		html.append("<a href=\"#\" onmouseover=\"setOnmouseover('divA"+i+j+"')\" onmouseout=\"setOnmouseout('divA"+i+j+"')\">");
		        		html.append(driverCarList.get(j).getCarNumber());
		        		html.append("</a>");
		        		html.append("<div id=\"divA"+i+j+"\" class=\"solre\" style=\"display:none;\">");
		        		html.append("<ul>");
		        		html.append("<li>司机姓名："+driverCarList.get(j).getName()+" &nbsp;&nbsp;&nbsp;司机号码："+driverCarList.get(j).getCode()+"</li>");
		        		html.append("<li>运营线路："+driverCarList.get(j).getDriverLine()+"</li>");
		        		html.append("<li>当前位置："+driverCarList.get(j).getLastLocation()+"</li>");
		        		html.append("<li>定位时间："+driverCarList.get(j).getLastTime()+"</li>");
		        		html.append("</ul>");
		        		html.append("</div>");
		        		html.append("</td>");
	        			html.append("</tr>");
		            }
	        	}else if(!Constants.CARGO_REPLYRESULT_EXIST_KEY.equals(list.get(i).getCargoResult()) && null != list.get(i).getCargoResult() && !"".equals(list.get(i).getCargoResult())) {
	        		html.append("<tr>");
		        	html.append("<td title='"+list.get(i).getCargoName()+"' style='text-align:center; width:80px;' >");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].id' id='assistId' value='"+list.get(i).getAssistId()+"'/>");
		        	html.append("<input type='hidden' name='cargoAssistArray["+i+"].cargoId' id='cargoId' value='"+list.get(i).getId()+"'/>");
		        	html.append(list.get(i).getCargoName());
		        	html.append("</td>");      
			        
		        	html.append("<td style='text-align:center; width:170px;'>");
		        	html.append("发货时间:"+list.get(i).getRequestStartTime());
		        	html.append("<br/>");
		        	if(null != list.get(i).getStartProvince()) {
		        		html.append(list.get(i).getStartProvince());
		        	}
		        	if(null != list.get(i).getStartCity()) {
		        		html.append(list.get(i).getStartCity());
		        	}
		        	if(null != list.get(i).getStartCounty()) {
		        		html.append(list.get(i).getStartCounty());
		        	}
		        	html.append("→");
		        	if(null != list.get(i).getEndProvince()) {
		        		html.append(list.get(i).getEndProvince());
		        	}
		        	if(null != list.get(i).getEndCity()){
		        		html.append(list.get(i).getEndCity());
		        	}
		        	if(null != list.get(i).getEndCounty()) {
		        		html.append(list.get(i).getEndCounty());
		        	}
		        	html.append("</td>");

					html.append("<td style='text-align:center;'>");
					html.append(list.get(i).getCargoWeight());
					html.append("</td>");
					html.append("<td style='text-align:center;'>");
					html.append(list.get(i).getCargoCubage());
					html.append("</td>");
					html.append("<td style='text-align:center;'>");
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarLength())) {
						html.append(list.get(i).getRequestCarLength());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarBarType())) {
						html.append(list.get(i).getRequestCarBarType());
						html.append(" ");
					}
					if(StringUtils.isNotEmpty(list.get(i).getRequestCarPlateType())) {
						html.append(list.get(i).getRequestCarPlateType());
						html.append(" ");
					}
					html.append("</td>");

		        	html.append("<td width='100px' style='text-align:center;'>");
		        	//-1虚假货源
		        	if(Constants.CARGO_REPLYRESULT_NONENTITY_KEY.equals(list.get(i).getCargoResult())) {
		        		html.append(Constants.CARGO_REPLYRESULT_NONENTITY_VALUE);
		        	}
		        	
		        	//2货已走
		        	if(Constants.CARGO_REPLYRESULT_HAD_COVERED_KEY.equals(list.get(i).getCargoResult())) {
		        		html.append(Constants.CARGO_REPLYRESULT_HAD_COVERED_VALUE);
		        	}
		        	
		        	//3无效号码
		        	if(Constants.CARGO_REPLYRESULT_HAD_THERR_COVERED_KEY.equals(list.get(i).getCargoResult())) {
		        		html.append(Constants.CARGO_REPLYRESULT_HAD_THERR_COVERED_VALUE);
		        	}
		        	html.append("</td>");
		        	html.append("<td width='190px' style='text-align:center;'></td>");
		            html.append("</tr>");
	        	}
	        }
	        orderCargoInfoDomain.setHtml1(html.toString());
	        logger.debug("open company feedback parameter success!!!");
	        return JSonRespone.makeHasContentJSonRespone("0", "success",orderCargoInfoDomain);
	 }
	 
	 
	 
	@RequestMapping("/openCompanyFeedbackRecordPagexinx.jspx")
	@ResponseBody
	protected JSonRespone openCompanyFeedbackRecordPagexinx(OrderCargoInfoDomain orderCargoInfoDomain) throws Exception {
		 //判断是否登陆
	        if (getSessionUser() == null) {
	            return JSonRespone.makeHasContentJSonRespone("1", "请先登录");
	        }
	        logger.debug("query today import cargo by phone begin. userId=[{}]",
	                getSessionUser().getId());
	        
	        StringBuffer html1 = new StringBuffer();
	        StringBuffer html2 = new StringBuffer();
	        StringBuffer html3 = new StringBuffer();
	        /**
	         * 获取企业信息
	         */
	        List<OrderCargoInfoDomain> list1 = orderCargoInfoService.queryTodayImportInfo(String.valueOf(getSessionUser().getId()), orderCargoInfoDomain);
	        if(list1.size()==1) {
	        	/**
		         * 获取企业联系历史记录
		         */
	        	List<MarketingCargoCompanyContacts> contactsList = companyAndDriverContactsService.queryCompanyContactByTelephoneList(list1.get(0).getContactMobilephone());
	        	
	        	html1.append("<li>发货单位："+list1.get(0).getCompanyName()+"</li>");
	        	html1.append("<li>联系方式："+list1.get(0).getContactName()+"   "+list1.get(0).getContactMobilephone()+"</li>");
	        	String FirstDate = "";
	        	if(StringUtils.isNotEmpty(list1.get(0).getFirstDate())) {
	        		FirstDate = list1.get(0).getFirstDate();
	        	}
	        	if(StringUtils.isEmpty(list1.get(0).getRegCompanyId())) {
	        		html1.append("<li>企业状态：未注册&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首次联系："+FirstDate+"</li>");
	        	}else {
	        		html1.append("<li>企业状态：已注册&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首次联系："+FirstDate+"</li>");
	        	}
    			for(int i = 0;i<contactsList.size();i++) {
					String names = "";
					if(StringUtils.isNotEmpty(contactsList.get(i).getAssisterName())) {
						names = contactsList.get(i).getAssisterName();
					}
    				if(Constants.COMP_REPLYRESULT_INVALID_KEY.equals(contactsList.get(i).getReplyResult())) {
    					html2.append("<li>"+names+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+DateUtil.parseLongDataFromStr(contactsList.get(i).getCreateTime())+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+Constants.COMP_REPLYRESULT_INVALID_VALUE+"</li>");
    				}else if(Constants.COMP_REPLYRESULT_REGISTER_KEY.equals(contactsList.get(i).getReplyResult())) {
    					html2.append("<li>"+names+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+DateUtil.parseLongDataFromStr(contactsList.get(i).getCreateTime())+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+Constants.COMP_REPLYRESULT_REGISTER_VALUE+"</li>");
    				}else if(Constants.COMP_REPLYRESULT_INTENTION_KEY.equals(contactsList.get(i).getReplyResult())) {
    					html2.append("<li>"+names+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+DateUtil.parseLongDataFromStr(contactsList.get(i).getCreateTime())+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+Constants.COMP_REPLYRESULT_INTENTION_VALUE+"</li>");
    				}else if(Constants.COMP_REPLYRESULT_NOT_INTENTION_KEY.equals(contactsList.get(i).getReplyResult())) {
    					html2.append("<li>"+names+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+DateUtil.parseLongDataFromStr(contactsList.get(i).getCreateTime())+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+Constants.COMP_REPLYRESULT_NOT_INTENTION_VALUE+"</li>");
    				}else if(Constants.COMP_REPLYRESULT_NOT_SPECIFY_KEY.equals(contactsList.get(i).getReplyResult())) {
    					html2.append("<li>"+names+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+DateUtil.parseLongDataFromStr(contactsList.get(i).getCreateTime())+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+Constants.COMP_REPLYRESULT_NOT_SPECIFY_VALUE+"</li>");
    				}
    			}
	        }
	        MarketingCargoCompanyContacts marketingCargoCompanyContacts=companyAndDriverContactsService.queryCompanyContactByTelephone(orderCargoInfoDomain.getContactMobilephone());
	        html3.append("<h3 class='deh3'>联系登记</h3>");
	        html3.append("<dt >");
	        if(marketingCargoCompanyContacts != null) {
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	if("0".equals(marketingCargoCompanyContacts.getReplyResult())) {
	        		html3.append(" checked ");
	        	} 
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_INVALID_KEY+"' id='RadioGroup1_0' />");
	        	html3.append(Constants.COMP_REPLYRESULT_INVALID_VALUE);
	        	html3.append("</label>");
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	if("2".equals(marketingCargoCompanyContacts.getReplyResult())) {
	        		html3.append("checked");
	        	} 
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_INTENTION_KEY+"' id='RadioGroup1_2' />");
	        	html3.append(Constants.COMP_REPLYRESULT_INTENTION_VALUE);
	        	html3.append("</label>");
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	if("3".equals(marketingCargoCompanyContacts.getReplyResult())) {
	        		html3.append(" checked ");
	        	} 
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_NOT_INTENTION_KEY+"' id='RadioGroup1_3' />");
	        	html3.append(Constants.COMP_REPLYRESULT_NOT_INTENTION_VALUE);
	        	html3.append("</label>");
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	if("4".equals(marketingCargoCompanyContacts.getReplyResult())) {
	        		html3.append(" checked ");
	        	} 
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_NOT_SPECIFY_KEY+"' id='RadioGroup1_4' />");
	        	html3.append(Constants.COMP_REPLYRESULT_NOT_SPECIFY_VALUE);
	        	html3.append("</label>");
				html3.append("<label>");
				html3.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：");
				html3.append("<input type='text'");
				if(StringUtils.isNotEmpty(marketingCargoCompanyContacts.getRemark())) {
					html3.append(" name='remark' value='"+marketingCargoCompanyContacts.getRemark()+"' class=\"boxrk\" id='remark' />");
				}else {
					html3.append(" name='remark' value='' class=\"boxrk\" id='remark' />");
				}
				html3.append("<input type='hidden' name='replyResultHtmlId' value='"+marketingCargoCompanyContacts.getReplyResult()+"' id='replyResultHtmlId' />");
				html3.append("</label>");
	        }else {
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_INVALID_KEY+"' id='RadioGroup1_0' />");
	        	html3.append(Constants.COMP_REPLYRESULT_INVALID_VALUE);
	        	html3.append("</label>");
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_INTENTION_KEY+"' id='RadioGroup1_2' />");
	        	html3.append(Constants.COMP_REPLYRESULT_INTENTION_VALUE);
	        	html3.append("</label>");
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_NOT_INTENTION_KEY+"' id='RadioGroup1_3' />");
	        	html3.append(Constants.COMP_REPLYRESULT_NOT_INTENTION_VALUE);
	        	html3.append("</label>");
	        	html3.append("<label>");
	        	html3.append("<input type='radio'");
	        	html3.append(" name='replyResult' value='"+Constants.COMP_REPLYRESULT_NOT_SPECIFY_KEY+"' id='RadioGroup1_4' />");
	        	html3.append(Constants.COMP_REPLYRESULT_NOT_SPECIFY_VALUE);
	        	html3.append("</label>");
				html3.append("<label>");
				html3.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：");
				html3.append("<input type='text' name='remark' value='' class='boxrk' id='remark' />");
				html3.append("<input type='hidden' name='replyResultHtmlId' value='' id='replyResultHtmlId' />");
				html3.append("</label>");

	        }
	        html3.append("</dt><dd>");
	        html3.append("<div class=\"return fields\"><a  href=\"javascript:saveCompanyRecord();\">确定</a></div>");
	        html3.append("</dd>");
	        orderCargoInfoDomain.setHtml1(html1.toString());
	        orderCargoInfoDomain.setHtml2(html2.toString());
	        orderCargoInfoDomain.setHtml3(html3.toString());
		 return JSonRespone.makeHasContentJSonRespone("0", "success",orderCargoInfoDomain);
	}

}
