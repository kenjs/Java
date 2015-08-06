package com.cy.driver.service.impl;

import com.cy.driver.bo.LocationCollectLastInfoBo;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.dao.LocationLastInfoDao;
import com.cy.driver.service.LocationLastInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 位置信息service impl
 * @date 2014-6-6
 * @author haoyong
 *
 */
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
@Service("locationLastInfoService")
public class LocationLastInfoServiceImpl implements LocationLastInfoService {

    @Resource
	private LocationLastInfoDao locationLastInfoDao;
	
	public void setLocationLastInfoDao(LocationLastInfoDao locationLastInfoDao) {
		this.locationLastInfoDao = locationLastInfoDao;
	}

	public int insertLastLocation(LocationCollectLastInfoBo bo) {
        if (bo != null) {
            String currenTime = DateUtil.getCurrentDateTime();
            bo.setLastTime(currenTime);
            bo.setCreateTime(currenTime);
            bo.setModifyTime(currenTime);
            bo.setLocation(bo.getProvince() + bo.getCity() + bo.getCounty() + bo.getTown());
        }
		locationLastInfoDao.insertLastLocation(bo);
        return bo.getId();
	}

	public int insertLocation(LocationCollectLastInfoBo bo) {
        if (bo != null) {
            String currenTime = DateUtil.getCurrentDateTime();
            bo.setCollectTime(currenTime);
            bo.setCreateTime(currenTime);
            bo.setLocation(bo.getProvince() + bo.getCity() + bo.getCounty() + bo.getTown());
        }
		locationLastInfoDao.insertLocation(bo);
        return bo.getId();
	}

	public int checkLocationExist(String driverId) {
		return locationLastInfoDao.checkLocationExist(driverId);
	}

	public void updateLastLocation(LocationCollectLastInfoBo bo) {
        if (bo != null) {
            String currenTime = DateUtil.getCurrentDateTime();
            bo.setLastTime(currenTime);
            bo.setModifyTime(currenTime);
            bo.setLocation(bo.getProvince() + bo.getCity() + bo.getCounty() + bo.getTown());
        }
		locationLastInfoDao.updateLastLocation(bo);
	}

}
