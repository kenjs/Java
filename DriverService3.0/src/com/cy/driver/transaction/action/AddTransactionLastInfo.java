package com.cy.driver.transaction.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.bo.TransactionLastInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.transaction.service.TransactionInfoService;

/**
 * 新增交易状态历史
 * @author haoyong
 *
 */
public class AddTransactionLastInfo extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3607275729131402425L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionInfoService transactionInfoService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			String cargoId = request.getParameter("cargoId");
			String transactionId = request.getParameter("transactionId");
			String remark = request.getParameter("remark");
						
			if(StringUtils.isBlank(driverId)) {
				log.info("司机不存在");
				sendResponseToJson("-9", "司机不存在");
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
			
			if(StringUtils.isBlank(cargoId)) {
				log.info("货源不存在");
				sendResponseToJson("-8", "货源不存在");
				return ERROR;
			}
			if(StringUtils.isBlank(transactionId)) {
				log.info("交易不存在");
				sendResponseToJson("-8", "交易不存在");
				return ERROR;
			}
			TransactionLastInfoBo bo = new TransactionLastInfoBo();
			bo.setDriverId(driverId);
			bo.setCargoId(cargoId);
			bo.setTransactionId(transactionId);
			bo.setRemark(remark);
			int key = transactionInfoService.addTransactionLastInfo(bo);
			if(key != 0) {
				log.info("新增交易状态历史成功");
				sendResponseToJson("1", "新增交易状态历史成功");
			} else {
				log.info("新增交易状态历史失败");
				sendResponseToJson("-8", "新增交易状态历史失败");
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
		bo.setOperationName("addTransactionLastInfo");
		bo.setOperationType(54);
		bo.setRemark("新增交易状态历史");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	
}
