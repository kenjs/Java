package com.cy.dcts.orderCargo.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

/**
 * 根据登录人的id获取待发货货源
 * @author nxj
 *
 */
public class QueryOrderCargoQuoteAction extends BaseJsonAction {

	private static final long serialVersionUID = -1347082609950796824L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			if (getSessionUser() == null) {
				this.sendResponseToJson("1", "请先登录");
				return ;
			}
			List<OrderCargoInfoDomain> list = new ArrayList<OrderCargoInfoDomain>();
			OrderCargoInfoDomain orderCargoInfoDomain = new OrderCargoInfoDomain();
			orderCargoInfoDomain.setDeployUserid(getSessionUser().getId());
			orderCargoInfoDomain.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			orderCargoInfoDomain.setCargoFlag(Constants.CARGO_FLAG_PENDING_TRADE_KEY);
			list = queryOrderCargoInfoService.queryOrderCargoQuoteInfoList(orderCargoInfoDomain);
			String result = this.sendResponseToJson("0","货源查询成功!",list);
			logger.warn("query my ordercargo success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("query my ordercargo error!");
			throw new RuntimeException();
		}
	}

	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
	

}
