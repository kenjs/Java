package com.cy.dctms.funRelation.service;

import java.util.List;


import com.cy.dctms.common.domain.FunRealationInfoDomain;

public interface IFunRealationInfoService {

	/**
	 * ��ѯ��Ȩ��Ϣ�б�
	 * @author:wjl
	 */
	public List<FunRealationInfoDomain> queryFunRealationInfoList(FunRealationInfoDomain funRealationInfoDomain,String userId);
	/**
	 * ���渳Ȩ��Ϣ
	 * @author:wjl
	 */
	public void saveFunRealationInfo(FunRealationInfoDomain funRealationInfoDomain,String userId);
}