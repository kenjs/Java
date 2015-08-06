package com.cy.driver.dao;

import com.cy.driver.domain.AppReleasesDomain;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/10/13.
 */
@Repository("appReleasesDao")
public interface AppReleasesDao {

    public long queryAppReleaseMaxId() throws SQLException;

    public int queryAppReleaseDownByMaxId(long id) throws SQLException;

    public void updateAppReleaseDownById(Map<String, Object> map) throws SQLException;

    /**
     * APP 最新版本信息
     * @param type
     * @return
     * @throws SQLException
     */
    public AppReleasesDomain queryLatestAppVersionInfo(int type) throws SQLException;

    public List<AppReleasesDomain> queryForceAppInfo(int innerVersion) throws SQLException;
}
