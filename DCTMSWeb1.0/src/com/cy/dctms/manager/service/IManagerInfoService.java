package com.cy.dctms.manager.service;

import java.util.List;

import com.cy.dctms.common.domain.ManagerInfoDomain;

public interface IManagerInfoService {
	/**
	 * ����Ա��½
	 * @author:wjl
	 */
	public ManagerInfoDomain userLogin(ManagerInfoDomain managerInfoDomain);
	/**
	 * ����Ա�����޸�
	 * @author:wjl
	 */
	public void updateManagerPassword(ManagerInfoDomain managerInfoDomain ,String userId);
		/**
	 * ��ѯ����Ա��Ϣ�б�
	 * @author:wjl
	 */
	public void queryManagerInfoList(ManagerInfoDomain managerInfoDomain);
	/**
	 * �����Ա��Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public ManagerInfoDomain queryManagerInfoMxById(String id);
	/**
	 * �������Ա��Ϣ
	 * @author:wjl
	 */
	public void saveManagerInfo(ManagerInfoDomain managerInfoDomain ,String userId);
	/**
	 * ɾ������Ա��Ϣ
	 * @author:wjl
	 */
	public void deleteManagerInfo(String id,String userId);
	/**
	 * ��������Ա��Ϣ�б�
	 * @author:wjl
	 */
	public void exportManagerInfo(ManagerInfoDomain managerInfoDomain);
}
	
