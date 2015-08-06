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
 * 修改营运线路
 * @date 2014-6-3
 * @author haoyong
 *
 */
public class DriverLineInfoUpdateAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7461532310645434865L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverLineInfoService driverLineInfoService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		String driverId = request.getParameter("driverId");
		try {
			if(StringUtils.isBlank(driverId)){
				logger.info("司机不存在. ");
				sendResponseToJson("-9", "司机不存在");
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
			
			DriverLineInfoBo bo = new DriverLineInfoBo();
			String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
				bo.setId(Integer.parseInt(id));
			} else {
				sendResponseToJson("-8","没有找到要修改的运营路线.");
				logger.info("没有找到要修改的运营路线");
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
			
//			if(StringUtils.isNotBlank(request.getParameter("lineType"))) {
//				bo.setLineType(request.getParameter("lineType"));
//			} else {
//				this.sendResponseToJson("-8", "请选择线路类型.");
//				logger.info("请选择线路类型");
//				return ERROR;
//			}
			
			@SuppressWarnings("unchecked")
			List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
			for (DriverLineInfoDomain e : list) {
				if(! id.equals(e.getId())) {
					if(startProvince.equals(e.getStartProvince()) && startCity.equals(e.getStartCity())) {
						if(endProvince.equals(e.getEndProvince()) && endCity.equals(e.getEndCity())) {
							sendResponseToJson("-8","修改后的线路与其他线路重复,请修改.");						
							return ERROR;
						}
					}
				}
			}
			
			int i = driverLineInfoService.updateDriverLineInfo(bo);
			if(i != 1){
				sendResponseToJson("0", "修改运营线路失败.");
				logger.info("修改运营线路失败.");
				return ERROR;
			}
			sendResponseToJson("1", "修改运营线路成功.");
			logger.info("修改运营线路成功.");
		} catch (IOException e) {
			logger.error("修改运营线路出错." + e.getMessage());
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
		bo.setOperationName("driverLineInfoUpdateAction");
		bo.setOperationType(24);
		bo.setRemark("修改营运线路");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
