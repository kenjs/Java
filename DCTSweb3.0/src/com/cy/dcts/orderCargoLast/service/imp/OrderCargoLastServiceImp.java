package com.cy.dcts.orderCargoLast.service.imp;

import java.util.HashMap;
import java.util.Map;

import com.cy.dcts.common.bo.OrderCargoLastInfo;
import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.orderCargoLast.dao.IOrderCargoLastDao;
import com.cy.dcts.orderCargoLast.service.IOrderCargoLastService;


public class OrderCargoLastServiceImp implements IOrderCargoLastService{
    private IOrderCargoLastDao orderCargoLastDao;
	public String addOrderCargoLastInfo(TransactionInfo transactionInfo,String stateType) {
		Map<String, Object> addMap=new HashMap<String, Object>();
		if(transactionInfo!=null){
			addMap.put("cargoId", transactionInfo.getCargoId());
			addMap.put("driverId", transactionInfo.getDriverId());
			addMap.put("stateType", stateType);//货源状态类型
			addMap.put("remark", transactionInfo.getRemark());
		}
		return orderCargoLastDao.addOrderCargoLastInfo(addMap);
	}
	
	public IOrderCargoLastDao getOrderCargoLastDao() {
		return orderCargoLastDao;
	}
	public void setOrderCargoLastDao(IOrderCargoLastDao orderCargoLastDao) {
		this.orderCargoLastDao = orderCargoLastDao;
	}
    
}
