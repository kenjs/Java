package com.cy.dctms.operationLog.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.OperationLogDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.operationLog.dao.IOperationLogDao;
import com.cy.dctms.operationLog.service.IOperationLogService;

public class OperationLogServiceImp implements IOperationLogService {

	private IOperationLogDao operationLogDao;

	@Override
	public void queryOperationLogList(OperationLogDomain operationLogDomain) {
		operationLogDao.queryOperationLogList(operationLogDomain);
	}

	@Override
	public void exportOperationLog(OperationLogDomain operationLogDomain) {
		 operationLogDao.exportOperationLog(operationLogDomain);
	}

	public void setOperationLogDao(IOperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}
	

}
