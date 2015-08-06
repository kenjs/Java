package com.cy.dctms.transactionLast.service;

import java.util.List;

import com.cy.dctms.common.domain.TransactionLastInfoDomain;

public interface ITransactionLastInfoService {
	
	/**
	 * 查询订单历史状态信息列表
	 * @author:wjl
	 */
	public void queryTransactionLastInfoList(TransactionLastInfoDomain transactionLastInfoDomain);
	
	/**
	 * 导出订单历史状态信息列表
	 * @author:wjl
	 */
	public void exportTransactionLastInfo(TransactionLastInfoDomain transactionLastInfoDomain);

	
}
	
