package com.cy.driver.assess.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.assess.domain.UserDriverAssessInfoDomain;
import com.cy.driver.assess.service.DriverUserAssessInfoService;
import com.cy.driver.operationLog.service.OperationLogService;

public class SelectUserDriverAssessInfoList extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -616040911210021325L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserAssessInfoService driverUserAssessInfoService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
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
			//分页相关
			String fromSize = request.getParameter("fromSize");
			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("driverId", driverId);
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			
			List<UserDriverAssessInfoDomain> list = driverUserAssessInfoService.selectUserDriverAssessInfoList(map);
			if(list != null) {
				if(list.size() == 0){
					log.info("未找到符合条件的信息");
					sendResponseToJson("0","未找到符合条件的信息");
				}else{
					log.info("查找成功, 共找到"+ list.size() +"条数据");
					sendResponseToJson("1","查找成功",list);
				}
			} else {
				log.info("未找到符合条件的信息");
				sendResponseToJson("0","未找到符合条件的信息");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				sendResponseToJson("-8", e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return null;
	}

	public void setDriverUserAssessInfoService(
			DriverUserAssessInfoService driverUserAssessInfoService) {
		this.driverUserAssessInfoService = driverUserAssessInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectUserDriverAssessInfoList");
		bo.setOperationType(64);
		bo.setRemark("查询货主对司机的评价信息");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
