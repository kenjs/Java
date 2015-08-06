package com.cy.driver.pactDriver.service.impl;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.PactDriverInfo;
import com.cy.driver.pactDriver.dao.PactDriverInfoDao;
import com.cy.driver.pactDriver.domain.PactDriverInfoDomain;
import com.cy.driver.pactDriver.service.PactDriverInfoService;
/**
 * @description 会员操作service实现类
 * @author 		haoy
 *
 */
public class PactDriverInfoServiceImpl implements PactDriverInfoService{

	private PactDriverInfoDao pactDriverInfoDao;
	
	public void setPactDriverInfoDao(PactDriverInfoDao pactDriverInfoDao) {
		this.pactDriverInfoDao = pactDriverInfoDao;
	}

	public PactDriverInfoServiceImpl() {
	}

	@Override
	public List<PactDriverInfoDomain> selectPactDriverInfoList(
			Map<String, String> map) throws Exception {
		return pactDriverInfoDao.selectPactDriverInfoList(map);
	}

	@Override
	public int updatePactDriverInfo(PactDriverInfo info) throws Exception {
		return pactDriverInfoDao.updatePactDriverInfo(info);
	}

}
