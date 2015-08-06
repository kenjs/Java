package com.cy.driver.pactDriver.dao.impl;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.PactDriverInfo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.pactDriver.dao.PactDriverInfoDao;
import com.cy.driver.pactDriver.domain.PactDriverInfoDomain;
/**
 * @description	会员操作dao实现类
 * @author Administrator
 *
 */
public class PactDriverInfoDaoImpl extends BaseDao implements PactDriverInfoDao {

	public PactDriverInfoDaoImpl() {
		
	}

	@Override
	public List<PactDriverInfoDomain> selectPactDriverInfoList(
			Map<String, String> map) throws Exception {
		return (List<PactDriverInfoDomain>) queryForList("iBatisSelectPactDriverInfo", map);
	}

	@Override
	public int updatePactDriverInfo(PactDriverInfo info) throws Exception {
		return saveObject("iBatisUpdatePactDriverInfo", info);
	}

}
