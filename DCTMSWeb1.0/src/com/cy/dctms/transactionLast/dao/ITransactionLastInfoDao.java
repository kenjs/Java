package com.cy.dctms.transactionLast.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.TransactionLastInfoDomain;

public interface ITransactionLastInfoDao {

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
