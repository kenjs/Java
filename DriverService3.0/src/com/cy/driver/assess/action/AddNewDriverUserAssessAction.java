package com.cy.driver.assess.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverUserAssessInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.assess.service.DriverUserAssessInfoService;
import com.cy.driver.operationLog.service.OperationLogService;

/**
 * 新增货源评价
 * @date 2014-6-9
 * @author haoyong
 *
 */
public class AddNewDriverUserAssessAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5288383312711696270L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserAssessInfoService driverUserAssessInfoService;
	
	public void setDriverUserAssessInfoService(
			DriverUserAssessInfoService driverUserAssessInfoService) {
		this.driverUserAssessInfoService = driverUserAssessInfoService;
	}

	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			
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
			
			DriverUserAssessInfoBo bo = new DriverUserAssessInfoBo();
			
			if(StringUtils.isBlank(request.getParameter("assessEvaluateScore"))){
				sendResponseToJson("-8", "请选择评分");
				return ERROR;
			} else {
				bo.setAssessEvaluateScore(Integer.parseInt(request.getParameter("assessEvaluateScore")));
				if((Integer.parseInt(request.getParameter("assessEvaluateScore")) == 9) 
													&& StringUtils.isBlank(request.getParameter("assess"))) {
					sendResponseToJson("-8", "请说明差评原因");
					return ERROR;
				}
			}
			if(StringUtils.isBlank(request.getParameter("cargoId"))){
				sendResponseToJson("-8", "货物不存在");
				return ERROR;
			} else {
				bo.setCargoId(Integer.parseInt(request.getParameter("cargoId")));
			}
			
			if(StringUtils.isBlank(request.getParameter("userId"))){
				sendResponseToJson("-8", "用户不存在");
				return ERROR;
			} else {
				bo.setUserId(Integer.parseInt(request.getParameter("userId")));
			}
			if(StringUtils.isBlank(request.getParameter("transactionId"))){
				sendResponseToJson("-8", "交易订单不存在");
				return ERROR;
			} else {
				bo.setTransactionId(Integer.parseInt(request.getParameter("transactionId")));
			}
			bo.setDriverId(Integer.parseInt(driverId));
			bo.setAssess(request.getParameter("assess"));
			int key = driverUserAssessInfoService.selectAssessNum(bo);
			if(key == 0) {
				int i = driverUserAssessInfoService.addNewDriverUserAssessInfo(bo);
				if(i == 0){
					log.info("评价货源失败");
					sendResponseToJson("-8", "评价货源失败");
				}else{
					log.info("评价货源成功");
					sendResponseToJson("1", "评价货源成功");
				}
			} else {
				bo.setId(key);
				int j = driverUserAssessInfoService.updateDriverUserAssessInfo(bo);
				if(j == 0){
					log.info("修改货源评价失败");
					sendResponseToJson("0", "修改货源评价失败");
				}else{
					log.info("修改货源评价成功");
					sendResponseToJson("1", "修改货源评价成功");
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

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("addNewDriverUserAssessAction");
		bo.setOperationType(42);
		bo.setRemark("新增货源评价");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
