package com.cy.dctms.workLog.service;

import java.util.List;


import com.cy.dctms.common.domain.ManagerWorkLogInfoDomain;

public interface IManagerWorkLogInfoService {

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
