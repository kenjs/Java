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
 * 修改货源评价
 * @date 2014-6-9
 * @author haoyong
 *
 */
public class UpdateDriverUserAssessAction extends BaseJsonAction{

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
			
			DriverUserAssessInfoBo bo = getDriverUserAssessInfoBo();
			if(bo.getId() == 0){
				log.info("该条评价不存在");
				sendResponseToJson("-8", "该条评价不存在");
				return ERROR;
			}
			int i = driverUserAssessInfoService.updateDriverUserAssessInfo(bo);
			if(i == 0){
				log.info("修改货源评价失败");
				sendResponseToJson("0", "修改货源评价失败");
			}else{
				log.info("修改货源评价成功");
				sendResponseToJson("1", "修改货源评价成功");
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
	
	private DriverUserAssessInfoBo getDriverUserAssessInfoBo() {
		DriverUserAssessInfoBo bo = new DriverUserAssessInfoBo();
		
		bo.setAssess(request.getParameter("assess"));
		if(StringUtils.isNotBlank(request.getParameter("assessEvaluateScore")))
		{
			bo.setAssessEvaluateScore(Integer.parseInt(request.getParameter("assessEvaluateScore")));
		}
		if(StringUtils.isNotBlank(request.getParameter("id")))
		{
			bo.setId(Integer.parseInt(request.getParameter("id")));
		}
		
		return bo; 
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("updateDriverUserAssessAction");
		bo.setOperationType(43);
		bo.setRemark("修改货源评价");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
