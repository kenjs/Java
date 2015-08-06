package com.cy.driver.transaction.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.bo.TransactionLastInfoBo;
import com.cy.driver.transaction.dao.TransactionInfoDao;
import com.cy.driver.transaction.domain.TransactionInfoDomain;
import com.cy.driver.transaction.service.TransactionInfoService;
import com.cy.driver.webUser.domain.WebUserInfoDomain;
/**
 * 订单service impl
 * @date 2014-6-5
 * @author haoyong
 */
public class TransactionInfoServiceImpl implements TransactionInfoService {

	private TransactionInfoDao transactionInfoDao;

	public void setTransactionInfoDao(TransactionInfoDao transactionInfoDao) {
		this.transactionInfoDao = transactionInfoDao;
	}

	public int selectDriverOrderNumber(String driverId) {
		return transactionInfoDao.selectDriverOrderNumber(driverId);
	}

	@SuppressWarnings("unchecked")
	public List<?> selectDriverOrderList(String driverId,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize", listSize);
		List<TransactionInfoDomain> list = (List<TransactionInfoDomain>) transactionInfoDao.selectDriverOrderList(map);
		return list;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public int addTransactionLastInfo(TransactionLastInfoBo bo) {
		return transactionInfoDao.addTransactionLastInfo(bo);
	}

	public TransactionInfoDomain selectDriverOrderDetail(String id) {
		return transactionInfoDao.selectDriverOrderDetail(id);
	}

	public int updateTransactionInfoById(Map<String, Object> map) {		
		if(map.containsKey("tradeStart")) {
			if("6".equals(map.get("tradeStart").toString())) {
				map.put("remark", "司机确认取消用车");
				map.put("tradeCancelOrigin", "1");
			}
		}
		return transactionInfoDao.updateTransactionInfoById(map);
	}

	public List<TransactionInfoDomain> selectTransactionByShipperOrderNo(
			Map<String, String> map) throws Exception {
		return transactionInfoDao.selectTransactionByShipperOrderNo(map);
	}

	public List<TransactionInfoDomain> selectDoneCargoList(Map<String,Object> map) {
		return transactionInfoDao.selectDoneCargoList(map);
	}

	public int selectMyOrderNumber(String driverId) {
		return transactionInfoDao.selectMyOrderNumber(driverId);
	}

	public Map<String,Long> selectTransactionStartById(String id) {
		return transactionInfoDao.selectTransactionStartById(id);
	}
	
	@Override
	public WebUserInfoDomain selectShipperCompanyId(String deployUserId,
			String shipperCompanyCode) throws Exception {		
		Map<String,String> map = new HashMap<String,String>();
		map.put("deployUserId", deployUserId);
		map.put("shipperCompanyCode", shipperCompanyCode);
		return transactionInfoDao.selectShipperCompanyId(map);
	}

}
