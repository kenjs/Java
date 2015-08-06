package com.cy.dctms.driverUser.dao;

import com.cy.dctms.common.domain.DriverUserInfoDomain;

public interface IDriverUserInfoDao {

	/**
	 * ��ѯ˾����Ϣ�б�
	 * @author:wjl
	 */
	public void queryDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain);
	

	/**
	 * ���˾����Ϣ�б�
	 * @author:wjl
	 */
	public void auditDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain);
	
	/**
	 * ��ѯ˾��������Ϣ�б�
	 * @author:wjl
	 */
	public void queryDriverUserTransactionInfoList(DriverUserInfoDomain driverUserInfoDomain);
	
	/**
	 * ��ѯ˾��������б�
	 * @author:wjl
	 */
	public void driverUserTotalDataList(DriverUserInfoDomain driverUserInfoDomain);
	
	/**
	 * ��˾����Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public DriverUserInfoDomain queryDriverUserInfoById(String id);	
	
	/**
	 * ��˾����Ϣ��ϸ����Code
	 * @author:wjl
	 */
	public DriverUserInfoDomain queryDriverUserInfoByCode(String code);	

	/**
	 * ����˾����Ϣ
	 * @author:wjl
	 */
	public void saveDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId);

	/**
	 * ɾ��˾����Ϣ
	 * @author:wjl
	 */
	public void deleteDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId);
	
	/**
	 * ���˾����Ϣ
	 * @author:wjl
	 */
	public void auditDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId);
}
