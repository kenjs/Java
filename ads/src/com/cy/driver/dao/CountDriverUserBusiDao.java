package com.cy.driver.dao;

import com.cy.driver.domain.CountDriverUserBusiDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by haoy on 2014/9/25.
 */
@Repository("countDriverUserBusiDao")
public interface CountDriverUserBusiDao {

    /**
     * 新增
     * @param map
     * @return
     * @throws SQLException
     */
    public int insertCountDriverUserBusi(Map<String, Object> map) throws SQLException;

    /**
     * 修改
     * @param map
     * @return
     * @throws SQLException
     */
    public int updateCountDriverUserBusi(Map<String, Object> map) throws SQLException;

    /**
     * 根据driveId查询
     * @param driverId
     * @return
     * @throws SQLException
     */
    public CountDriverUserBusiDomain queryCountDriverUserBusi(int driverId) throws SQLException;

    /**
     * 查询特定列的数据
     * @param map
     * @return
     * @throws SQLException
     */
    public int queryCountDriverUserBusiColumn(Map<String, Object> map) throws SQLException;
}
