package com.cy.driver.pactDriver.service;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.PactDriverInfo;
import com.cy.driver.pactDriver.domain.PactDriverInfoDomain;
/**
 * @description 会员操作service
 * @author 		haoy
 *
 */
public interface PactDriverInfoService {

	/**
	 * 查询会员列表
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	public List<PactDriverInfoDomain> selectPactDriverInfoList(Map<String,String> map) throws Exception;
	
	/**
	 * 修改会员状态
	 * @param info
	 * @return	int
	 * @throws Exception
	 */
	public int updatePactDriverInfo(PactDriverInfo info) throws Exception;
}
