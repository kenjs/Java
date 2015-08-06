package com.cy.dcts.log.dao;

import com.cy.dcts.common.bo.OperationLogInfo;

/**
 * 操作日志dao
 * @author haoyong
 *
 */
public interface OperationLogDao {

	/**
	 * 新增操作日志
	 */
	public int insertOperationLog(OperationLogInfo bo);
}
