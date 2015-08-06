package com.cy.driver.user.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.util.DateUtil;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.DriverUserCargoInfoService;
/**
 * 货源消息提醒
 * @author haoyong
 *
 */
public class CargoInfoRemindAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3007878175041650173L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OperationLogService operationLogService;
	private DriverUserCargoInfoService driverUserCargoInfoService;
	
	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)) {
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
			
			Map<String,String> map = new HashMap<String,String>();
			String nearByModifyTime = request.getParameter("nearByModifyTime");
			String businesslineModifyTime = request.getParameter("businesslineModifyTime");
			String driverLineModifyTime = request.getParameter("driverLineModifyTime");
			try {
				if(StringUtils.isBlank(nearByModifyTime)) {
					nearByModifyTime = DateUtil.getNowStr();
				}
				if(StringUtils.isBlank(businesslineModifyTime)) {
					businesslineModifyTime = DateUtil.getNowStr();
				}
				if(StringUtils.isBlank(driverLineModifyTime)) {
					driverLineModifyTime = DateUtil.getNowStr();
				}
			} catch (ParseException e) {				
				e.printStackTrace();
				log.error(e.getMessage());
				try {
					sendResponseToJson("-8", e.getMessage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			map.put("driverId", driverId);
			map.put("nearByModifyTime", nearByModifyTime);
			map.put("businesslineModifyTime", businesslineModifyTime);
			map.put("driverLineModifyTime", driverLineModifyTime);
			
			String jsonStr = driverUserCargoInfoService.cargoInfoRemind(map);
			sendResponseToJson("1", "查询成功", jsonStr);
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
		bo.setOperationName("cargoInfoRemind");
		bo.setOperationType(59);
		bo.setRemark("货源消息提醒");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}
}
