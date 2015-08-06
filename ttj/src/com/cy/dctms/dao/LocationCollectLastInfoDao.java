package com.cy.dctms.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by haoy on 2014/11/27.
 */
@Repository("locationCollectLastInfoDao")
public interface LocationCollectLastInfoDao {

    /**
     * 移动历史位置信息
     * @throws SQLException
     */
    public void addLastLocation() throws SQLException;

    /**
     * 删除历史位置信息
     * @throws SQLException
     */
    public void deleteHistoryLocation() throws SQLException;
}
