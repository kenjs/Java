package com.cy.dctms.funRelation.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.FunRealationInfoDomain;

public interface IFunRealationInfoDao {

	/**
	 * ��ѯ��Ȩ��Ϣ�б�
	 * @author:wjl
	 */
	public List<FunRealationInfoDomain> queryFunRealationInfoByPage(FunRealationInfoDomain funRealationInfoDomain,String userId);
	/**
	 * ���渳Ȩ��Ϣ
	 * @author:wjl
	 */
	public void saveFunRealationInfo(FunRealationInfoDomain funRealationInfoDomain,String userId);
}
