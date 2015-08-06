package com.cy.dcts.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.OperationLogInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.log.service.OperationLogService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 操作日志拦截
 * @author haoyong
 *
 */
public class DCTSOperationLogInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6805305373795758183L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OperationLogService operationLogService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log2Db(invocation);
		return invocation.invoke();
	}

	private void log2Db(ActionInvocation invocation) {
		try {
			OperationLogInfo bo = new OperationLogInfo();
			WebUserInfo info = null;
			Object obj = invocation.getInvocationContext().getSession().
									get(Constants.SESSION_LOGIN_USER);			
			if(obj != null) {
				info = (WebUserInfo) obj;
				bo.setUserriverId(Integer.parseInt(info.getId()));
			}
			
			String actioName = invocation.getProxy().getActionName();
			for(DCTSActionEnum e : DCTSActionEnum.values()) {
				if(actioName.equals(e.operationName())) {					
					bo.setOperationType(e.operationType());
					bo.setOperationName(e.operationName());
					bo.setRemark(e.remark());
					
					operationLogService.insertOperationLog(bo);
				}
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
