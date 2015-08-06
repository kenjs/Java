package com.cy.driver.line.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.line.domain.DriverLineInfoDomain;
import com.cy.driver.line.service.DriverLineInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 查找营运线路
 * @date 2014-6-3
 * @author haoyong
 *
 */
public class DriverLineInfoSelectAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4950422236502077396L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverLineInfoService driverLineInfoService;
	
	protected void execMethod() throws Exception {
		
	}

	@SuppressWarnings("unchecked")
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)){
				sendResponseToJson("-9","用户不存在.");
				logger.info("用户不存在.");
				return ERROR;
			}
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				logger.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				logger.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(driverId);
			
			List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
			if(list != null){
				if(list.size() == 0){
					logger.info("未找到符合条件的信息");
					sendResponseToJson("0", "未找到符合条件的信息");
				}else {
					logger.info("查找成功, 一共找到"+ list.size() +"数据");
					sendResponseToJson("1", "查找成功.",list);
				}
			}else {
				logger.info("未找到符合条件的信息");
				sendResponseToJson("0", "未找到符合条件的信息");
			}
		} catch (IOException e) {
			logger.error("查找运营线路出错." + e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setDriverLineInfoService(DriverLineInfoService driverLineInfoService) {
		this.driverLineInfoService = driverLineInfoService;
	}
	
	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("driverLineInfoSelectAction");
		bo.setOperationType(25);
		bo.setRemark("查询营运线路");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
