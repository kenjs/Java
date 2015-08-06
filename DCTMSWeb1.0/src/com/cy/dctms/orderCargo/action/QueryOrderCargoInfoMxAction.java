package com.cy.dctms.orderCargo.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.OrderCargoInfoDomain;
import com.cy.dctms.orderCargo.service.IOrderCargoInfoService;

public class QueryOrderCargoInfoMxAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IOrderCargoInfoService orderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;

	/** 查询货物信息信息明细
	 * @author:wjl
	 * @time:2013-04-16 11:15:00
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query orderCargoInfo Mx start");
		if(getSessionUser()==null){
			return "loginMx";
		}
		if(!orderCargoInfoDomain.getId().equals("0")){
			orderCargoInfoDomain = orderCargoInfoService.queryOrderCargoInfoMxById(orderCargoInfoDomain.getId());
		}else {
			orderCargoInfoDomain = new OrderCargoInfoDomain();
			orderCargoInfoDomain.setId("0");
		}
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
