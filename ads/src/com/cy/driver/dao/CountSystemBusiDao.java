package com.cy.driver.dao;

import com.cy.driver.domain.CountSystemBusiDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by haoy on 2014/9/25.
 */
@Repository("countSystemBusiDao")
public interface CountSystemBusiDao {

    /**
     * 新增
     * @param map
     * @return
     * @throws SQLException
     */
    public int insertCountSystemBusi(Map<String, Object> map) throws SQLException;

    /**
     * 修改
     * @param map
     * @return
     * @throws SQLException
     */
    public int updateCountSystemBusi(Map<String, Object> map) throws SQLException;

    /**
     * 根据driveId查询
     * @return
     * @throws SQLException
     */
    public CountSystemBusiDomain queryCountSystemBusi() throws SQLException;

    public int queryCountSystemBusiColumn(String column) throws SQLException;
}
