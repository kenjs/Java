package com.cy.dctms.applyInfo.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.ApplyInfoDomain;

public interface IApplyInfoDao {

	/**
	 * 查询身份证验证申请信息列表
	 * @author:wjl
	 */
	public void queryApplyInfoList(ApplyInfoDomain applyInfoDomain);

	/**
	 * 查身份证验证申请信息明细根据ID
	 * @author:wjl
	 */
	public ApplyInfoDomain queryApplyInfoById(String id);	

	/**
	 * 保存身份证验证申请信息
	 * @author:wjl
	 */
	public void saveApplyInfo(ApplyInfoDomain applyInfoDomain,String userId);

}
