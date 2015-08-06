package com.cy.driver.dao;

import com.cy.driver.bo.DriverUserInfoBo;
import com.cy.driver.bo.MarketingDriverBusinessLine;
import com.cy.driver.bo.MarketingDriverInfo;
import com.cy.driver.bo.MarketingDriverLine;
import com.cy.driver.domain.DriverUserInfoDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/12/18.
 */
@Repository("marketingDriverInfoDao")
public interface MarketingDriverInfoDao {

    /**
     * 根据手机号码查询
     * @param code
     * @return
     * @throws java.sql.SQLException
     */
    public MarketingDriverInfo selectMarketingDriverInfoByMobile(String code) throws SQLException;

    /**
     * 添加司机
     * @param driverUserInfoBo
     * @return
     * @throws SQLException
     */
    public int addDriverFromMarketingDriver(DriverUserInfoBo driverUserInfoBo) throws SQLException;

    /**
     * 查询线路
     * @param customerDriverId
     * @return
     * @throws SQLException
     */
    public List<MarketingDriverLine> queryMarketingDriverLines(String customerDriverId) throws SQLException;

    /**
     * 添加线路
     * @param list
     * @throws SQLException
     */
    public void addDriverLine(List<MarketingDriverLine> list) throws SQLException;

    /**
     * 查询司机信息
     * @param id
     * @return
     * @throws SQLException
     */
    public DriverUserInfoDomain queryDriverUserInfoByCode(String id) throws SQLException;

    /**
     * 修改司机信息
     * @param map
     * @throws SQLException
     */
    public void updateMarketingDriverInfoById(Map<String, Object> map) throws SQLException;

    /**
     *查询营销司机预约线路
     * @param driverId
     * @return
     * @throws SQLException
     */
    public List<MarketingDriverBusinessLine> queryMarketingDriverBusinessLines(String driverId) throws SQLException;

    /**
     * 新增预约线路
     * @param list
     * @throws SQLException
     */
    public void addDriverBusinessLineFromMarketing(List<MarketingDriverBusinessLine> list) throws SQLException;

    /**
     * 添加营销司机信息
     * @param driverId
     * @throws SQLException
     */
    public void addMarketingDriverInfo(int driverId) throws SQLException;

    public int queryNoteOperateDriversByPhone(String phone) throws SQLException;

    public int updateNoteOperateDriversRegTime(Map<String, Object> map) throws SQLException;
}
