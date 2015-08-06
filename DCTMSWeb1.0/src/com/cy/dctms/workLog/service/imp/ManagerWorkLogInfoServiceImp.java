package com.cy.dctms.workLog.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;
import com.cy.dctms.workLog.dao.IManagerWorkLogInfoDao;
import com.cy.dctms.workLog.service.IManagerWorkLogInfoService;

public class ManagerWorkLogInfoServiceImp implements IManagerWorkLogInfoService {

	private IManagerWorkLogInfoDao managerWorkLogInfoDao;

	@Override
	public List<ManagerWorkLogInfoDomain> queryManagerWorkLogInfoList(ManagerWorkLogInfoDomain managerWorkLogInfoDomain) {
		return managerWorkLogInfoDao.queryManagerWorkLogInfoList(managerWorkLogInfoDomain);
	}
	@Override
	public void saveManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain) {
		managerWorkLogInfoDao.saveManagerWorkLogInfo(managerWorkLogInfoDomain);
	}
	public void setManagerWorkLogInfoDao(IManagerWorkLogInfoDao managerWorkLogInfoDao) {
		this.managerWorkLogInfoDao = managerWorkLogInfoDao;
	}
	@Override
	public List<ManagerWorkLogInfoDomain> exportManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain) {
		return managerWorkLogInfoDao.exportManagerWorkLogInfo(managerWorkLogInfoDomain);
	}
}
