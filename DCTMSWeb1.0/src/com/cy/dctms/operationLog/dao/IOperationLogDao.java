package com.cy.dctms.operationLog.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.OperationLogDomain;

public interface IOperationLogDao {

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
