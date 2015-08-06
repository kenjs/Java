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
 * 根据id查询货物
 * @author nxj
 *
 */
public class QueryOrderCargoQuoteByIdAction extends BaseJsonAction {

	
	private static final long serialVersionUID = 6288699309693217723L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			if (getSessionUser() == null) {
				this.sendResponseToJson("1", "请先登录");
				return ;
			}
			String orderCargoId = this.request.getParameter("orderCargoId"); 
			List<OrderCargoInfoDomain> list = new ArrayList<OrderCargoInfoDomain>();
			OrderCargoInfoDomain orderCargoInfoDomain = new OrderCargoInfoDomain();
			orderCargoInfoDomain.setDeployUserid(getSessionUser().getId());
			orderCargoInfoDomain.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			orderCargoInfoDomain.setCargoFlag(Constants.CARGO_FLAG_PENDING_TRADE_KEY);
			orderCargoInfoDomain.setId(orderCargoId);
			list = queryOrderCargoInfoService.queryOrderCargoQuoteInfoList(orderCargoInfoDomain);
			if(list.size() == 1) {
				String result = this.sendResponseToJson("0","根据id查询货源查询成功!",list.get(0));
				logger.warn("query my ordercargo success. json=[{}]",new Object[] { result });
			}
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
