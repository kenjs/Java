package com.cy.dcts.log.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dcts.common.bo.OperationLogInfo;
import com.cy.dcts.log.dao.OperationLogDao;
import com.cy.dcts.log.service.OperationLogService;
/**
 * 操作日志service imple
 * @author haoyong
 *
 */
public class OperationLogServiceImpl implements OperationLogService {

	private OperationLogDao operationLogDao;
	
	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public int insertOperationLog(OperationLogInfo bo) {
		return operationLogDao.insertOperationLog(bo);
	}

}
