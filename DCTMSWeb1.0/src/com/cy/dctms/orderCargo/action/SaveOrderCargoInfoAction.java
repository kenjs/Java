package com.cy.dctms.orderCargo.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.OrderCargoInfoDomain;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.orderCargo.service.IOrderCargoInfoService;
import com.cy.dctms.workLog.service.IManagerWorkLogInfoService;

public class SaveOrderCargoInfoAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IOrderCargoInfoService orderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;

	/** 保存货物信息信息
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("save orderCargoInfo start");
		if(getSessionUser()==null){
			sendResponseMessage("loginMx");
			return SUCCESS;
		}
		orderCargoInfoService.saveOrderCargoInfo(orderCargoInfoDomain,getSessionUser().getId());
		return SUCCESS;
	}

	public void setOrderCargoInfoService(IOrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}
	public OrderCargoInfoDomain getOrderCargoInfoDomain() {
		return orderCargoInfoDomain;
	}
	public void setOrderCargoInfoDomain(OrderCargoInfoDomain orderCargoInfoDomain) {
		this.orderCargoInfoDomain = orderCargoInfoDomain;
	}


}
