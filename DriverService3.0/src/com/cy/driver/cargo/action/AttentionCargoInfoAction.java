package com.cy.driver.cargo.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverCargoCollectInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.operationLog.service.OperationLogService;

/**
 * 关注货源
 * @date 2014-6-11
 * @author haoyong
 *
 */
public class AttentionCargoInfoAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8081096242938035653L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OrderCargoInfoService orderCargoInfoService;

	protected void execMethod() throws Exception {
	
	}

	public String exec() {
		DriverCargoCollectInfoBo bo = new DriverCargoCollectInfoBo();
		String driverId = request.getParameter("driverId");
		String cargoId = request.getParameter("cargoId");				
		try {
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在. ");
				sendResponseToJson("-9", "司机不存在");
				return ERROR;
			} else {
				bo.setDriverId(Integer.parseInt(driverId));
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
			
			if(StringUtils.isBlank(cargoId)){
				log.info("货源不存在. ");
				sendResponseToJson("-8", "货源不存在");
				return ERROR;
			} else {
				bo.setCargoId(Integer.parseInt(cargoId));
			}
			//判断给条货物是否已关注
			boolean isAtten = orderCargoInfoService.cargoIsAttention(driverId, cargoId);
			if(isAtten) {
				log.info("ID为 " + cargoId + "的货物已经关注");
				sendResponseToJson("0", "您已经关注此条货源");
				return ERROR;
			}
			int i = orderCargoInfoService.attentionCargoInfo(bo);
			if(i != 0){
				log.info("关注货源成功. ");
				sendResponseToJson("1", "关注货源成功");
			}else{
				log.info("关注货源失败. ");
				sendResponseToJson("-8", "关注货源失败");
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
		bo.setOperationName("attentionCargoInfoAction");
		bo.setOperationType(30);
		bo.setRemark("关注货源");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
