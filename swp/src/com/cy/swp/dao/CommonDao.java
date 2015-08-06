package com.cy.swp.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by nixianjing on 14/12/8.
 */
@Repository("commonDao")
public interface CommonDao{

    /**
     * 获取系统参数值
     * @return
     * @throws java.sql.SQLException
     */
    public String getSystemParameter(Map<String, Object> map) throws SQLException;


}
