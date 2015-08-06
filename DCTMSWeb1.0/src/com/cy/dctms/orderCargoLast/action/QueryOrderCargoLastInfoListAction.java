package com.cy.dctms.orderCargoLast.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.OrderCargoLastInfoDomain;
import com.cy.dctms.orderCargoLast.service.IOrderCargoLastInfoService;

public class QueryOrderCargoLastInfoListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IOrderCargoLastInfoService orderCargoLastInfoService;
	private OrderCargoLastInfoDomain orderCargoLastInfoDomain;

	/** 查询货源历史状态信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query orderCargoLastInfo list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (orderCargoLastInfoDomain==null) {
			orderCargoLastInfoDomain = new OrderCargoLastInfoDomain();
		}
		if (orderCargoLastInfoDomain.getPageInfo()==null) {
			orderCargoLastInfoDomain.setPageInfo(new PageInfo());
		}
		orderCargoLastInfoService.queryOrderCargoLastInfoList(orderCargoLastInfoDomain);
		return SUCCESS;
	}

	public void setOrderCargoLastInfoService(IOrderCargoLastInfoService orderCargoLastInfoService) {
		this.orderCargoLastInfoService = orderCargoLastInfoService;
	}
	public OrderCargoLastInfoDomain getOrderCargoLastInfoDomain() {
		return orderCargoLastInfoDomain;
	}

	public void setOrderCargoLastInfoDomain(OrderCargoLastInfoDomain orderCargoLastInfoDomain) {
		this.orderCargoLastInfoDomain = orderCargoLastInfoDomain;
	}


}
