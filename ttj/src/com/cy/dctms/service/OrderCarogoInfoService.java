package com.cy.dctms.service;

import java.sql.SQLException;
/**
 * @descroption 定期清理过期货源
 * @author 		haoy
 *
 */
public interface OrderCarogoInfoService {

	/**
	 * 开始任务
	 * @throws SQLException
	 */
	public void updateOrderCargo() throws SQLException;
}
