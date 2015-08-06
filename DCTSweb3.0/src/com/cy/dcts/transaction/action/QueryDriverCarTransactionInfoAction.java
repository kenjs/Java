package com.cy.dcts.transaction.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseJsonAction;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.transaction.service.ITransactionInfoService;

/**
 * 根据司机Id查询司机交易记录
 * @author nxj
 *
 */
public class QueryDriverCarTransactionInfoAction extends BaseJsonAction {

	private static final long serialVersionUID = 178660064339093996L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ITransactionInfoService transactionInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			if (getSessionUser() == null) {
				this.sendResponseToJson("1", "请先登录");
				return;
			}
			String driverId = this.request.getParameter("driverId");
			String pageSize = this.request.getParameter("pageSize");
			String curPage = this.request.getParameter("curPage");
			if(StringUtils.isEmpty(driverId) || StringUtils.isEmpty(pageSize) || StringUtils.isEmpty(curPage)) {
				this.sendResponseToJson("2", "参数错误！");
				return;
			}
			TransactionInfoDomain transactionInfoDomain = new TransactionInfoDomain();
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageSize(Integer.parseInt(pageSize));
			pageInfo.setCurPage(Integer.parseInt(curPage)-1);
			pageInfo.setCurPageNo(Integer.parseInt(curPage));
			transactionInfoDomain.setPageInfo(pageInfo);
			transactionInfoDomain.setDriverId(driverId);
			transactionInfoDomain.setTradeStart(Constants.TRADE_START_SUCCESS_KEY);
			List<TransactionInfoDomain> list = transactionInfoService.queryDriverCarTransactionInfo(transactionInfoDomain);
			transactionInfoDomain.setList(list);
			String result = this.sendResponseToJson("0", "查询司机交易订单成功！",transactionInfoDomain);
			logger.warn("query driver car transactionInfo success. json=[{}]",new Object[] { result });
		}catch(Exception e) {
			logger.error("query driver car transactionInfo error!");
			throw new RuntimeException();
		}
	}

	public void setTransactionInfoService(
			ITransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

}
