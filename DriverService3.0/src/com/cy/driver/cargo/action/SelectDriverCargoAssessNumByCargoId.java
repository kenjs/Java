package com.cy.driver.cargo.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 某条货源被多少次标注为货已走
 * @author haoyong
 *
 */
public class SelectDriverCargoAssessNumByCargoId extends BaseJsonAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3209349634898219142L;
	private OrderCargoInfoService orderCargoInfoService;
	private OperationLogService operationLogService;
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String cargoId = request.getParameter("cargoId");
			if(StringUtils.isBlank(cargoId)) {
				log.info("货源ID为空");
				sendResponseToJson("-8", "货源ID为空");
				return ERROR;
			}
			int res = orderCargoInfoService.selectDriverCargoAssessNum(cargoId);
			sendResponseToJson("1", "该条货源已被" + res + "次点评为‘货已走’",res);
			log2Db("");
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
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectDriverCargoAssessNumByCargoId");
		bo.setOperationType(60);
		bo.setRemark("某条货源被多少次标注为货已走");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setOrderCargoInfoService(OrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
