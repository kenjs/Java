package com.cy.dctms.dao;

import com.cy.dctms.common.bo.DaycountDriverActive;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by haoy on 2014/9/23.
 */
@Repository("daycountDriverActiveDao")
public interface DaycountDriverActiveDao {
    /**
     * 新增
     * @param list
     * @throws SQLException
     */
    public void insertDaycountDriverActive(List<DaycountDriverActive> list) throws SQLException;

    /**
     * 修改
     * @param list
     * @throws SQLException
     */
    public void updateDaycountDriverActive(List<DaycountDriverActive> list) throws SQLException;

    /**
     * 查询
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int selectDaycountDriverActive(int driverId) throws SQLException;

    /**
     * 查询司机是否上传位置信息
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int selectDriverLastLocation(int driverId) throws SQLException;

    /**
     * 司机打开次数
     * @param driverId
     * @return
     * @throws SQLException
     */
//    public int selectDriverDayOpens(int driverId) throws SQLException;

    /**
     * 司机主动通讯次数
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int selectDriverDayInitiativeLinks(int driverId) throws SQLException;

    /**
     * 司机被动收到的货源推送次数
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int selectDriverDayHuoyuanPushs(int driverId) throws SQLException;

    /**
     * 司机被动上传位置信息次数
     * @param driverId
     * @return
     * @throws SQLException
     */
    public int selectDriverDayLocationUps(int driverId) throws SQLException;
}
