package com.cy.driver.pactDriver.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.operationLog.service.OperationLogService;
import com.cy.driver.pactDriver.domain.PactDriverInfoDomain;
import com.cy.driver.pactDriver.service.PactDriverInfoService;
/**
 * @description 合同车源查询
 * @author 		haoy
 * @since 		2014-8-27
 *
 */
public class QueryPactDriverInfoByType extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1984578847094467492L;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private OperationLogService operationLogService;
	private PactDriverInfoService pactDriverInfoService;

	@Override
	protected void execMethod() throws Exception {
		
		try {
			//查询参数
			String driverId = request.getParameter("driverId");
			String pactStart = request.getParameter("pactStart");
			String fromSize = request.getParameter("fromSize");
			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
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
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("driverId", driverId);
			map.put("pactStart", pactStart);
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			
			List<PactDriverInfoDomain> list = pactDriverInfoService.selectPactDriverInfoList(map);
			if(list != null) {
				if(list.size() == 0) {
					sendResponseToJson("0", "未找到符合条件的信息");
				} else {
					sendResponseToJson("1", "查找成功", list);
				}
			} else {
				sendResponseToJson("0", "未找到符合条件的信息");
			}
			
			String remark = "";
			if("0".equals(pactStart)) {
				remark = "待确认会员信息查询";
			} else if("1".equals(pactStart)) {
				remark = "我的会员信息查询";
			}
			log2Db(driverId, remark);//记录操作日志
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void log2Db(String driverId, String remark) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("queryPactDriverInfoByType");
		bo.setOperationType(74);
		bo.setRemark(remark);
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
