package com.cy.dctms.operationLog.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.domain.PageInfo;
import com.cy.dctms.common.action.BasePageAction;
import com.cy.dctms.common.domain.OperationLogDomain;
import com.cy.dctms.operationLog.service.IOperationLogService;

public class QueryOperationLogListAction extends BasePageAction {

	private static final long serialVersionUID = -6071234793104457114L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private IOperationLogService operationLogService;
	private OperationLogDomain operationLogDomain;

	/** 查询操作日志信息列表
	 * @author:wjl
	 */
	@Override
	protected String execMethod() throws Exception {
		logger.debug("query operationLog list start");
		if(getSessionUser()==null){
			sendResponseMessage("login");
			return SUCCESS;
		}
		if (operationLogDomain==null) {
			operationLogDomain = new OperationLogDomain();
		}
		if (operationLogDomain.getPageInfo()==null) {
			operationLogDomain.setPageInfo(new PageInfo());
		}
		operationLogService.queryOperationLogList(operationLogDomain);
		return SUCCESS;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	public OperationLogDomain getOperationLogDomain() {
		return operationLogDomain;
	}

	public void setOperationLogDomain(OperationLogDomain operationLogDomain) {
		this.operationLogDomain = operationLogDomain;
	}


}
