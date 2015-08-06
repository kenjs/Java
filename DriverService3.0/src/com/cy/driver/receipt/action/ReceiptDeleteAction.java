package com.cy.driver.receipt.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.util.FileUtil;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.receipt.service.TransactionReceiptPathService;
/**
 * 删除
 * @author haoyong
 *
 */
public class ReceiptDeleteAction extends BaseJsonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private TransactionReceiptPathService transactionReceiptPathService;
	private OperationLogService operationLogService;

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	protected void execMethod() throws Exception {
		try {
			String driverId = request.getParameter("driverId");
			//String tradeStart = request.getParameter("tradeStart");
			String idJson = request.getParameter("idJson");
						
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
			
			JSONObject jsonObject = JSONObject.fromObject(idJson);
			Map<String,Object> map = jsonObject.fromObject(jsonObject);
			JSONArray jsonArray = JSONArray.fromObject(map.get("id"));
			List<String> list = (List<String>) jsonArray.toCollection(jsonArray, String.class);
			List<String> path = transactionReceiptPathService.selectReceiptPathById(list);
			int res = transactionReceiptPathService.deleteTransactionReceiptPath(list);
			if(res != 0) {
				sendResponseToJson("1", "删除成功！");
				for (String imgPath : path) {
					FileUtil.removeImg(imgPath);
				}				
			} else {
				sendResponseToJson("0", "删除失败！");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			sendResponseToJson("-8", e.getMessage());
			e.printStackTrace();
		}
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("receiptDelete");
		bo.setOperationType(68);
		bo.setRemark("回单删除");
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
