package com.cy.driver.assess.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.assess.domain.AssessDomain;
import com.cy.driver.assess.service.DriverUserAssessInfoService;
import com.cy.driver.operationLog.service.OperationLogService;

/**
 * 查询用户对司机的评价信息
 * @author haoyong
 *
 */
public class SelectUserDriverAssessNum extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8906688230567294799L;
	private Logger _log = LoggerFactory.getLogger(getClass());
	private DriverUserAssessInfoService driverUserAssessInfoService;

	@Override
	protected void execMethod() throws Exception {
		
	}

	@Override
	public String exec() {
		String driverId = request.getParameter("driverId");
		try {
			if(StringUtils.isBlank(driverId)){
				_log.info("司机不存在");
				sendResponseToJson("-9","司机不存在");
				return ERROR;
			}
			
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				_log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return ERROR;
			} else if(accFlag == 11) {
				_log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return ERROR;
			}
			log2Db(driverId);
			
			List<AssessDomain> list = driverUserAssessInfoService.selectUserDriverAssessNum(driverId);
			JSONObject json = new JSONObject();
			if(list != null) {
				int length = list.size();
				switch(length) {
					case 0:
						json.accumulate("highOpinion", "0");
						json.accumulate("average", "0");
						json.accumulate("poor", "0");						
						break;
					case 1:
						String level = list.get(0).getScore();
						if("3".equals(level)) {
							json.accumulate("highOpinion", list.get(0).getLevel());
							json.accumulate("average", "0");
							json.accumulate("poor", "0");									
						} else if("6".equals(level)) {
							json.accumulate("highOpinion", "0");
							json.accumulate("average", list.get(0).getLevel());
							json.accumulate("poor", "0");										
						} else if("9".equals(level)) {
							json.accumulate("highOpinion", "0");
							json.accumulate("average", "0");
							json.accumulate("poor", list.get(0).getLevel());									
						}
						break;
					case 2:
						String one = list.get(0).getScore(),two = list.get(1).getScore();
						if("6".equals(one) && "3".equals(two)) {
							json.accumulate("highOpinion", list.get(1).getLevel());
							json.accumulate("average", list.get(0).getLevel());
							json.accumulate("poor", "0");							
						} else if("9".equals(one) && "3".equals(two)) {
							json.accumulate("highOpinion", list.get(1).getLevel());
							json.accumulate("average", "0");
							json.accumulate("poor", list.get(0).getLevel());								
						} else if("9".equals(one) && "6".equals(two)) {
							json.accumulate("highOpinion", "0");
							json.accumulate("average", list.get(1).getLevel());
							json.accumulate("poor", list.get(0).getLevel());								
						}
						break;
					case 3: case 4:
						json.accumulate("highOpinion", list.get(2).getLevel());
						json.accumulate("average", list.get(1).getLevel());
						json.accumulate("poor", list.get(0).getLevel());							
						break;
				}
			}			
			sendResponseToJson("1", "查询用户对司机的评价信息成功",json.toString());
		} catch (Exception e) {
			_log.error(e.getMessage());
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
		bo.setOperationName("addNewDriverUserAssessAction");
		bo.setOperationType(63);
		bo.setRemark("查询用户对司机的评价信息");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	
	public void setDriverUserAssessInfoService(
			DriverUserAssessInfoService driverUserAssessInfoService) {
		this.driverUserAssessInfoService = driverUserAssessInfoService;
	}

}
