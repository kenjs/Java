package com.cy.driver.record.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.record.domain.QuoteInfoDomain;
import com.cy.driver.record.service.QuoteInfoService;

/**
 * 所有报过价的订单列表action
 * @date 2014-6-6
 * @author haoyong
 */
public class SelectQuoteTransactionListAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7555652969058423471L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private QuoteInfoService quoteInfoService;

	protected void execMethod() throws Exception {
		
	}

	@SuppressWarnings("unchecked")
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");			
			//分页相关
			String fromSize = request.getParameter("fromSize");
			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
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
			
			List<QuoteInfoDomain> list = (List<QuoteInfoDomain>) quoteInfoService.selectQuoteTransactionList(driverId,fromSize,listSize);
			if(list != null){
				if(list.size() == 0){
					log.info("未找到符合条件的信息");
					sendResponseToJson("0", "未找到符合条件的信息");
				}else{
					log.info("查找成功.共找到"+ list.size() +"条数据. ");
					sendResponseToJson("1", "查找成功.",list);
				}
			}else{
				log.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
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

	public void setQuoteInfoService(QuoteInfoService quoteInfoService) {
		this.quoteInfoService = quoteInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectQuoteTransactionListAction");
		bo.setOperationType(37);
		bo.setRemark("所有报过价的订单列表");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
