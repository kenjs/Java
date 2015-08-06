package com.cy.driver.transaction.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.transaction.service.TransactionInfoService;
/**
 * 企业用车订单条数
 * @date 2014-6-5
 * @author haoyong
 *
 */
public class TransactionNumberWithComAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5761588008183253308L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionInfoService transactionInfoService; 

	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在");
				sendResponseToJson("-9","司机不存在");
				return ERROR;
			}
			
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(driverId);
			
			int num = transactionInfoService.selectDriverOrderNumber(driverId);
			if(num == 0) {
				log.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
			} else {
				log.info("查找成功, 共找到"+ num +"条数据");
				sendResponseToJson("1", "查找成功.",num);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setTransactionInfoService(
			TransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

	private OperationLogService operationLogService;

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("transactionNumberWithComAction");
		bo.setOperationType(32);
		bo.setRemark("企业用车订单条数");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}