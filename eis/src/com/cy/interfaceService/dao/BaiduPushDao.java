package com.cy.interfaceService.dao;

import com.cy.interfaceService.bo.PushLogInfo;
import com.cy.interfaceService.domain.DriverUserInfo;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by haoy on 2014/12/22.
 */
@Repository("baiduPushDao")
public interface BaiduPushDao {

    /**
     * 根据司机ID查找司机信息
     * @param driverId
     * @return
     * @throws SQLException
     */
    public DriverUserInfo queryDriverUserInfoById(String driverId) throws SQLException;

    /**
     * 添加发送日志
     * @param pushLogInfo
     * @return
     * @throws SQLException
     */
    public int addPushLogInfo(PushLogInfo pushLogInfo) throws SQLException;

    /**
     * 修改推送日志表信息
     * @param pushLogInfo
     * @return
     * @throws SQLException
     */
    public int updatePushLogInfoById(PushLogInfo pushLogInfo) throws SQLException;
}
