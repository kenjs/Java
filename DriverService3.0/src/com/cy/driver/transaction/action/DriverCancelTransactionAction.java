package com.cy.driver.transaction.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.bo.TransactionLastInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
import com.cy.driver.transaction.service.TransactionInfoService;
/**
 * 司机异常取消订单
 * @author haoyong
 *
 */
public class DriverCancelTransactionAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2139826521865182234L;
	private Logger _log = LoggerFactory.getLogger(getClass());
	private TransactionInfoService transactionInfoService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)){
				_log.info("司机不存在. ");
				sendResponseToJson("-9", "司机不存在");
				return ERROR;
			}
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				_log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				_log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(driverId);
			
			
			String id = request.getParameter("transactionId");
			String reason = request.getParameter("cancelReason");
			String note = request.getParameter("note");
			if(StringUtils.isBlank(id)) {
				_log.info("交易不存在");
				sendResponseToJson("-8","交易不存在");
				return ERROR;
			}
			//如果订单状态已完成或异常取消或web端取消
			Map<String,Long> mapStart = transactionInfoService.selectTransactionStartById(id);
			if(mapStart != null) {
				if(mapStart.containsKey("tradeStart")) {
					if(mapStart.get("tradeStart") == 5) {
						_log.warn("id为" + id + "的交易已经完成，无法取消");
						sendResponseToJson("0", "该交易已经完成，无法取消。");
						return ERROR;
					}
				}
				if(mapStart.containsKey("tradeCancelOrigin")) {
					long origin = mapStart.get("tradeCancelOrigin");
					if(origin == 1) {
						_log.warn("id为" + id + "的交易已经被司机取消");
						sendResponseToJson("0", "该交易已经被司机取消");
						return ERROR;
					}
					if(origin == 2 ) {
						_log.warn("id为" + id + "的交易已经被货主取消");
						sendResponseToJson("0", "该交易已经被货主取消");
						return ERROR;
					}
				}
			}
			
			TransactionInfoDomain domain = transactionInfoService.selectDriverOrderDetail(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
//			map.put("tradeStart", "6");
//			map.put("orderStart", "1");
			map.put("tradeCancelOrigin", "1");
			int i = transactionInfoService.updateTransactionInfoById(map);
			if(i != 0) {
				_log.info("司机取消订单成功");
				sendResponseToJson("1","司机取消订单成功");
				TransactionLastInfoBo bo = new TransactionLastInfoBo();
				bo.setTransactionId(id);
				bo.setRemark(reason + ";" + note);
				bo.setCargoId(domain.getCargoId());
				bo.setDriverId(domain.getDriverId());
				transactionInfoService.addTransactionLastInfo(bo);
			} else {
				_log.info("司机取消订单失败");
				sendResponseToJson("0","司机取消订单失败");
			}
		} catch (IOException e) {
			_log.error(e.getMessage());
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
		bo.setOperationName("driverCancelTransaction");
		bo.setOperationType(55);
		bo.setRemark("司机取消订单");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
