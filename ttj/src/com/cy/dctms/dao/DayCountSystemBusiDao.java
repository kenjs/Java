package com.cy.dctms.dao;

import com.cy.dctms.common.bo.DayCountSystemBusi;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("dayCountSystemBusiDao")
public interface DayCountSystemBusiDao {

    /**
     * 保存统计数据到指定表
     *
     * @param dayCountSystemBusi
     * @throws SQLException
     */
    public void countDaySystemBusi(DayCountSystemBusi dayCountSystemBusi) throws SQLException;

    /**
     * 当天app下载数
     *
     * @return int
     * @throws SQLException
     */
    public int queryDailyAppDownloadsNum() throws SQLException;

    /**
     * 当天注册企业数
     *
     * @return int
     * @throws SQLException
     */
    public int queryDailyCompanyRegisterNum() throws SQLException;

    /**
     * 当天企业发布货源数
     *
     * @return int
     * @throws SQLException
     */
    public int queryDailyCompanyReleaseCargoNum() throws SQLException;

    /**
     * 当天人工导入或爬虫导入货源数
     *
     * @return int
     * @throws SQLException
     */
    public int queryDailyImportCargoNum() throws SQLException;

    /**
     * 当天注册司机数
     *
     * @return int
     * @throws SQLException
     */
    public int queryDailyRegisterDriversNum() throws SQLException;

    /**
     * 当天订车数（当天交易数）
     *
     * @return int
     * @throws SQLException
     */
    public int queryDailyBookCarsNum() throws SQLException;

    /**
     * 截止到当天app用户注册总数
     *
     * @return int
     * @throws SQLException
     */
    public int queryAppRegisterNumByNow() throws SQLException;

    /**
     * 截止到当天web用户注册总数
     *
     * @return int
     * @throws SQLException
     */
    public int queryWebRegisterNumByNow() throws SQLException;

    /**
     * 认证企业数
     *
     * @return
     * @throws SQLException
     */
    public int queryEnddayAuthUsers() throws SQLException;

    /**
     * 认证司机数
     *
     * @return
     * @throws SQLException
     */
    public int queryEnddayAuthDrivers() throws SQLException;

    /**
     * 司机日存活数
     *
     * @return
     * @throws SQLException
     */
    public int queryDriverDaySurvival() throws SQLException;

    /**
     * 司机日活跃数
     *
     * @return
     * @throws SQLException
     */
    public int queryDriverDayActive() throws SQLException;

    /**
     * 司机周存活数
     *
     * @return
     * @throws SQLException
     */
    int queryDriverWeekSurvival() throws SQLException;

    /**
     * 司机周活跃数
     *
     * @return
     * @throws SQLException
     */
    public int queryDriverWeekActive() throws SQLException;

    /**
     * 司机月存活数
     *
     * @return
     * @throws SQLException
     */
    int queryDriverMonthSurvival();

    /**
     * 司机月活跃数
     *
     * @return
     * @throws SQLException
     */
    int queryDriverMonthActive();

    /**
     * 司机2个月存活数
     *
     * @return
     * @throws SQLException
     */
    int queryDriverMonth2Survival();

    /**
     * 司机2个月活跃数
     *
     * @return
     * @throws SQLException
     */
    int queryDriverMonth2Active();
}
