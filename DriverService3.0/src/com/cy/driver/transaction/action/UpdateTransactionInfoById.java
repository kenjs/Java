package com.cy.driver.transaction.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.transaction.service.TransactionInfoService;

/**
 * 更改订单状态（卸货）
 * @date 2014-6-11
 * @author haoyong
 *
 */
public class UpdateTransactionInfoById extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6702532847600480959L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionInfoService transactionInfoService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在. ");
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
			
			String id = request.getParameter("id");
			String tradeStart = request.getParameter("tradeStart");
			if(StringUtils.isBlank(id)){
				sendResponseToJson("-8", "交易不存在");
				log.info("交易不存在.");
				return ERROR;
			}
			if(StringUtils.isBlank(tradeStart)){
				sendResponseToJson("-8", "交易状态未知");				
				return ERROR;
			}
			//如果订单已经完成或取消或已卸货或异常取消或web端取消, 无法继续操作
			Map<String,Long> mapStart = transactionInfoService.selectTransactionStartById(id);
			if(mapStart != null) {
				if(mapStart.containsKey("tradeStart")) {
					long start = mapStart.get("tradeStart");
					if(start == 5) {
						log.warn("id为" + id + "的交易已经完成，无法取消");
						sendResponseToJson("0", "该交易已经完成，无法取消。");
						return ERROR;
					}
					if(start == 6) {
						log.warn("id为" + id + "的交易已经取消");
						sendResponseToJson("0", "该交易已经取消");
						return ERROR;
					}
					if(start == 7) {
						log.warn("id为" + id + "的交易司机已卸货，无法取消");
						sendResponseToJson("0", "该交易司机已卸货，无法取消。");
						return ERROR;
					}
				}
				if(mapStart.containsKey("tradeCancelOrigin")) {
					long origin = mapStart.get("tradeCancelOrigin");
					if(origin == 1) {
						log.warn("id为" + id + "的交易已经被司机取消");
						sendResponseToJson("0", "该交易已经被司机取消");
						return ERROR;
					}
					if(origin == 2 ) {
						log.warn("id为" + id + "的交易已经被货主取消");
						sendResponseToJson("0", "该交易已经被货主取消");
						return ERROR;
					}
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("tradeStart", tradeStart);
			int i = transactionInfoService.updateTransactionInfoById(map);
			if(i == 0){
				sendResponseToJson("0", "修改失败");
				log.info("修改失败.");
			}else{
				sendResponseToJson("1", "修改成功");
				log.info("修改成功.");
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
		bo.setOperationName("unloadCargoAction");
		bo.setOperationType(35);
		bo.setRemark("卸货动作");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
