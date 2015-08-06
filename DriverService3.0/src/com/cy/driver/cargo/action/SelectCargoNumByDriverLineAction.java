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
 * 根据司机线路查找符合条件的货物数量
 * @date 2014-6-5
 * @author haoyong
 *
 */
public class SelectCargoNumByDriverLineAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1226322419631042318L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OrderCargoInfoService orderCargoInfoService;

	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			
			if(StringUtils.isBlank(driverId)){
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
			
			int count = orderCargoInfoService.selectCargoNumByDriverLine(driverId);
			if(count == 0){
				log.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
			} else {
				log.info("查找成功");
				sendResponseToJson("1", "查找成功",count);
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

	public void setOrderCargoInfoService(OrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectCargoNumByDriverLineAction");
		bo.setOperationType(27);
		bo.setRemark("根据司机线路查找符合条件的货物数量");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}