package com.cy.dcts.orderCargoLast.dao.imp;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.orderCargoLast.dao.IOrderCargoLastDao;

public class OrderCargoLastDaoImp extends BaseDao implements IOrderCargoLastDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String addOrderCargoLastInfo(Map<String, Object> addMap){
		try {
			return this.addObjectKeyString("isnert_order_cargo_last_info", addMap);
		} catch (Exception e) {
			logger.warn("isnert_order_cargo_last_info error", e);
			throw new RuntimeException();
		}
	}

}
