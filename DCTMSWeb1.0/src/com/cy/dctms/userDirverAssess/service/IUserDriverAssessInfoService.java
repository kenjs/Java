package com.cy.dctms.userDirverAssess.service;

import java.util.List;

import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;

public interface IUserDriverAssessInfoService {
	
	/**
	 * ��ѯ��ҵ��˾��������Ϣ�б�
	 * @author:wjl
	 */
	public void queryUserDriverAssessInfoList(UserDriverAssessInfoDomain userDriverAssessInfoDomain);
	
	/**
	 * ������ҵ��˾��������Ϣ�б�
	 * @author:wjl
	 */
	public void exportUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain);

	/**
	 * ����ҵ��˾��������Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public UserDriverAssessInfoDomain queryUserDriverAssessInfoMxById(String id);

	/**
	 * ������ҵ��˾��������Ϣ
	 * @author:wjl
	 */
	public void saveUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain ,String userId);

	/**
	 * ɾ����ҵ��˾��������Ϣ
	 * @author:wjl
	 */
	public void deleteUserDriverAssessInfo(String id,String userId);
	
	
}
	
