package com.cy.driver.dao;

import com.cy.driver.domain.CountWebUserBusiDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Administrator on 2014/10/13.
 */
@Repository("countWebUserBusiDao")
public interface CountWebUserBusiDao {

    public CountWebUserBusiDomain queryCountWebUserBusiDomainByWebUserId(int webUserId) throws SQLException;

    public int queryCountWebUserBusiColumnByWebUserId(Map<String, Object> map) throws SQLException;

    public void insertCountWebUserBusiC(Map<String, Object> map) throws SQLException;

    public int updateCountWebUserBusiByWebUserId(Map<String, Object> map) throws SQLException;

    public int queryWebUserIdById(String id) throws SQLException;

    public int queryDriverUserAuditFlagById(int driverId) throws SQLException;
}
