package com.cy.dctms.transaction.service;

import java.util.List;

import com.cy.dctms.common.domain.TransactionInfoDomain;

public interface ITransactionInfoService {
	
	/**
	 * ��ѯ������Ϣ��Ϣ�б�
	 * @author:wjl
	 */
	public void queryTransactionInfoList(TransactionInfoDomain transactionInfoDomain);
	
	/**
	 * ����������Ϣ��Ϣ�б�
	 * @author:wjl
	 */
	public void exportTransactionInfo(TransactionInfoDomain transactionInfoDomain);

	/**
	 * �齻����Ϣ��Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public TransactionInfoDomain queryTransactionInfoMxById(String id);

	/**
	 * ���潻����Ϣ��Ϣ
	 * @author:wjl
	 */
	public void saveTransactionInfo(TransactionInfoDomain transactionInfoDomain ,String userId);

	/**
	 * ɾ��������Ϣ��Ϣ
	 * @author:wjl
	 */
	public void deleteTransactionInfo(String id,String userId);
	
	
}
	
