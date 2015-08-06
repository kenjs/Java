package com.cy.dctms.dao;

import com.cy.dctms.common.bo.MarketingDriverLevelChangeRecord;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2014/12/23.
 */
@Repository("driverUserInfoDao")
public interface DriverUserInfoDao {

    /**
     * 司机最近使用时间
     * @param map
     * @return
     * @throws SQLException
     */
    public int updateDriverUserInfoById(Map<String, String> map) throws SQLException;

    /**
     * 查询最近操作的司机
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> queryDriverUserFromLog() throws SQLException;

    /**
     * 司机客户等级统计
     * @param map
     * @return
     * @throws SQLException
     */
    public int updateMarketingDriverInfo(Map<String, String> map) throws SQLException;

    /**
     * 查询认证通过的司机id
     * @return
     * @throws SQLException
     */
    public List<String> querySubmitedDriverId() throws SQLException;

    /**
     * 查询有经营线路的司机
     * @return
     * @throws SQLException
     */
    public List<String> queryDriverDriverLineCount() throws SQLException;

    /**
     * 查询司机月存活
     * @return
     * @throws SQLException
     */
    public List<String> queryDriverAlive() throws SQLException;

    /**
     * 查询营销专员id
     * @param driverId
     * @return
     * @throws SQLException
     */
    public String queryMarketingAssisterId(String driverId) throws SQLException;

    /**
     * 查询司机等级
     * @return
     * @throws SQLException
     */
    public String queryMarketingDriverLevel(String driverId) throws SQLException;

    /**
     * 新增客户司机等级变更记录
     * @param record
     * @return
     * @throws SQLException
     */
    public int addMarketingDriverLevelChangeRecord(MarketingDriverLevelChangeRecord record) throws SQLException;
}
