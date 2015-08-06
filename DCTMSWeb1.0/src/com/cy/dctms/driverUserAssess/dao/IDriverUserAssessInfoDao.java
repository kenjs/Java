package com.cy.dctms.driverUserAssess.dao;

import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;

public interface IDriverUserAssessInfoDao {

	/**
	 * ��ѯ˾������ҵ������Ϣ�б�
	 * @author:wjl
	 */
	public void queryDriverUserAssessInfoList(DriverUserAssessInfoDomain driverUserAssessInfoDomain);

	/**
	 * ����˾������ҵ������Ϣ�б�
	 * @author:wjl
	 */
	public void exportDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain);

	/**
	 * ��˾������ҵ������Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public DriverUserAssessInfoDomain queryDriverUserAssessInfoById(String id);	

	/**
	 * ����˾������ҵ������Ϣ
	 * @author:wjl
	 */
	public void saveDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain,String userId);

	/**
	 * ɾ��˾������ҵ������Ϣ
	 * @author:wjl
	 */
	public void deleteDriverUserAssessInfo(String id,String userId);
}
