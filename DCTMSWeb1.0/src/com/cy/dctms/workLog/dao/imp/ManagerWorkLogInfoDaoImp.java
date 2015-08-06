package com.cy.dctms.workLog.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.workLog.dao.IManagerWorkLogInfoDao;

public class ManagerWorkLogInfoDaoImp extends BaseDao implements IManagerWorkLogInfoDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<ManagerWorkLogInfoDomain> queryManagerWorkLogInfoList(ManagerWorkLogInfoDomain managerWorkLogInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", managerWorkLogInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (managerWorkLogInfoDomain.getPageInfo().getCurPage()-1)*managerWorkLogInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("name", java.net.URLDecoder.decode(managerWorkLogInfoDomain.getName(),"utf-8"));
			queryMap.put("managerId", managerWorkLogInfoDomain.getManagerId());
			queryMap.put("columnId", managerWorkLogInfoDomain.getColumnId());
			queryMap.put("managerCode", managerWorkLogInfoDomain.getManagerCode());
			queryMap.put("managerName", java.net.URLDecoder.decode(managerWorkLogInfoDomain.getManagerName(),"utf-8"));
			managerWorkLogInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_manager_workLog_info_count",queryMap));// 总页数
			return (List<ManagerWorkLogInfoDomain>) queryForList("query_manager_workLog_info_by_page",queryMap);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ManagerWorkLogInfoDomain> exportManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("name", java.net.URLDecoder.decode(managerWorkLogInfoDomain.getName(),"utf-8"));
			queryMap.put("managerId", managerWorkLogInfoDomain.getManagerId());
			queryMap.put("columnId", managerWorkLogInfoDomain.getColumnId());
			queryMap.put("managerCode", managerWorkLogInfoDomain.getManagerCode());
			queryMap.put("managerName", java.net.URLDecoder.decode(managerWorkLogInfoDomain.getManagerName(),"utf-8"));
			return (List<ManagerWorkLogInfoDomain>) queryForList("export_manager_workLog_info",queryMap);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void saveManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain) {
		try {
			ManagerWorkLogInfo bo = new ManagerWorkLogInfo();
			bo.setName(managerWorkLogInfoDomain.getName());
			bo.setColumnId(Long.valueOf(managerWorkLogInfoDomain.getColumnId()));
			bo.setContent(managerWorkLogInfoDomain.getContent());
			bo.setManagerId(Long.valueOf(managerWorkLogInfoDomain.getManagerId()));
			bo.setTableName(managerWorkLogInfoDomain.getTableName());
			addObject("add_manager_workLog_info", bo);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
