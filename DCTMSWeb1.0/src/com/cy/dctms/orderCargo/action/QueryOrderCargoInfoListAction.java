package com.cy.dctms.orderCargo.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.OrderCargoInfoDomain;
import com.cy.dctms.orderCargo.service.IOrderCargoInfoService;

public class QueryOrderCargoInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IOrderCargoInfoService orderCargoInfoService;
	private OrderCargoInfoDomain orderCargoInfoDomain;

	/** 查询货物信息信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query orderCargoInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (orderCargoInfoDomain==null) {
			orderCargoInfoDomain = new OrderCargoInfoDomain();
		}
		if (orderCargoInfoDomain.getPageInfo()==null) {
			orderCargoInfoDomain.setPageInfo(new PageInfo());
		}
		orderCargoInfoService.queryOrderCargoInfoList(orderCargoInfoDomain);
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
