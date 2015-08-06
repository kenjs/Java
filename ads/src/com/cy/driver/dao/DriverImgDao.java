package com.cy.driver.dao;

import com.cy.driver.bo.DriverImg;
import com.cy.driver.domain.DriverImgDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2015/1/21.
 */
@Repository("driverImgDao")
public interface DriverImgDao {

    /**
     * 查询司机文件
     * @param driverImg
     * @return
     * @throws SQLException
     */
    public DriverImgDomain selectDriverImg(DriverImg driverImg) throws SQLException;

    /**
     * 查询司机文件
     * @param driverId
     * @return
     * @throws SQLException
     */
    public List<DriverImgDomain> selectDriverImgByDriverId(String driverId) throws SQLException;

    /**
     * 保存司机图片信息
     * @param driverImg
     * @return
     * @throws SQLException
     */
    public int insertDriverImg(DriverImg driverImg) throws SQLException;

    /**
     * 修改司机图片信息
     * @param driverImg
     * @return
     * @throws SQLException
     */
    public int updateDriverImg(DriverImg driverImg) throws SQLException;

}
