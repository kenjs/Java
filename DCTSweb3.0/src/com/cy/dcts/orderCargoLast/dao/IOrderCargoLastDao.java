package com.cy.dcts.orderCargoLast.dao;

import java.util.Map;

public interface IOrderCargoLastDao {
	/**
	 * 增加货源状态历史记录（日志：有增无减）
	 * @param orderCargoLastInfo
	 * @return
	 */
	String addOrderCargoLastInfo(Map<String, Object> addMap);
}
