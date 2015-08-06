package com.cy.dcts.mainIndex.action;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

/**
 * 最新货源
 * @author nxj
 *
 */
public class NewOrderCargoStartAction extends BaseJsonAction {

	private static final long serialVersionUID = 7989278923618953990L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IQueryOrderCargoInfoService queryOrderCargoInfoService;
	
	@Override
	protected void execMethod() throws Exception {
		try {
			String curPage = this.request.getParameter("curPage");
			String pageSize = this.request.getParameter("pageSize");
			List<OrderCargoInfoDomain> list = new ArrayList<OrderCargoInfoDomain>();
			OrderCargoInfoDomain orderCargoInfoDomain = new OrderCargoInfoDomain();
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(Integer.parseInt(curPage));
			pageInfo.setPageSize(Integer.parseInt(pageSize));
			orderCargoInfoDomain.setPageInfo(pageInfo);
			orderCargoInfoDomain.setCargoFlag(Constants.CARGO_FLAG_PENDING_TRADE_KEY);
			orderCargoInfoDomain.setDeletedFlag(String.valueOf(Constants.DELETED_FLAG_FALSE));
			list = queryOrderCargoInfoService.queryStartDeployOrderCargoInfoByPage(orderCargoInfoDomain);
			orderCargoInfoDomain.setList(list);
			String result = this.sendResponseToJson("1","查询最新货源成功!",orderCargoInfoDomain);
			logger.warn("query cargo success. json=[{}]",new Object[] { result });
		}catch (Exception e) {
			logger.error("query cargo error!");
			throw new RuntimeException();
		}
		
	}

	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
}
