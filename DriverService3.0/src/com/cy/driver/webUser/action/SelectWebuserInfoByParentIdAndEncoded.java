package com.cy.driver.webUser.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.webUser.domain.WebUserInfoDomain;
import com.cy.driver.webUser.service.WebUserInfoService;
/**
 * 根据parentId和encoded查询
 * @author haoyong
 *
 */
public class SelectWebuserInfoByParentIdAndEncoded extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1983782372061628328L;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private WebUserInfoService webUserInfoService;
	private OperationLogService operationLogService;

	@Override
	protected void execMethod() throws Exception {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)) {
				log.info("用户未登陆");
				sendResponseToJson("-9", "用户未登陆");
				return;
			}
			int accFlag = operationLogService.checkUser(driverId);
			if(accFlag == 1) {
				log.info("该用户不存在或已被删除");
				sendResponseToJson("-9","该用户不存在或已被删除");
				return;
			} else if(accFlag == 11) {
				log.info("该用户已被冻结");
				sendResponseToJson("-9","该用户已被冻结");
				return;
			}
			log2Db(driverId);
			
			String parentId = request.getParameter("parentId");
			String encoded = request.getParameter("encoded");
			if(StringUtils.isBlank(parentId) || StringUtils.isBlank(encoded)) {
				sendResponseToJson("-8", "所属物流企业或者编码代码不能为空");
				return;
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("parentId", parentId);
			map.put("encoded", encoded);
			
			WebUserInfoDomain domain = webUserInfoService.selectWebuserInfoByParentIdAndEncoded(map);
			if(domain == null) {
				sendResponseToJson("0", "未找到符合条件的信息");
				return;
			}
			sendResponseToJson("1", "查找成功",domain);
		} catch (Exception e) {
			log.error(e.getMessage());
			sendResponseToJson("-8", e.getMessage());
			e.printStackTrace();			
		}
		
	}

	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectWebuserInfoByParentIdAndEncoded");
		bo.setOperationType(65);
		bo.setRemark("查询WEB用户信息");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}
	
	public void setWebUserInfoService(WebUserInfoService webUserInfoService) {
		this.webUserInfoService = webUserInfoService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
