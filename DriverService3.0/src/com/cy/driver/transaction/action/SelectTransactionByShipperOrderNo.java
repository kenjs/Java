package com.cy.driver.transaction.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
import com.cy.driver.transaction.service.TransactionInfoService;

/**
 * @author haoyong
 * @date 2014/8/8
 * @description 根据 ‘发货单号’查询订单信息
 */
public class SelectTransactionByShipperOrderNo extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7503164200301557949L;

	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionInfoService transactionInfoService; 
	private OperationLogService operationLogService;
	
	@Override
	protected void execMethod() throws Exception {
		try {
			//参数
			String driverId = request.getParameter("driverId");
			String shipperOrderNo = request.getParameter("shipperOrderNo");
			//分页相关
			String fromSize = request.getParameter("fromSize");
			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在");
				sendResponseToJson("-9","司机不存在");
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
			log2Db(driverId);//操作日志
			
			Map<String,String> map = new HashMap<String, String>();
			//map.put("driverId", driverId);
			map.put("shipperOrderNo", shipperOrderNo);
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			
			List<TransactionInfoDomain> list = transactionInfoService.selectTransactionByShipperOrderNo(map);
			if(list != null){
				if(list.size() == 0){
					log.info("未找到符合条件的信息");
					sendResponseToJson("0", "未找到符合条件的信息");
				}else{
					log.info("查找成功, 共找到"+ list.size() +"条数据");
					sendResponseToJson("1", "查找成功.",list);
				}
			}else {
				log.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
			}
		} catch (Exception e) {			
			log.error(e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("transactionInfoListAction");
		bo.setOperationType(65);
		bo.setRemark("根据 ‘发货单号’查询订单信息");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
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

}
