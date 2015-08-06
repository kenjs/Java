package com.cy.driver.pactDriver.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.common.bo.PactDriverInfo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.pactDriver.service.PactDriverInfoService;
/**
 * @description 合同车源状态修改
 * @author 		haoy
 * @since		2014-8-27
 *
 */
public class UpdatePactDriverInfo extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3981487103492116670L;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private OperationLogService operationLogService;
	private PactDriverInfoService pactDriverInfoService;

	@Override
	protected void execMethod() throws Exception {
		
		try {
			PactDriverInfo info = new PactDriverInfo();
			
			//获取参数
			String id = request.getParameter("id");
			String pactStart = request.getParameter("pactStart");
			
			String driverId = request.getParameter("driverId");
			
			if(StringUtils.isBlank(driverId)) {
				log.info("用户ID为空");
				sendResponseToJson("-9", "用户ID为空");
				return;
			}
			
			//判断用户状态
			int flag = operationLogService.checkUser(driverId);
			if(flag == 1) {
				sendResponseToJson("-9","该用户不存在或已被删除");
				return;
			} else if(flag == 11) {
				sendResponseToJson("-9","该用户已被冻结");
				return;
			}
			
			if(StringUtils.isBlank(id)) {
				sendResponseToJson("-8","必须参数合同车源ID缺失");
				return;
			}
			
			if(StringUtils.isBlank(pactStart)) {
				sendResponseToJson("-8","必须参数合同车源状态缺失");
				return;
			}
			
			info.setId(Integer.parseInt(id));
			info.setPactStart(pactStart);
			
			int rst = pactDriverInfoService.updatePactDriverInfo(info);
			if(rst == 1) {
				sendResponseToJson("1","操作成功");
			} else {
				sendResponseToJson("1","操作失败");
			}
			
			//记录操作日志
			log2Db(driverId);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("updatePactDriverInfo");
		bo.setOperationType(75);
		bo.setRemark("修改合同状态");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public void setPactDriverInfoService(PactDriverInfoService pactDriverInfoService) {
		this.pactDriverInfoService = pactDriverInfoService;
	}

}
