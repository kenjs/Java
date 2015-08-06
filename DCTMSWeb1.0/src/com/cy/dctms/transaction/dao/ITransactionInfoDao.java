package com.cy.dctms.transaction.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.TransactionInfoDomain;

public interface ITransactionInfoDao {

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
	public TransactionInfoDomain queryTransactionInfoById(String id);	

	/**
	 * ���潻����Ϣ��Ϣ
	 * @author:wjl
	 */
	public void saveTransactionInfo(TransactionInfoDomain transactionInfoDomain,String userId);

	/**
	 * ɾ��������Ϣ��Ϣ
	 * @author:wjl
	 */
	public void deleteTransactionInfo(String id,String userId);
}
