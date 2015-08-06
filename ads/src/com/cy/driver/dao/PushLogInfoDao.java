package com.cy.driver.dao;

import com.cy.driver.bo.PushLogInfo;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by haoy on 2014/12/24.
 */
@Repository("pushLogInfoDao")
public interface PushLogInfoDao {

    /**
     * 通知是否点击
     * @param pushLogInfo
     * @return
     * @throws SQLException
     */
    public int updatePushLogInfoById(PushLogInfo pushLogInfo) throws SQLException;
}
