package com.cy.dctms.operationLog.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.OperationLog;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.OperationLogDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.operationLog.dao.IOperationLogDao;


public class OperationLogDaoImp extends BaseDao implements IOperationLogDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryOperationLogList(OperationLogDomain operationLogDomain) {
		try {
			List<OperationLogDomain> dataList = new ArrayList<OperationLogDomain>();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", operationLogDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (operationLogDomain.getPageInfo().getCurPage()-1)*operationLogDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("userDriverId", operationLogDomain.getUserDriverId());
			queryMap.put("userDriverCode", operationLogDomain.getUserDriverCode());
			queryMap.put("userDriverName", java.net.URLDecoder.decode( operationLogDomain.getUserDriverName(),"utf-8"));
			queryMap.put("type", operationLogDomain.getType());
			queryMap.put("remark", java.net.URLDecoder.decode( operationLogDomain.getRemark(),"utf-8"));
			queryMap.put("queryTimeQ", operationLogDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", operationLogDomain.getQueryTimeZ());
			if ("1".equals(operationLogDomain.getType())) {
				operationLogDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_driver_operationLog_info_count",queryMap));// 总页数
				dataList = (List<OperationLogDomain>) queryForList("query_driver_operationLog_info_by_page",queryMap);
			}else if ("0".equals(operationLogDomain.getType())) {
				operationLogDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_web_operationLog_info_count",queryMap));// 总页数
				dataList = (List<OperationLogDomain>) queryForList("query_web_operationLog_info_by_page",queryMap);
			}
			
			operationLogDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_operationLog_list",e);
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportOperationLog(OperationLogDomain operationLogDomain) {
		try {
			List<OperationLogDomain> dataList = new ArrayList<OperationLogDomain>();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("userDriverId", operationLogDomain.getUserDriverId());
			queryMap.put("userDriverCode", operationLogDomain.getUserDriverCode());
			queryMap.put("userDriverName", operationLogDomain.getUserDriverName());
			queryMap.put("type", operationLogDomain.getType());
			queryMap.put("remark", operationLogDomain.getRemark());
			queryMap.put("queryTimeQ", operationLogDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", operationLogDomain.getQueryTimeZ());
			if ("1".equals(operationLogDomain.getType())) {
				dataList = (List<OperationLogDomain>) queryForList("export_driver_operationLog_info",queryMap);
			}else if ("0".equals(operationLogDomain.getType())) {
				dataList = (List<OperationLogDomain>) queryForList("export_web_operationLog_info",queryMap);
			}
			operationLogDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("export_operationLog_list",e);
			throw new RuntimeException();
		}
	}
	
}
