package com.cy.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 记录操作日志
 * @author haoy
 *
 */
public class OperationLogInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762260829974687133L;
	private OperationLogService operationLogService;
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log2Db(invocation);
		return invocation.invoke();
	}

	private void log2Db(ActionInvocation invocation) {		
		StringBuilder sb = new StringBuilder();
		try {
			long startTime = System.currentTimeMillis();
			String actionName = invocation.getProxy().getActionName();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			String id = request.getParameter("userId");
			OperationLogInfoBo bo = new OperationLogInfoBo();
			
			for(DSActionEnum e : DSActionEnum.values()) {
				if(actionName.equals(e.operationName())) {
					bo.setOperationName(actionName);
					bo.setOperationType(e.operationType());
					bo.setRemark(e.remark());
					if(StringUtils.isNotBlank(id)) {
						bo.setUserDriverId(Integer.parseInt(id));
					}
					operationLogService.insertOperationLog(bo);
				}
			}
			long endTime = System.currentTimeMillis();
			long time = endTime - startTime;
			sb.append("请求action:").append(actionName).append(";耗时：").append(time).append("毫秒");
			log.info(sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
