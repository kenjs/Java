package com.cy.dcts.orderCargo.action;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DataDictInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.util.ConvertOrderCargoInfoUtil;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;

/**
 * 修改货源action
 * @author zdy
 */
public class ModifyLocalOrderCargoInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;
	private DataDictInfoDomain dataDictInfoDomain = new DataDictInfoDomain();//初始化数据字典表
	
	
	@Override
	protected String execMethod() throws Exception{
		//判断是否登陆
		if(getSessionUser() == null){
			return LOGIN;
		}		
		logger.debug("modify local order cargo info begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		
		// 参数验证
				if (orderCargoInfoDomain == null 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getStartProvince()) 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getStartCity()) 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getEndProvince()) 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getEndCity()) 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getContactName())  
						|| orderCargoInfoDomain.getRequestStartTime()==null ) {
					logger.warn("add local order cargo info parameter error. userId=[{}], companyId=[{}]",getSessionUser().getId(), getSessionUser().getCompanyId());
					orderCargoInfoDomain.setErrorMessage("修改失败，参数不能为空！");
					return ERROR;
				}
		//参数转换
		if("请选择".equals(orderCargoInfoDomain.getRequestCarLength())) {
			orderCargoInfoDomain.setRequestCarLength("");
		}
		if("请选择".equals(orderCargoInfoDomain.getRequestCarBarType())) {
			orderCargoInfoDomain.setRequestCarBarType("");
		}
		if("请选择".equals(orderCargoInfoDomain.getRequestCarPlateType())) {
			orderCargoInfoDomain.setRequestCarPlateType("");
		}
		
		//判断货源是否存在
		//load
		OrderCargoInfo orderCargoInfo = queryOrderCargoInfoService.queryOrderCargoInfoById(orderCargoInfoDomain.getId());
		if(orderCargoInfo == null){
			orderCargoInfoDomain.setErrorMessage("修改失败，货源不存在");
			logger.warn("modify local order cargo info error, cargoId=[{}], message=[{}], userId=[{}], companyId=[{}]", 
					new Object[]{orderCargoInfoDomain.getId(), orderCargoInfoDomain.getErrorMessage(),
					getSessionUser().getId(), getSessionUser().getCompanyId()});
			return ERROR;
		}
		// 只能修改自己创建的货物（权限验证）
		if(orderCargoInfoDomain == null || !getSessionUser().getId().equals(orderCargoInfo.getDeployUserid())) {
			orderCargoInfoDomain.setErrorMessage("修改失败，权限不足！");
			logger.warn("modify local order cargo info error, cargoId=[{}], message=[{}], userId=[{}], companyId=[{}]", 
					new Object[]{orderCargoInfoDomain.getId(), orderCargoInfoDomain.getErrorMessage(),
					getSessionUser().getId(), getSessionUser().getCompanyId()});
			return ERROR;
	   }
		
		//log
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMATE_DAY);
		if(dateFormat.parse(dateFormat.format(orderCargoInfo.getRequestStartTime())).getTime() 
				< dateFormat.parse(dateFormat.format(new Date())).getTime()
			&& (dateFormat.parse(dateFormat.format(orderCargoInfo.getRequestStartTime())).getTime()
					!= dateFormat.parse(orderCargoInfoDomain.getRequestStartTime()).getTime())){
			//重新发布-添加到日志表中（待续）
			
			
//			logger.info("TAG=[{}], USERID=[{}], USERCODE=[{}], CARGOID=[{}], REDEPLOYFLAG=[{}], TIME=[{}]",
//				new Object[]{Constants.WEB_USER_RE_ADD_ORDER_CARGO_TAG,  
//				getSessionUser().getId(), getSessionUser().getCode(), orderCargoInfo.getId(), 1,
//				new SimpleDateFormat(Constants.DATE_FORMATE_LONG).format(new Date())});
		}else if(dateFormat.parse(dateFormat.format(orderCargoInfo.getRequestStartTime())).getTime()
				!= dateFormat.parse(orderCargoInfoDomain.getRequestStartTime()).getTime()){
			//修改货源的发布时间了-添加到日志表中（待续）
			
//			logger.info("TAG=[{}], USERID=[{}], USERCODE=[{}], CARGOID=[{}], REDEPLOYFLAG=[{}], TIME=[{}]",
//			new Object[]{Constants.WEB_USER_MODIFY_ORDER_CARGO_TIME_TAG,  
//			getSessionUser().getId(), getSessionUser().getCode(), orderCargoInfo.getId(), 1,
//			new SimpleDateFormat(Constants.DATE_FORMATE_LONG).format(new Date())});
		}
		
		saveOrderCargoInfoService.modifyOrderCargoInfo(ConvertOrderCargoInfoUtil.getOrderCargoInfoFromDomain(orderCargoInfoDomain), getSessionUser());
		logger.debug("modify local order cargo info success! userId=[{}], companyId=[{}], cargoId=[{}]",
				new Object[] { getSessionUser().getId(), getSessionUser().getCompanyId(), orderCargoInfoDomain.getId()});
		orderCargoInfoDomain.setErrorMessage("success");
		return SUCCESS;
	}
	

	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}


	public OrderCargoInfoDomain getOrderCargoInfoDomain() {
		return orderCargoInfoDomain;
	}


	public void setOrderCargoInfoDomain(OrderCargoInfoDomain orderCargoInfoDomain) {
		this.orderCargoInfoDomain = orderCargoInfoDomain;
	}


	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
	
	public DataDictInfoDomain getDataDictInfoDomain() {
		return dataDictInfoDomain;
	}

}
