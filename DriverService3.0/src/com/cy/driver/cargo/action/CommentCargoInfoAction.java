package com.cy.driver.cargo.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.DriverCargoAssessInfoBo;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.operationLog.service.OperationLogService;

/**
 * 点评货源
 * @date 2014-6-11
 * @author haoyong
 *
 */
public class CommentCargoInfoAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8081096242938035653L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OrderCargoInfoService orderCargoInfoService;

	protected void execMethod() throws Exception {
	
	}

	public String exec() {
		DriverCargoAssessInfoBo bo = new DriverCargoAssessInfoBo();
		String driverId = request.getParameter("driverId");
		String cargoId = request.getParameter("cargoId");
		String type = request.getParameter("type");
		String assessInfo = request.getParameter("assessInfo");		
		
		try {
			if(StringUtils.isBlank(driverId)) {
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
			
			if(StringUtils.isBlank(cargoId)) {
				log.info("货源不存在. ");
				sendResponseToJson("-8", "货源不存在");
				return ERROR;
			} else {
				bo.setCargoId(Integer.parseInt(cargoId));
			}
			if(StringUtils.isBlank(type)) {
				log.info("评价类型不存在. ");
				sendResponseToJson("-8", "评价类型不存在");
				return ERROR;
			} else {
				bo.setType(Integer.parseInt(type));
				if("0".equals(type) && StringUtils.isBlank(assessInfo)) {
					log.info("请说明原因. ");
					sendResponseToJson("-8", "请说明原因");
					return ERROR;
				}
			}
			bo.setAssessInfo(assessInfo);
			
			int checkI = orderCargoInfoService.selectByDriverAndCargoId(driverId, cargoId);
			if(checkI == 0) {			
				int i = orderCargoInfoService.commentCargoInfo(bo);
				if(i != 0){
					log.info("点评货源成功. ");
					sendResponseToJson("1", "点评货源成功");
				}else{
					log.info("点评货源失败. ");
					sendResponseToJson("-8", "点评货源失败");
				}
			} else {
				int updateI = orderCargoInfoService.updateAssess(bo);
				if(updateI != 0){
					log.info("修改点评货源成功. ");
					sendResponseToJson("1", "修改点评货源成功");
				}else{
					log.info("修改点评货源失败. ");
					sendResponseToJson("0", "修改点评货源失败");
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

	public void setOrderCargoInfoService(OrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}

	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("commentCargoInfoAction");
		bo.setOperationType(31);
		bo.setRemark("点评货源");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
}
