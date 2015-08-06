package com.cy.dctms.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by haoy on 2014/9/5.
 */
@Repository("daycountDriverUserBusiDao")
public interface DaycountDriverUserBusiDao {

    /**
     * @param list
     * @throws SQLException
     */
    public void insertDaycountDriverUserBusi(List list) throws SQLException;

    /**
     * 截至当天待确认订单数
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryNoConfirmOrders(int driverId) throws SQLException;

    /**
     * 截至当天待评价订单
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryNoAssessmentOrders(int driverId) throws SQLException;

    /**
     * 当天找货数
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int querySupplyFinds(int driverId) throws SQLException;

    /**
     * 当天报价数
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryPriceQuotes(int driverId) throws SQLException;

    /**
     * 报价成功数
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryPriceQuotesSucceed(int driverId) throws SQLException;

    /**
     * 被订车数
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryForOrders(int driverId) throws SQLException;

    /**
     * 当天成交数
     *
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryTransactions(int driverId) throws SQLException;

    /**
     * 查询有存活数和活跃数记录的司机
     *
     * @return
     * @throws SQLException
     */
    public List<Integer> queryDriverListForActive() throws SQLException;

    /**
     * 查询有业务数据记录的司机
     *
     * @return
     * @throws SQLException
     */
    public List<Integer> queryDriverListForDataCount() throws SQLException;

    /**
     * 货源拨打电话次数
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryCallCargoNum(int driverId) throws SQLException;

    /**
     * 订单拨打电话次数
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int queryCallTransactionNum(int driverId) throws SQLException;
}
