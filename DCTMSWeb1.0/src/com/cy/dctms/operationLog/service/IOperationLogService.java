package com.cy.dctms.operationLog.service;

import java.util.List;

import com.cy.dctms.common.domain.OperationLogDomain;

public interface IOperationLogService {
	
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
	
