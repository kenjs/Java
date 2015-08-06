package com.cy.dctms.webUser.service;

import com.cy.dctms.common.domain.WebUserInfoDomain;

public interface IWebUserInfoService {
	
	/**
	 * ��ѯ��ҵ��Ϣ�б�
	 * @author:wjl
	 */
	public void queryWebUserInfoList(WebUserInfoDomain webUserInfoDomain);
	
	/**
	 * ��ѯ�����ҵ��Ϣ�б�
	 * @author:wjl
	 */
	public void auditWebUserInfoList(WebUserInfoDomain webUserInfoDomain);
	
	/**
	 * ��ѯ��ҵ������Ϣ�б�
	 * @author:wjl
	 */
	public void queryWebUserInfoTransactionList(WebUserInfoDomain webUserInfoDomain);

	/**
	 * ����ҵ��Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public WebUserInfoDomain queryWebUserInfoMxById(String id);

	/**
	 * ������ҵ��Ϣ
	 * @author:wjl
	 */
	public void saveWebUserInfo(WebUserInfoDomain webUserInfoDomain ,String userId);

	/**
	 * ɾ����ҵ��Ϣ
	 * @author:wjl
	 */
	public void deleteWebUserInfo(WebUserInfoDomain webUserInfoDomain,String userId);
	/**
	 * �����ҵ��Ϣ
	 * @author:wjl
	 */
	public void auditWebUserInfo(WebUserInfoDomain webUserInfoDomain ,String userId);

}
	
