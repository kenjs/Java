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
import com.cy.driver.cargo.dao.OrderCargoInfoDao;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.transaction.service.TransactionInfoService;

public class CancelConfirmUseCarAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7512367754091273769L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionInfoService transactionInfoService;
	private OperationLogService operationLogService;
	private OrderCargoInfoDao orderCargoInfoDao;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
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
			TransactionLastInfoBo bo = new TransactionLastInfoBo();
			bo.setId(Integer.valueOf(id));			
			
			//如果交易已经完成或取消或异常取消或web端取消
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
				transactionInfoService.addTransactionLastInfo(bo);
				sendResponseToJson("1", "修改成功");
				log.info("修改成功.");
				log2Db(driverId,tradeStart);//操作日记记录
				if("6".equals(tradeStart)) {
					map.put("cargoFlag", "0");
					orderCargoInfoDao.updateCargoInfo(map);
				}
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

	private void log2Db(String id,String flag) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("cancelConfirmUseCarAction");
		if("3".equals(flag)) {
			bo.setOperationType(47);
			bo.setRemark("司机确认用车");
		} else if("6".equals(flag)) {
			bo.setOperationType(36);			
			bo.setRemark("司机确认取消用车");
		}
		if(StringUtils.isNotBlank(id)) {
			bo.setUserDriverId(Integer.parseInt(id));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setTransactionInfoService(
			TransactionInfoService transactionInfoService) {
		this.transactionInfoService = transactionInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public void setOrderCargoInfoDao(OrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}

}
