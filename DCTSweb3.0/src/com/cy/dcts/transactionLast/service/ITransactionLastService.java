package com.cy.dcts.transactionLast.service;

import java.util.Map;

import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.TransactionLastInfo;


public interface ITransactionLastService {
	/**
	 * 增加交易状态历史记录（日志：有增无减）
	 * @param transactionLastInfo
	 * @return
	 */
	String addTransactionLastInfo(TransactionInfo transactionInfo);
	
	/**
	 *  根据交易id和交易状态查询交易历史记录
	 * @param queryMap
	 * @return
	 */
	TransactionLastInfo queryTransactionLastByIdAndStart(String transactionId,String start);
}
