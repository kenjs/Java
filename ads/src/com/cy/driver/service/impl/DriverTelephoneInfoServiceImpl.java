package com.cy.driver.service.impl;

import com.cy.driver.bo.DriverTelephoneInfo;
import com.cy.driver.dao.DriverTelephoneInfoDao;
import com.cy.driver.domain.DriverTelephoneInfoDomain;
import com.cy.driver.service.DriverTelephoneInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by haoy on 2014/9/24.
 */
@Service("driverTelephoneInfoService")
public class DriverTelephoneInfoServiceImpl implements DriverTelephoneInfoService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DriverTelephoneInfoDao driverTelephoneInfoDao;

    public void setDriverTelephoneInfoDao(DriverTelephoneInfoDao driverTelephoneInfoDao) {
        this.driverTelephoneInfoDao = driverTelephoneInfoDao;
    }

    @Override
    public int saveUserMobilePhoneInfo(DriverTelephoneInfo driverTelephoneInfo) throws Exception {

        int rst;

        DriverTelephoneInfoDomain driverTelephoneInfoDomain = driverTelephoneInfoDao.queryTelephoneInfo(driverTelephoneInfo.getDriverId());

        if (driverTelephoneInfoDomain == null) {//新增
            driverTelephoneInfoDao.insertTelephoneInfo(driverTelephoneInfo);
            rst = driverTelephoneInfo.getId();
        } else {//修改
            driverTelephoneInfo.setId(Integer.parseInt(driverTelephoneInfoDomain.getId()));
            rst = driverTelephoneInfoDao.updateTelephoneInfo(driverTelephoneInfo);
        }

        return rst;
    }
}
