package com.cy.dctms.operationLog.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.OperationLogDomain;

public interface IOperationLogDao {

	/**
	 * ��ѯ������־��Ϣ�б�
	 * @author:wjl
	 */
	public void queryOperationLogList(OperationLogDomain operationLogDomain);

	/**
	 * ����������־��Ϣ�б�
	 * @author:wjl
	 */
	public void exportOperationLog(OperationLogDomain operationLogDomain);

}
