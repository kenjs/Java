package com.cy.dcts.orderCargo.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.DataDictInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.util.ConvertOrderCargoInfoUtil;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;
import com.cy.dcts.orderCargo.service.ISaveOrderCargoInfoService;

/**
 * 添加货源 action
 * @author zdy
 */
public class AddLocalOrderCargoInfoAction extends BaseJsonAction  {

	
	private static final long serialVersionUID = 6601989558296876680L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryOrderCargoInfoService queryOrderCargoInfoService; 
	private ISaveOrderCargoInfoService saveOrderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;
	private DataDictInfoDomain dataDictInfoDomain = new DataDictInfoDomain();//初始化字典表
	@Override
	protected void execMethod() throws Exception {
		String id="";
		
		//判断是否登陆
		if (getSessionUser() == null) {
			this.sendResponseToJson("1", "请先登录");
			return ;
		}
		logger.debug("add local order cargo info begin. userId=[{}], companyId=[{}]",
				getSessionUser().getId(), getSessionUser().getCompanyId());
		
		if(!StringUtils.isEmpty(orderCargoInfoDomain.getId())){//重新发布
			OrderCargoInfo orderCargoInfo;
			//根据Id查询货源
			orderCargoInfo=queryOrderCargoInfoService.queryOrderCargoInfoById(orderCargoInfoDomain.getId());
			if(orderCargoInfo==null){
				this.sendResponseToJson("3", "重新发布失败，查询错误！");
				return ;
			}else{
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				//装货时间为空时默认为当前日期
				if(StringUtils.isEmpty(orderCargoInfoDomain.getRequestStartTime())){
					orderCargoInfo.setRequestStartTime(new Date());
				}else{
					orderCargoInfo.setRequestStartTime(format.parse(orderCargoInfoDomain.getRequestStartTime()));
				}
				//卸货时间为空时就为空
				if(StringUtils.isEmpty(orderCargoInfoDomain.getRequestEndTime())){
					orderCargoInfo.setRequestEndTime(null);
				}else{
					orderCargoInfo.setRequestEndTime(format.parse(orderCargoInfoDomain.getRequestEndTime()));
				}
			id=saveOrderCargoInfoService.addOrderCargoInfo(orderCargoInfo,getSessionUser());
			}
		}else{//发布新货源
		
		//参数转换
		// 参数验证
				if (orderCargoInfoDomain == null 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getStartProvince()) 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getStartCity()) 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getEndProvince()) 
						|| StringUtils.isEmpty(orderCargoInfoDomain.getEndCity())
						|| StringUtils.isEmpty(orderCargoInfoDomain.getContactName()) 
						|| orderCargoInfoDomain.getRequestStartTime()==null ) {
					logger.warn("add local order cargo info parameter error. userId=[{}], companyId=[{}]",getSessionUser().getId(), getSessionUser().getCompanyId());
					this.sendResponseToJson("2", "添加失败，带*的必填项不能为空！");
					return ;
				}
		
		//下拉框选择内容转换(货物类型 int 0-请选择)
		if("请选择".equals(orderCargoInfoDomain.getRequestCarLength())) {
			orderCargoInfoDomain.setRequestCarLength("");
		}
		if("请选择".equals(orderCargoInfoDomain.getRequestCarBarType())) {
			orderCargoInfoDomain.setRequestCarBarType("");
		}
		if("请选择".equals(orderCargoInfoDomain.getRequestCarPlateType())) {
			orderCargoInfoDomain.setRequestCarPlateType("");
		}
		

		//保存用户喜好设置
//		putUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_START_PROVINCE, orderCargoInfoDomain.getStartProvince());
//		putUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_START_CITY, orderCargoInfoDomain.getStartCity());
//		putUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_START_COUNTY, orderCargoInfoDomain.getStartCounty());
//		putUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_END_PROVINCE, orderCargoInfoDomain.getEndProvince());
//		putUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_END_CITY, orderCargoInfoDomain.getEndCity());
//		putUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_END_COUNTY, orderCargoInfoDomain.getEndCounty());
//		
		
		 id = saveOrderCargoInfoService.addOrderCargoInfo(ConvertOrderCargoInfoUtil.getOrderCargoInfoFromDomain(orderCargoInfoDomain), getSessionUser());
		}
		logger.debug(
				"add local order cargo info success! userId=[{}], companyId=[{}], cargoId=[{}]",
				new Object[] { getSessionUser().getId(), getSessionUser().getCompanyId(), id });
		this.sendResponseToJson("0", "success",id);
	}

	public void setSaveOrderCargoInfoService(
			ISaveOrderCargoInfoService saveOrderCargoInfoService) {
		this.saveOrderCargoInfoService = saveOrderCargoInfoService;
	}

	public OrderCargoInfoDomain getOrderCargoInfoDomain() {
		return orderCargoInfoDomain;
	}

	public void setOrderCargoInfoDomain(
			OrderCargoInfoDomain orderCargoInfoDomain) {
		this.orderCargoInfoDomain = orderCargoInfoDomain;
	}

	public DataDictInfoDomain getDataDictInfoDomain() {
		return dataDictInfoDomain;
	}

	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}


}
