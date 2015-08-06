package com.cy.dctms.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by haoy on 2014/9/5.
 */
@Repository("daycountWebUserBusiDao")
public interface DaycountWebUserBusiDao {

    /**
     * 新增
     * @param list
     * @throws SQLException
     */
    public void insertDaycountWebUserBusi(List list) throws SQLException;

    /**
     * 截至当天待确认订单数
     * @param webUserId
     * @return
     * @throws SQLException
     */
    public int queryNoConfirmOrders(int webUserId) throws SQLException;

    /**
     * 截至当天待确认收货数
     * @param webUserId
     * @return
     * @throws SQLException
     */
    public int queryNoConfirmReceives(int webUserId) throws SQLException;

    /**
     * 截至当天待评价订单
     * @param webUserId
     * @return
     * @throws SQLException
     */
    public int queryNoAssessmentOrders(int webUserId) throws SQLException;

    /**
     * 当天发布货源数
     * @param webUserId
     * @return
     * @throws SQLException
     */
    public int querySupplyReleases(int webUserId) throws SQLException;

    /**
     * 当天订车数
     * @param webUserId
     * @return
     * @throws SQLException
     */
    public int queryTruckOrders(int webUserId) throws SQLException;

    /**
     * 当天成交数
     * @param webUserId
     * @return
     * @throws SQLException
     */
    public int queryTransactions(int webUserId) throws SQLException;

    /**
     * 查询企业
     * @return
     * @throws SQLException
     */
    public List<Integer> queryCompanyList() throws SQLException;

}
