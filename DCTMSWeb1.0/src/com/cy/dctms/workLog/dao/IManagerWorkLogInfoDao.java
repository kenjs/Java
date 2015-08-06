package com.cy.dctms.workLog.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;

public interface IManagerWorkLogInfoDao {

	/**
	 * ��ѯ����Ա������־��Ϣ�б�
	 * @author:wjl
	 */
	public List<ManagerWorkLogInfoDomain> queryManagerWorkLogInfoList(ManagerWorkLogInfoDomain managerWorkLogInfoDomain);
	/**
	 * �������Ա������־��Ϣ
	 * @author:wjl
	 */
	public void saveManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain);
	/**
	 * ��������Ա������־��Ϣ�б�
	 * @author:wjl
	 */
	public List<ManagerWorkLogInfoDomain> exportManagerWorkLogInfo(ManagerWorkLogInfoDomain managerWorkLogInfoDomain);
}
