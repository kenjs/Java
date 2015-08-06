package com.cy.dcts.orderCargo.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.QuoteInfoDomain;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

/**
 * 根据货物Id查询货物报价列表
 * @author nxj
 *
 */
public class QueryQuoteListAction extends BaseJsonAction  {

	private static final long serialVersionUID = -6247864054561628114L;
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
			List<QuoteInfoDomain> list = new ArrayList<QuoteInfoDomain>();
			QuoteInfoDomain quoteInfoDomain = new QuoteInfoDomain();
			quoteInfoDomain.setCargoId(orderCargoId);
			quoteInfoDomain.setStart(String.valueOf(Constants.DELETED_FLAG_FALSE));
			list = queryOrderCargoInfoService.queryQuoteInfoList(quoteInfoDomain);
			String result = this.sendResponseToJson("0","根据货源ID查询货源报价成功!",list);
			logger.warn("query ordercargo quoteinfo success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("query ordercargo quoteinfo error!");
			throw new RuntimeException();
		}
		
	}

	public void setQueryOrderCargoInfoService(
			IQueryOrderCargoInfoService queryOrderCargoInfoService) {
		this.queryOrderCargoInfoService = queryOrderCargoInfoService;
	}
}
