package com.cy.driver.user.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.domain.DriverNotificationInfoDomain;
import com.cy.driver.user.service.DriverUserCargoInfoService;
/**
 * 消息列表查询
 * @date 2014-6-11
 * @author Administrator
 *
 */
public class QueryDriverNotificationInfoListAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4744564711556886295L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoService driverUserCargoInfoService;
	private OperationLogService operationLogService;

	protected void execMethod() throws Exception {
		
	}

	@SuppressWarnings("unchecked")
	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
						
			if(StringUtils.isBlank(driverId)){
				sendResponseToJson("-9", "用户没有登录");
				log.info("用户没有登录");
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
			
			List<DriverNotificationInfoDomain> list = (List<DriverNotificationInfoDomain>) driverUserCargoInfoService.
															queryDriverNotificationInfo(driverId,fromSize,listSize);
			if(list != null){
				if(list.size() == 0){
					sendResponseToJson("0", "未找到符合条件的信息");
					log.info("未找到符合条件的信息");
				}else{
					sendResponseToJson("1", "查询成功",list);
					log.info("查询成功, 找到" + list.size() + "条数据");
				}
			}else{
				sendResponseToJson("0", "未找到符合条件的信息");
				log.info("未找到符合条件的信息");
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

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("queryDriverNotificationInfoListAction");
		bo.setOperationType(19);
		bo.setRemark("消息列表查询");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setDriverUserCargoInfoService(
			DriverUserCargoInfoService driverUserCargoInfoService) {
		this.driverUserCargoInfoService = driverUserCargoInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
