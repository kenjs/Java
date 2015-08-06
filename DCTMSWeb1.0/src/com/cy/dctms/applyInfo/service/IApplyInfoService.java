package com.cy.dctms.applyInfo.service;


import com.cy.dctms.common.domain.ApplyInfoDomain;

public interface IApplyInfoService {
	
	/**
	 * 查询身份证验证申请信息列表
	 * @author:wjl
	 */
	public void queryApplyInfoList(ApplyInfoDomain applyInfoDomain);
	
	/**
	 * 查身份证验证申请信息明细根据ID
	 * @author:wjl
	 */
	public ApplyInfoDomain queryApplyInfoMxById(String id);

	/**
	 * 保存身份证验证申请信息
	 * @author:wjl
	 */
	public void saveApplyInfo(ApplyInfoDomain applyInfoDomain ,String userId);

}
	
