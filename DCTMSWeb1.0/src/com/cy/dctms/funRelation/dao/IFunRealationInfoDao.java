package com.cy.dctms.funRelation.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.FunRealationInfoDomain;

public interface IFunRealationInfoDao {

	/**
	 * 查询赋权信息列表
	 * @author:wjl
	 */
	public List<FunRealationInfoDomain> queryFunRealationInfoByPage(FunRealationInfoDomain funRealationInfoDomain,String userId);
	/**
	 * 保存赋权信息
	 * @author:wjl
	 */
	public void saveFunRealationInfo(FunRealationInfoDomain funRealationInfoDomain,String userId);
}
