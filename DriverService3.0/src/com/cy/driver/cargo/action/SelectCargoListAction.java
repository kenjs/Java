package com.cy.driver.cargo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.action.BaseJsonAction;
import com.cy.common.bo.OperationLogInfoBo;
import com.cy.driver.cargo.domain.OrderCargoInfoDomain;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.operationLog.service.OperationLogService;
/**
 * 查找货源
 * @date 2014-6-4
 * @author haoyong
 *
 */
public class SelectCargoListAction extends BaseJsonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5177258595607027607L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private OrderCargoInfoService orderCargoInfoService;
	
	protected void execMethod() throws Exception {
		
	}

	public String exec() {
		try {
			String driverId = request.getParameter("driverId");
			if(StringUtils.isBlank(driverId)){
				log.info("司机不存在");
				sendResponseToJson("-9","司机不存在");
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
			
			Map<String,String> map = getMap();
			if(map.containsKey("startTime")) {
				if(StringUtils.isBlank(map.get("startTime"))){
					log.info("查找失败, 用户没有选择装货时间");
					sendResponseToJson("-8","用户没有选择装货时间");
					return ERROR;
				}
				String el = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
				boolean chkDat = map.get("startTime").toString().matches(el);
				if(! chkDat) {
					log.info("装货时间格式不正确");
					sendResponseToJson("-8","装货时间格式不正确");
					return ERROR;
				}
			}
			//分页相关
			String fromSize = request.getParameter("fromSize");
			String listSize = request.getParameter("listSize");
			if(StringUtils.isBlank(fromSize)) fromSize = "0";
			if(StringUtils.isBlank(listSize)) listSize = "500";
			
			map.put("fromSize", fromSize);
			map.put("listSize", listSize);
			List<OrderCargoInfoDomain> list = orderCargoInfoService.selectCargoList(map);
			if(list != null){
				if(list.size() == 0){
					log.info("未找到符合条件的信息");
					sendResponseToJson("0","未找到符合条件的信息");
				}else{
					log.info("查找成功, 共找到"+ list.size() +"条数据");
					sendResponseToJson("1","查找成功",list);
				}
			}else{
				log.info("未找到符合条件的信息");
				sendResponseToJson("0","未找到符合条件的信息");
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

	private Map<String,String> getMap() {
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("startTime", request.getParameter("startTime"));
		map.put("endTime", request.getParameter("endTime"));
		map.put("startProvince", request.getParameter("startProvince"));
		map.put("endProvince", request.getParameter("endProvince"));
		map.put("startCity", request.getParameter("startCity"));
		map.put("endCity", request.getParameter("endCity"));
		map.put("carType", request.getParameter("carType"));
		map.put("carLength", request.getParameter("carLength"));
		map.put("companyName", request.getParameter("companyName"));
		
		return map;
	}
	
	public void setOrderCargoInfoService(OrderCargoInfoService orderCargoInfoService) {
		this.orderCargoInfoService = orderCargoInfoService;
	}
	
	private OperationLogService operationLogService;
	
	private void log2Db(String driverId) {
		OperationLogInfoBo bo = new OperationLogInfoBo();
		bo.setOperationName("selectCargoListAction");
		bo.setOperationType(26);
		bo.setRemark("搜索找货");
		if(StringUtils.isNotBlank(driverId)) {
			bo.setUserDriverId(Integer.parseInt(driverId));
		}
		operationLogService.insertOperationLog(bo);
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
