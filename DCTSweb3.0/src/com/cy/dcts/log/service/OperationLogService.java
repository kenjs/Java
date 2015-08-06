package com.cy.dcts.log.service;

import com.cy.dcts.common.bo.OperationLogInfo;

/**
 * 操作日志service
 * @author haoyong
 *
 */
public interface OperationLogService {

	/**
	 * 新增操作日志
	 * @param bo
	 * @return
	 */
	public int insertOperationLog(OperationLogInfo bo);
}
