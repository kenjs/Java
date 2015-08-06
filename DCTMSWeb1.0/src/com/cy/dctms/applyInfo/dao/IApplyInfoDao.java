package com.cy.dctms.applyInfo.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.ApplyInfoDomain;

public interface IApplyInfoDao {

	/**
	 * ��ѯ���֤��֤������Ϣ�б�
	 * @author:wjl
	 */
	public void queryApplyInfoList(ApplyInfoDomain applyInfoDomain);

	/**
	 * �����֤��֤������Ϣ��ϸ����ID
	 * @author:wjl
	 */
	public ApplyInfoDomain queryApplyInfoById(String id);	

	/**
	 * �������֤��֤������Ϣ
	 * @author:wjl
	 */
	public void saveApplyInfo(ApplyInfoDomain applyInfoDomain,String userId);

}
