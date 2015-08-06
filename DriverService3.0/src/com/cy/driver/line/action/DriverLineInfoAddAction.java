package com.cy.driver.line.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverLineInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.line.domain.DriverLineInfoDomain;
import com.cy.driver.line.service.DriverLineInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 新增营运线路
 * @date 2014-6-3
 * @author haoyong
 *
 */
public class DriverLineInfoAddAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5886221998342682380L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverLineInfoService driverLineInfoService;
	
	protected void execMethod() throws Exception {
	
	}
	
	public String exec() {
		try {
			DriverLineInfoBo bo = new DriverLineInfoBo();
			String driverId = request.getParameter("driverId");
			if(StringUtils.isNotBlank(driverId)) {				
				bo.setDriverId(request.getParameter("driverId"));
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
			} else {
				this.sendResponseToJson("-9", "用户不存在.");
				logger.info("用户不存在");
				return ERROR;
			}
			
			String startProvince = request.getParameter("startProvince");
			if(StringUtils.isNotBlank(startProvince)) {
				bo.setStartProvince(startProvince);
			} else {
				this.sendResponseToJson("-8", "请选择起始地-省.");
				logger.info("请选择起始地-省");
				return ERROR;
			}
			
			String startCity = request.getParameter("startCity");
			if(StringUtils.isNotBlank(startCity)) {
				bo.setStartCity(startCity);
			} else {
				this.sendResponseToJson("-8", "请选择起始地-市.");
				logger.info("请选择起始地-市");
				return ERROR;
			}
			
			String endProvince = request.getParameter("endProvince");
			if(StringUtils.isNotBlank(endProvince)) {
				bo.setEndProvince(endProvince);
			} else {
				this.sendResponseToJson("-8", "请选择起始地-省.");
				logger.info("请选择起始地-省");
				return ERROR;
			}
			
			String endCity = request.getParameter("endCity");
			if(StringUtils.isNotBlank(endCity)) {
				bo.setEndCity(endCity);
			} else {
				this.sendResponseToJson("-8", "请选择起始地-市.");
				logger.info("请选择起始地-市");
				return ERROR;
			}
			
			if(StringUtils.isNotBlank(request.getParameter("lineType"))) {
				bo.setLineType(request.getParameter("lineType"));
			} else {
				this.sendResponseToJson("-8", "请选择线路类型.");
				logger.info("请选择线路类型");
				return ERROR;
			}
			
			@SuppressWarnings("unchecked")
			List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
			for (DriverLineInfoDomain e : list) {
				if(startProvince.equals(e.getStartProvince()) && startCity.equals(e.getStartCity())) {
					if(endProvince.equals(e.getEndProvince()) && endCity.equals(e.getEndCity())) {
						sendResponseToJson("-8","新增的线路与其他线路重复,请修改.");						
						return ERROR;
					}
				}
			}
			
			int count = driverLineInfoService.selectDriverLineInfoCount(bo.getDriverId());
			if(count > 3) {
				sendResponseToJson("0","最多只能添加3条.");
				logger.info("最多只能添加3条");
				return ERROR;
			}
			int i = driverLineInfoService.insertDriverLineInfo(bo);
			if(i == 0){
				sendResponseToJson("-8", "新增运营线路失败.");
				logger.info("新增运营线路失败.");
				return ERROR;
			}
			sendResponseToJson("1", "新增运营线路成功.");
			logger.info("新增运营线路成功.");
		} catch (IOException e) {
			logger.error("新增运营线路出错." + e.getMessage());
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
		bo.setOperationName("driverLineInfoAddAction");
		bo.setOperationType(22);
		bo.setRemark("新增营运线路");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	
	public void setDriverLineInfoService(DriverLineInfoService driverLineInfoService) {
		this.driverLineInfoService = driverLineInfoService;
	}

}
