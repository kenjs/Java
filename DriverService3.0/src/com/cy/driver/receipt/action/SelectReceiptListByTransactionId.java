package com.cy.driver.receipt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.receipt.domain.TransactionReceiptPathDomain;
import com.cy.driver.receipt.service.TransactionReceiptPathService;
/**
 * 根据订单id查询
 * @author haoyong
 *
 */
public class SelectReceiptListByTransactionId extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5928750641372500944L;

	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionReceiptPathService transactionReceiptPathService;
	private OperationLogService operationLogService;
	
	@Override
	protected void execMethod() throws Exception {
		try {
			String driverId = request.getParameter("driverId");		
			String transactionId = request.getParameter("transactionId");
			String type = request.getParameter("type");
						
			if(StringUtils.isBlank(driverId)) {
				log.info("司机不存在");
				sendResponseToJson("-9", "司机不存在");
				return;
			}
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return;
			}
			log2Db(driverId);
			
			Map<String,String> map = new HashMap<String, String>();
			map.put("transactionId", transactionId);
			map.put("type", type);
			
			List<TransactionReceiptPathDomain> list = transactionReceiptPathService.selectReceiptListByTransactionId(map);
			if(list != null){
				if(list.size() == 0){
					this.sendResponseToJson("0", "未找到符合条件的信息");
					log.info("未找到符合条件的信息");
				}else {
					this.sendResponseToJson("1","查询成功!",list);
					log.info("查询成功");
				}
			}else {
				this.sendResponseToJson("0","未找到符合条件的信息");
				log.info("未找到符合条件的信息");
			}
		} catch (Exception e) {
			log.error("Query Info fail."+ e.getMessage());			
			sendResponseToJson("-8", e.getMessage());
			e.printStackTrace();
		}
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectReceiptListByTransactionId");
		bo.setOperationType(69);
		bo.setRemark("根据订单id查找回单");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setTransactionReceiptPathService(
			TransactionReceiptPathService transactionReceiptPathService) {
		this.transactionReceiptPathService = transactionReceiptPathService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
