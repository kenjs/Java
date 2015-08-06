package com.cy.dcts.orderCargoLast.service;

import com.cy.dcts.common.bo.TransactionInfo;


public interface IOrderCargoLastService {
	/**
	 * 增加货源状态历史记录
	 * @param orderCargoLastInfo
	 * @return
	 */
	String addOrderCargoLastInfo(TransactionInfo transactionInfo,String stateType);
}
