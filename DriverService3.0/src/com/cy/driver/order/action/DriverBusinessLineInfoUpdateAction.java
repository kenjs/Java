package com.cy.driver.order.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverBusinessLineInfo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.util.DateUtil;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.order.service.DriverBusinessLineInfoService;
/**
 * 修改预约
 * 2014-5-28
 * @author haoyong 
 *
 */
public class DriverBusinessLineInfoUpdateAction extends BaseJsonAction{

	private static final long serialVersionUID = -7207384315358432379L;
	private Logger logger = LoggerFactory. getLogger(getClass());
	private DriverBusinessLineInfoService driverBusinessLineInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}
	
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			String startProvince = request.getParameter("startProvince");
			String startCity = request.getParameter("startCity");
			String endProvince = request.getParameter("endProvince");
			String endCity = request.getParameter("endCity");
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
			
			DriverBusinessLineInfo domain = new DriverBusinessLineInfo();
			String id = request.getParameter("id");
			if(StringUtils.isBlank(id)){
				sendResponseToJson("-8","预约不存在.");
				return ERROR;
			} else {
				domain.setId(Integer.parseInt(id));
			}
			
			if(StringUtils.isBlank(request.getParameter("startTime")) || StringUtils.isBlank(request.getParameter("endTime"))) {
				this.sendResponseToJson("-8", "请选择起始时间.");
				logger.info("请选择起始时间");
				return ERROR;
			} else {
				try {
					Date currenDate = DateUtil.getNow();
					Date startDate = DateUtil.formatDate(request.getParameter("startTime"));
					if(DateUtil.isEarly(startDate,currenDate)) {
						this.sendResponseToJson("-8", "起始时间不能晚于当前时间.");	
						logger.info("起始时间不能晚于当前时间.");
						return ERROR;
					}
					Date endDate = DateUtil.formatDate(request.getParameter("endTime"));					
					if(DateUtil.isEarly(endDate,startDate)) {
						this.sendResponseToJson("-8", "结束时间不能晚于起始时间.");		
						logger.info("结束时间不能晚于起始时间.");
						return ERROR;
					}
					Date furtureDate = DateUtil.getFurturDate(31);
					if(DateUtil.isEarly(furtureDate, endDate)) {
						this.sendResponseToJson("-8", "结束时间必须在一个月之内.");	
						logger.info("结束时间必须在一个月之内.");
						return ERROR;
					}
					domain.setStartTime(startDate);
					domain.setEndTime(endDate);
				} catch (ParseException e) {
					e.printStackTrace();		
					logger.error(e.getMessage());
					sendResponseToJson("-8", "日期转换出现异常");
					return ERROR;
				}
			}
			if(StringUtils.isBlank(startProvince)) {
				this.sendResponseToJson("-8", "请选择起始地-省份.");
				logger.info("请选择起始地-省份");
				return ERROR;
			} else {
				domain.setStartProvince(startProvince);
			}
			if(StringUtils.isBlank(startCity)) {
				this.sendResponseToJson("-8", "请选择起始地-城市.");
				logger.info("请选择起始地-城市");
				return ERROR;			
			} else {
				domain.setStartCity(startCity);
			}
			if(StringUtils.isBlank(endProvince)) {
				this.sendResponseToJson("-8", "请选择目的地-省份.");
				logger.info("请选择目的地-省份");
				return ERROR;
			} else {
				domain.setEndProvince(endProvince);
			}
			if(StringUtils.isBlank(endCity)) {
				this.sendResponseToJson("-8", "请选择目的地-城市.");
				logger.info("请选择目的地-城市");
				return ERROR;
			} else {
				domain.setEndCity(endCity);
			}
			if(StringUtils.isBlank(request.getParameter("quoteFair"))) {
//				this.sendResponseToJson("-8", "请输入运费价格.");
//				logger.info("请输入运费价格");
//				return ERROR;
			} else {
				domain.setQuoteFair(request.getParameter("quoteFair"));
			}
			if(StringUtils.isBlank(request.getParameter("quoteType"))) {
//				this.sendResponseToJson("-8", "请选择报价类型.");
//				logger.info("请选择报价类型");
//				return ERROR;
			} else {
				domain.setQuoteType(request.getParameter("quoteType"));
			}
			
			//判断添加的预约路线是否重复
			List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoService.selectDriverBusinessLineInfoList(driverId);
			for (DriverBusinessLineInfoDomain e : list) {
				if(! id.equals(e.getId())) {
					if(startProvince.equals(e.getStartProvince()) && startCity.equals(e.getStartCity())) {
						if(endProvince.equals(e.getEndProvince()) && endCity.equals(e.getEndCity())) {
							this.sendResponseToJson("0", "修改的预约线路与其他的重复, 请修改. ");
							logger.info("修改的预约线路与其他的重复, 请修改. ");
							return ERROR;
						}
					}
				}
			}
			
			int i = driverBusinessLineInfoService.updateDriverBusinessLineInfo(domain);
			if(i == 1){
				logger.info("修改预约成功.");
				this.sendResponseToJson("1","修改预约成功");
			}else {
				logger.info("修改预约失败.");
				this.sendResponseToJson("0","修改预约失败");
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
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
		bo.setOperationName("driverBusinessLineInfoUpdateAction");
		bo.setOperationType(8);
		bo.setRemark("修改预约");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setDriverBusinessLineInfoService(
			DriverBusinessLineInfoService driverBusinessLineInfoService) {
		this.driverBusinessLineInfoService = driverBusinessLineInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
