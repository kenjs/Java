package com.cy.dctms.applyInfo.service;


import com.cy.dctms.common.domain.ApplyInfoDomain;

public interface IApplyInfoService {
	
	/**
	 * ��ѯ���֤��֤������Ϣ�б�
	 * @author:wjl
	 */
	public void queryApplyInfoList(ApplyInfoDomain applyInfoDomain);
	
	/**
	 * �����֤��֤������Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public ApplyInfoDomain queryApplyInfoMxById(String id);

	/**
	 * �������֤��֤������Ϣ
	 * @author:wjl
	 */
	public void saveApplyInfo(ApplyInfoDomain applyInfoDomain ,String userId);

}
	
