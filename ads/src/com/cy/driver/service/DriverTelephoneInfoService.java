package com.cy.driver.service;

import com.cy.driver.bo.DriverTelephoneInfo;

/**
 * 记录司机手机信息
 * Created by haoy on 2014/9/24.
 */
public interface DriverTelephoneInfoService {

    /**
     * 新增或修改司机手机信息
     * @param driverTelephoneInfo
     * @return
     * @throws Exception
     */
    public int saveUserMobilePhoneInfo(DriverTelephoneInfo driverTelephoneInfo) throws Exception;
}
