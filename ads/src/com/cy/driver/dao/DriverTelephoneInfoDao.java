package com.cy.driver.dao;

import com.cy.driver.bo.DriverTelephoneInfo;
import com.cy.driver.domain.DriverTelephoneInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by haoy on 2014/9/24.
 */
@Repository("driverTelephoneInfoDao")
public interface DriverTelephoneInfoDao {
    /**
     * 新增
     * @param driverTelephoneInfo
     * @return
     * @throws java.sql.SQLException
     */
    public int insertTelephoneInfo(DriverTelephoneInfo driverTelephoneInfo) throws SQLException;

    /**
     * 修改
     * @param driverTelephoneInfo
     * @return
     * @throws java.sql.SQLException
     */
    public int updateTelephoneInfo(DriverTelephoneInfo driverTelephoneInfo) throws SQLException;

    /**
     * 查询
     * @param driverId
     * @return
     * @throws java.sql.SQLException
     */
    public DriverTelephoneInfoDomain queryTelephoneInfo(int driverId) throws SQLException;
}
