package com.cy.dctms.manager.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.ManagerInfoDomain;

public interface IManagerInfoDao {
	
	public ManagerInfoDomain queryManagerInfoByCode(String code);	
	/**
	 * ��ѯ����Ա��Ϣ�б�
	 * @author:wjl
	 */
	public void queryManagerInfoList(ManagerInfoDomain managerInfoDomain);
	/**
	 * ��������Ա��Ϣ�б�
	 * @author:wjl
	 */
	public void exportManagerInfo(ManagerInfoDomain managerInfoDomain);
	/**
	 * �޸Ĺ���Ա����
	 * @author:wjl
	 */
	public void updateManagerPassword(ManagerInfoDomain managerInfoDomain,String userId);
	/**
	 * �����Ա��Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public ManagerInfoDomain queryManagerInfoById(String id);	
	/**
	 * �������Ա��Ϣ
	 * @author:wjl
	 */
	public void saveManagerInfo(ManagerInfoDomain managerInfoDomain,String userId);
	/**
	 * �������Ա�����ַ
	 * @author:wjl
	 */
	public void saveManagerMacInfo(String macAddress ,String id);
	/**
	 * ɾ������Ա��Ϣ
	 * @author:wjl
	 */
	public void deleteManagerInfo(String id,String userId);
	/**
	 * ɾ������Ա�����ַ
	 * @author:wjl
	 */
	public void deleteManagerMacInfo(String id,String userId);
}
