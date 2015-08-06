package com.cy.dctms.transaction.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.TransactionInfoDomain;

public interface ITransactionInfoDao {

	/**
	 * 查询交易信息信息列表
	 * @author:wjl
	 */
	public void queryTransactionInfoList(TransactionInfoDomain transactionInfoDomain);

	/**
	 * 导出交易信息信息列表
	 * @author:wjl
	 */
	public void exportTransactionInfo(TransactionInfoDomain transactionInfoDomain);

	/**
	 * 查交易信息信息明细根据ID
	 * @author:wjl
	 */
	public TransactionInfoDomain queryTransactionInfoById(String id);	

	/**
	 * 保存交易信息信息
	 * @author:wjl
	 */
	public void saveTransactionInfo(TransactionInfoDomain transactionInfoDomain,String userId);

	/**
	 * 删除交易信息信息
	 * @author:wjl
	 */
	public void deleteTransactionInfo(String id,String userId);
}
