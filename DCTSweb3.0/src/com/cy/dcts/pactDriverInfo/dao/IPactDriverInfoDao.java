package com.cy.dcts.pactDriverInfo.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.domain.PactDriverInfoDomain;

public interface IPactDriverInfoDao {
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
	boolean deletePactDriverInfo(Map<String, Object> modifyMap);
	
	/**
	 * 查询合同司机信息--不分页
	 * @param queryMap
	 * @return
	 */
	List<PactDriverInfoDomain> queryPactDriverInfo(Map<String, Object> queryMap);
	
	/**
	 * 查询合同司机信息--分页
	 * @param queryMap
	 * @return
	 */
	List<PactDriverInfoDomain> queryPactDriverInfoByPage(Map<String, Object> queryMap);
	
	/**
	 * 查询合同司机信息总记录数
	 * @param queryMap
	 * @return
	 */
	Integer queryPactDriverInfoCount(Map<String, Object> queryMap);
}
