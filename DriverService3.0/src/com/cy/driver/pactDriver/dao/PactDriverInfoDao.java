package com.cy.driver.pactDriver.dao;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.PactDriverInfo;
import com.cy.driver.pactDriver.domain.PactDriverInfoDomain;
/**
 * @description 会员操作dao      
 * @author 		haoy
 *
 */
public interface PactDriverInfoDao {

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
