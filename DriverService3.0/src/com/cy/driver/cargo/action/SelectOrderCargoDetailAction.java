package com.cy.driver.cargo.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.cargo.domain.OrderCargoInfoDomain;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 *  货源详情查询
 * @date 2014-6-6
 * @author haoyong
 *
 */
public class SelectOrderCargoDetailAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 411149435896032730L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OrderCargoInfoService orderCargoInfoService;
	
	public void setOrderCargoInfoService(OrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}

	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");	
			
			if(StringUtils.isBlank(driverId)) {				
				sendResponseToJson("-9","用户不存在");
				log.info("用户不存在.");
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
			if(StringUtils.isBlank(id)){
				log.info("货源不存在. ");
				sendResponseToJson("-8", "货源不存在");
				return ERROR;
			}
			OrderCargoInfoDomain domain = orderCargoInfoService.selectCargoDetailById(id);
			if(domain != null){
				log.info("查询成功. ");
				sendResponseToJson("1", "查询成功",domain);
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

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectOrderCargoDetailAction");
		bo.setOperationType(29);
		bo.setRemark("货源详情");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
