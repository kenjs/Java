package com.cy.driver.user.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.user.service.LoginUserInfoService;
/**
 * 百度云推送设置channelID和userID 
 * @author haoyong
 *
 */
public class SetBaiduPushIdAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3960823346536146552L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private LoginUserInfoService loginUserInfoService;
	private OperationLogService operationLogService;

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
			
			String baiduChannelId = request.getParameter("baiduChannelId");
			String baiduUserId = request.getParameter("baiduUserId");
			if(StringUtils.isBlank(baiduChannelId)) {
				sendResponseToJson("-8","channelId为空");
				return ERROR;
			}
			if(StringUtils.isBlank(baiduUserId)) {
				sendResponseToJson("-8","userId为空");
				return ERROR;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			if(StringUtils.isNotBlank(baiduChannelId)) {
				map.put("baiduChannelId", Long.parseLong(baiduChannelId));
			}			
			map.put("baiduUserId", baiduUserId);
			map.put("id", driverId);
			int i = loginUserInfoService.updateBaiduPushId(map);
			if(i != 0) {
				sendResponseToJson("1", "设置成功");				
			} else {
				sendResponseToJson("0", "设置失败");				
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
		bo.setOperationName("setBaiduPushId");
		bo.setOperationType(62);
		bo.setRemark("百度云推送设置channelID和userID");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setLoginUserInfoService(LoginUserInfoService loginUserInfoService) {
		this.loginUserInfoService = loginUserInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
