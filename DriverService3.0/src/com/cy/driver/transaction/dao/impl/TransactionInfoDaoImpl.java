package com.cy.driver.transaction.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cy.common.bo.TransactionLastInfoBo;
import com.cy.common.dao.BaseDao;
import com.cy.driver.transaction.dao.TransactionInfoDao;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
import com.cy.driver.webUser.domain.WebUserInfoDomain;

public class TransactionInfoDaoImpl extends BaseDao implements TransactionInfoDao {

	public int selectDriverOrderNumber(String driverId) {
		int i = 0;
		try {
			i = (Integer) queryForObject("iBatisSelectDriverOrderNumber", driverId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<?> selectDriverOrderList(Map<String,Object> map) {
		List<TransactionInfoDomain> list = null;
		try {
			list = (List<TransactionInfoDomain>) queryForList("iBatisSelectDriverOrderList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addTransactionLastInfo(TransactionLastInfoBo bo) {
		int key = 0;
		try {
			key = addObject("iBatisInsertTransactionLastInfo", bo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

	public TransactionInfoDomain selectDriverOrderDetail(String id) {
		TransactionInfoDomain domain = null;
		try {
			domain = (TransactionInfoDomain) queryForObject("iBatisSelectDriverOrderById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domain;
	}

	public int updateTransactionInfoById(Map<String, Object> map) {
		int i = 0;
		try {
			i = saveObject("iBatisUpdateTransactionInfoById", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionInfoDomain> selectDoneCargoList(Map<String,Object> map) {
		List<TransactionInfoDomain> list = null;
		try {
			list = (List<TransactionInfoDomain>) queryForList("iBatisSelectDoneCargoList", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int selectMyOrderNumber(String driverId) {
		int num = 0;
		try {
			num = (Integer) queryForObject("iBatisSelectMyOrderNumber", driverId);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return num;
	}

	@SuppressWarnings("unchecked")
	public Map<String,Long> selectTransactionStartById(String id) {		
		Map<String,Long> map = null;
		try {
			map = (Map<String, Long>) queryForObject("iBatisSelectTransactionStartById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionInfoDomain> selectTransactionByShipperOrderNo(
			Map<String, String> map) throws Exception {		
		return (List<TransactionInfoDomain>) queryForList("iBatisSelectTransactionByShipperOrderNo", map);
	}

	@Override
	public WebUserInfoDomain selectShipperCompanyId(Map<String,String> map) throws Exception {
		return (WebUserInfoDomain) queryForObject("iBatisSelectShipperCompanyId", map);
	}

}
