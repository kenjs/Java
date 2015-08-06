package com.cy.driver.record.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.bo.QuoteInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.record.domain.QuoteInfoDomain;
import com.cy.driver.record.service.QuoteInfoService;
/**
 * 新增货源报价
 * @date 2014-6-9
 * @author haoyong
 *
 */
public class AddQuoteInfoAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7729004734967297949L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private QuoteInfoService quoteInfoService;

	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {			
			QuoteInfoBo bo = new QuoteInfoBo();
			String cargoId = request.getParameter("cargoId"),
					driverId = request.getParameter("driverId");
			if(StringUtils.isNotBlank(cargoId)){
				bo.setCargoId(Integer.parseInt(cargoId));
			} else {
				log.info("货源不存在");
				sendResponseToJson("-8", "货源不存在");
				return ERROR;
			}
			if(StringUtils.isNotBlank(driverId)){
				bo.setDriverId(Integer.parseInt(driverId));
			}else {
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
			
			if(StringUtils.isNotBlank(request.getParameter("quoteFair"))){
				bo.setQuoteFair(Double.parseDouble(request.getParameter("quoteFair")));
			}else {
				log.info("请输入报价价格");
				sendResponseToJson("-8", "请输入报价价格");
				return ERROR;
			}
			if(StringUtils.isNotBlank(request.getParameter("quoteType"))){
				bo.setQuoteType(Integer.parseInt(request.getParameter("quoteType")));
			}else {
				log.info("请选择报价类型");
				sendResponseToJson("-8", "请选择报价类型");
				return ERROR;
			}		
			if(StringUtils.isNotBlank(request.getParameter("cashAdvance"))){
				bo.setCashAdvance(Double.parseDouble(request.getParameter("cashAdvance")));
			}
			if(StringUtils.isNotBlank(request.getParameter("prepaidOilCard"))){
				bo.setPrepaidOilCard(Double.parseDouble(request.getParameter("prepaidOilCard")));
			}
			bo.setRemark(request.getParameter("remark"));
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cargoId", cargoId);
			map.put("driverId", driverId);
			QuoteInfoDomain domain = quoteInfoService.selectQuoteInfoByDriverAndCargoId(map);
			if(domain == null) {
				int i = quoteInfoService.addNewQuoteInfo(bo);
				if(i == 0){
					log.info("新增报价失败");
					sendResponseToJson("-8", "报价失败");
				}else{
					log.info("新增报价成功");
					sendResponseToJson("1", "报价成功");
				}
			} else {
				bo.setId(domain.getId());
				int j = quoteInfoService.updateQuoteInfo(bo);
				if(j == 0){
					log.info("修改报价失败");
					sendResponseToJson("0", "报价失败");
				}else{
					log.info("修改报价成功");
					sendResponseToJson("1", "报价成功");
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
	
	public void setQuoteInfoService(QuoteInfoService quoteInfoService) {
		this.quoteInfoService = quoteInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("addQuoteInfoAction");
		bo.setOperationType(39);
		bo.setRemark("新增报价");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
