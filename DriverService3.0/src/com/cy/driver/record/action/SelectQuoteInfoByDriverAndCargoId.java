package com.cy.driver.record.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.record.domain.QuoteInfoDomain;
import com.cy.driver.record.service.QuoteInfoService;
/**
 * 根据货源id和司机id查找报价信息 
 * @author haoyong
 *
 */
public class SelectQuoteInfoByDriverAndCargoId extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048666413541636826L;
	private Logger _log = LoggerFactory.getLogger(getClass());
	private QuoteInfoService quoteInfoService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String driverId = request.getParameter("driverId"),
					cargoId = request.getParameter("cargoId");
			log2Db(driverId);
			if(StringUtils.isBlank(cargoId)) {
				_log.info("货源不存在");
				sendResponseToJson("-8", "货源不存在");
				return ERROR;
			}
			if(StringUtils.isBlank(driverId)) {
				_log.info("司机不存在");
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
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cargoId", cargoId);
			map.put("driverId", driverId);
			QuoteInfoDomain domain = quoteInfoService.selectQuoteInfoByDriverAndCargoId(map);
			if(domain != null) {
				_log.info("根据货源id和司机id查找报价信息 成功");
				sendResponseToJson("1", "报价信息查找成功",domain);
			} else {
				_log.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
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

	public void setQuoteInfoService(QuoteInfoService quoteInfoService) {
		this.quoteInfoService = quoteInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectQuoteInfoByDriverAndCargoId");
		bo.setOperationType(52);
		bo.setRemark("根据货源id和司机id查找报价信息");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
