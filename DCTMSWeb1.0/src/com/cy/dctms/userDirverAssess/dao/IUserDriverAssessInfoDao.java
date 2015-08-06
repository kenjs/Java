package com.cy.dctms.userDirverAssess.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.UserDriverAssessInfoDomain;

public interface IUserDriverAssessInfoDao {

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
	public UserDriverAssessInfoDomain queryUserDriverAssessInfoById(String id);	

	/**
	 * ������ҵ��˾��������Ϣ
	 * @author:wjl
	 */
	public void saveUserDriverAssessInfo(UserDriverAssessInfoDomain userDriverAssessInfoDomain,String userId);

	/**
	 * ɾ����ҵ��˾��������Ϣ
	 * @author:wjl
	 */
	public void deleteUserDriverAssessInfo(String id,String userId);
}
