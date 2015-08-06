package com.cy.dcts.pactDriverInfo.service;

import java.util.List;

import com.cy.dcts.common.domain.PactDriverInfoDomain;

public interface IPactDriverInfoService {
	/**
	 * 添加合同司机信息
	 * @param pactDriverInfo 合同司机信息对象
	 * @return 新增的主键Id
	 */
	String addPactDriverInfo(PactDriverInfoDomain pactDriverInfoDomain);
	 
	/**
	 * 删除合同司机信息
	 * @param id 合同司机信息Id
	 * @return
	 */
	boolean deletePactDriverInfo(String id);
	
	/**
	 * 查询合同司机信息
	 * @param queryMap
	 * @return
	 */
	List<PactDriverInfoDomain> queryPactDriverInfo(PactDriverInfoDomain pactDriverInfoDomain);
}
