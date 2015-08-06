package com.cy.driver.record.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.record.service.QuoteInfoService;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
/*
 * 我的记录（已经成交的导入的订单）
 */
public class SelectImportDealedTransactionList extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2082451339141096403L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private QuoteInfoService quoteInfoService;

	@Override
	protected void execMethod() throws Exception {
		try {
			String driverId= request.getParameter("driverId");			
			//分页相关
			String fromSize = request.getParameter("fromSize");
			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在. ");
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
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("driverId", driverId);			
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			@SuppressWarnings("unchecked")
			List<TransactionInfoDomain> list = (List<TransactionInfoDomain>) quoteInfoService.
																selectImportDealedTransactionList(map);
			if(list != null){
				if(list.size() == 0){
					log.info("未找到符合条件的信息");
					sendResponseToJson("0", "未找到符合条件的信息");
				} else {
					log.info("查找成功, 共找到"+ list.size() +"条数据. ");
					sendResponseToJson("1", "查找成功",list);
				}
			} else {
				log.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
			}
			log2Db(driverId);
		} catch (Exception e) {
			log.error(e.getMessage());
			sendResponseToJson("-8", e.getMessage());
			e.printStackTrace();
		}
	}

	public void setQuoteInfoService(QuoteInfoService quoteInfoService) {
		this.quoteInfoService = quoteInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectImportDealedTransactionList");
		bo.setOperationType(71);
		bo.setRemark("查找我的记录已经成交的导入的订单");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
