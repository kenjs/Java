package com.cy.dctms.driverUser.service;

import com.cy.dctms.common.domain.DriverUserInfoDomain;

public interface IDriverUserInfoService {
	
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
	public DriverUserInfoDomain queryDriverUserInfoMxById(String id);
	
	/**
	 * ��˾����Ϣ��ϸ����Code
	 * @author:wjl
	 */
	public DriverUserInfoDomain queryDriverUserInfoMxByCode(String code);

	/**
	 * ����˾����Ϣ
	 * @author:wjl
	 */
	public void saveDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain ,String userId);

	/**
	 * ɾ��˾����Ϣ
	 * @author:wjl
	 */
	public void deleteDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId);
	
	/**
	 * ���˾����Ϣ
	 * @author:wjl
	 */
	public void auditDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain ,String userId);
}
	
