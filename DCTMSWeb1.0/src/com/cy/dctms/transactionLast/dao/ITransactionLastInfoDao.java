package com.cy.dctms.transactionLast.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.TransactionLastInfoDomain;

public interface ITransactionLastInfoDao {

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
