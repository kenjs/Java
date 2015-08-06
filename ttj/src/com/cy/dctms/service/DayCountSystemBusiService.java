package com.cy.dctms.service;

import java.sql.SQLException;
/**
 * @description 统计app下载，web注册等数目
 * 按天统计系统业务数据
 * @author 		haoy
 *
 */
public interface DayCountSystemBusiService {

	public void countSystemBusiData() throws SQLException;
}
