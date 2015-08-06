package com.cy.driver.service.impl;

import com.cy.driver.bo.DriverUserAssessInfoBo;
import com.cy.driver.dao.DriverUserAssessInfoDao;
import com.cy.driver.domain.AssessDomain;
import com.cy.driver.domain.UserDriverAssessInfoDomain;
import com.cy.driver.service.DriverUserAssessInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * 货源评价service impl
 * @date 2014-6-9
 * @author haoyong
 *
 */
@Service("driverUserAssessInfoService")
public class DriverUserAssessInfoServiceImpl implements
		DriverUserAssessInfoService {

    @Resource
	private DriverUserAssessInfoDao driverUserAssessInfoDao;
	
	public void setDriverUserAssessInfoDao(
			DriverUserAssessInfoDao driverUserAssessInfoDao) {
		this.driverUserAssessInfoDao = driverUserAssessInfoDao;
	}

	public int addNewDriverUserAssessInfo(DriverUserAssessInfoBo bo) {
		return driverUserAssessInfoDao.addNewDriverUserAssessInfo(bo);
	}

	public int updateDriverUserAssessInfo(DriverUserAssessInfoBo bo) {
		return driverUserAssessInfoDao.updateDriverUserAssessInfo(bo);
	}
	
	public int selectAssessNum(DriverUserAssessInfoBo bo) {
		Object obj = driverUserAssessInfoDao.selectAssessNum(bo);
        if (obj == null) {
            return 0;

        }
        return (Integer) obj;
	}

	public int selectDriverUserAssess(String transactionId) {		
		return driverUserAssessInfoDao.selectDriverUserAssess(transactionId);
	}

	public List<AssessDomain> selectUserDriverAssessNum(String driverId)
			throws Exception {
		return driverUserAssessInfoDao.selectUserDriverAssessNum(driverId);
	}

	public List<UserDriverAssessInfoDomain> selectUserDriverAssessInfoList(
			Map<String, Object> map) throws Exception {
		return driverUserAssessInfoDao.selectUserDriverAssessInfoList(map);
	}

	
}
