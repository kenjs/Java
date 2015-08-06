package com.cy.driver.cargo.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.cargo.domain.OrderCargoInfoDomain;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.operationLog.service.OperationLogService;

/**
 * 附近货源
 * @date 2014-5-30
 * @author haoyong
 *
 */
public class SelectNearByCargoListAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8642497782041279343L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private OrderCargoInfoService orderCargoInfoService;
	private OperationLogService operationLogService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			//司机id
			String driverId = request.getParameter("driverId");
						
			if(StringUtils.isBlank(driverId)) {				
				sendResponseToJson("-9","用户不存在");
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
						
			//分页相关
			String fromSize = request.getParameter("fromSize");
			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			List<OrderCargoInfoDomain> list = orderCargoInfoService.selectNearByCargoList(driverId,fromSize,listSize);
			if(list != null){
				if(list.size() == 0){
					sendResponseToJson("0","未找到符合条件的信息");
					logger.info("未找到符合条件的信息");
				}else {
					sendResponseToJson("1","查找成功. ",list);
					logger.info("查找成功.");
				}
			}else {
				sendResponseToJson("0","未找到符合条件的信息");
				logger.info("未找到符合条件的信息");
			}
		} catch (IOException e) {
			try {
				sendResponseToJson("-8",e.getMessage());
				logger.error("查找发生异常." + e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectNearByCargoListAction");
		bo.setOperationType(14);
		bo.setRemark("附近货源列表");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setOrderCargoInfoService(OrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
