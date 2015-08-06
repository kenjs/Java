package com.cy.dctms.workLog.service;

import java.util.List;


import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;

public interface IManagerWorkLogInfoService {

	/**
	 * 查询管理员操作日志信息列表
	 * @author:wjl
	 */
	public List<ManagerWorkLogInfoDomain> queryManagerWorkLogInfoList(ManagerWorkLogInfoDomain managerWorkLogInfoDomain);
	/**
	 * 保存管理员操作日志信息
	 * @author:wjl
	 */
	public void saveManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain);
	/**
	 * 导出管理员操作日志信息列表
	 * @author:wjl
	 */
	public List<ManagerWorkLogInfoDomain> exportManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain);
		}
