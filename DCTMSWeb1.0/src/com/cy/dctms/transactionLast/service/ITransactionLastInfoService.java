package com.cy.dctms.transactionLast.service;

import java.util.List;

import com.cy.dctms.common.domain.TransactionLastInfoDomain;

public interface ITransactionLastInfoService {
	
	/**
	 * ��ѯ������ʷ״̬��Ϣ�б�
	 * @author:wjl
	 */
	public void queryTransactionLastInfoList(TransactionLastInfoDomain transactionLastInfoDomain);
	
	/**
	 * ����������ʷ״̬��Ϣ�б�
	 * @author:wjl
	 */
	public void exportTransactionLastInfo(TransactionLastInfoDomain transactionLastInfoDomain);

	
}
	
