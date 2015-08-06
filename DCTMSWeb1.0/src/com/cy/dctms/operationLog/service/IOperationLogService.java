package com.cy.dctms.operationLog.service;

import java.util.List;

import com.cy.dctms.common.domain.OperationLogDomain;

public interface IOperationLogService {
	
	/**
	 * 查询操作日志信息列表
	 * @author:wjl
	 */
	public void queryOperationLogList(OperationLogDomain operationLogDomain);
	/**
	 * 导出操作日志信息列表
	 * @author:wjl
	 */
	public void exportOperationLog(OperationLogDomain operationLogDomain);
}
	
