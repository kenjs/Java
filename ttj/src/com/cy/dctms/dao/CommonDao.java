package com.cy.dctms.dao;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

@Repository("commonDao")
public interface CommonDao {

	/**
	 * 获取系统参数值
	 * @return
	 * @throws java.sql.SQLException
	 */
	public String getSystemParameter(Map<String, Object> map) throws SQLException ;

    /**
     * 每十分钟检查发送验证码的用户是否
     * 注册成为用户
     * @return
     * @throws SQLException
     */
    public Object checkNotePerTenMinus() throws SQLException;

}
