package com.cy.driver.service.impl;

import com.cy.driver.bo.PactDriverInfo;
import com.cy.driver.dao.PactDriverInfoDao;
import com.cy.driver.domain.PactDriverInfoDomain;
import com.cy.driver.domain.VipDriverLineInfoDomain;
import com.cy.driver.service.PactDriverInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @description 会员操作service实现类
 * @author 		haoy
 *
 */
@Service("pactDriverInfoService")
public class PactDriverInfoServiceImpl implements PactDriverInfoService{

    @Resource
	private PactDriverInfoDao pactDriverInfoDao;

	public void setPactDriverInfoDao(PactDriverInfoDao pactDriverInfoDao) {
		this.pactDriverInfoDao = pactDriverInfoDao;
	}

	public PactDriverInfoServiceImpl() {
	}

	@Override
	public List<PactDriverInfoDomain> selectPactDriverInfoList(
			Map<String, String> map) throws Exception {

		List<PactDriverInfoDomain> dataList = new ArrayList<PactDriverInfoDomain>();
		List<PactDriverInfoDomain> list = pactDriverInfoDao.selectPactDriverInfoList(map);

		for (PactDriverInfoDomain pactDriverInfoDomain : list) {
			//vip
			if ("1".equals(pactDriverInfoDomain.getPactType())) {
				int businessLines = pactDriverInfoDao.queryVipDriverLineNum(pactDriverInfoDomain.getId());
				pactDriverInfoDomain.setBusinessLines(businessLines);
			}
			dataList.add(pactDriverInfoDomain);
		}
		return dataList;
	}

	@Override
	public int updatePactDriverInfo(PactDriverInfo info) throws Exception {
		return pactDriverInfoDao.updatePactDriverInfo(info);
	}

	@Override
	public List<VipDriverLineInfoDomain> queryVipDriverLineList(Map<String,String> map)
			throws SQLException {
		return pactDriverInfoDao.queryVipDriverLineList(map);
	}

    @Override
    public int updateVipDriverLineInfo(Map<String, Object> map) throws SQLException {
        return pactDriverInfoDao.updateVipDriverLineInfo(map);
    }
}
