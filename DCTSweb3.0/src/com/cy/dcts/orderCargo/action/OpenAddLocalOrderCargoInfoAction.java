package com.cy.dcts.orderCargo.action;

import org.slf4j.Logger;



import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BasePageAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.DataDictInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;

/**
 *  打开新增保存 本地货源信息页面 action
 * @author zdy
 */
public class OpenAddLocalOrderCargoInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private OrderCargoInfoDomain orderCargoInfoDomain;
	private DataDictInfoDomain dataDictInfoDomain = new DataDictInfoDomain();//初始化数据字典
	@Override
	protected String execMethod() throws Exception {
		//是否登陆
		if (getSessionUser() == null) {
			return LOGIN;
		}
		logger.debug("open add order cargo info page begin ,mobilePhone=[{}]",getSessionUser().getMobilephone());
		if(orderCargoInfoDomain==null){
			orderCargoInfoDomain=new OrderCargoInfoDomain();
		}
		//数据库中没有保存当前登录人的固定电话
		orderCargoInfoDomain.setContactName(getSessionUser().getName());//当前登录人的姓名
		orderCargoInfoDomain.setContactMobilephone(getSessionUser().getMobilephone());//当前登录人的手机号
		
//		orderCargoInfoDomain = new OrderCargoInfoDomain();
//		orderCargoInfoDomain.setStartProvince(getUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_START_PROVINCE));
//		orderCargoInfoDomain.setStartCity(getUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_START_CITY));
//		orderCargoInfoDomain.setStartCounty(getUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_START_COUNTY));
//		orderCargoInfoDomain.setEndProvince(getUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_END_PROVINCE));
//		orderCargoInfoDomain.setEndCity(getUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_END_CITY));
//		orderCargoInfoDomain.setEndCounty(getUserPerferenceInfo(UserPreferenceInfo.DEPLOY_CARGO_END_COUNTY));
		if(orderCargoInfoDomain!=null&&Constants.PUBLISH_CARGO.equals(orderCargoInfoDomain.getMenuAId())){
			return "mycenter";
		}
		return "mainAdd";
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



}
