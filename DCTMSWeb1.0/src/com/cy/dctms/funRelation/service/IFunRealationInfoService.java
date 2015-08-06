package com.cy.dctms.funRelation.service;

import java.util.List;


import com.cy.dctms.common.domain.FunRealationInfoDomain;

public interface IFunRealationInfoService {

	/**
	 * 查询赋权信息列表
	 * @author:wjl
	 */
	public List<FunRealationInfoDomain> queryFunRealationInfoList(FunRealationInfoDomain funRealationInfoDomain,String userId);
	/**
	 * 保存赋权信息
	 * @author:wjl
	 */
	public void saveFunRealationInfo(FunRealationInfoDomain funRealationInfoDomain,String userId);
}