package com.cy.dctms.orderCargo.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.OrderCargoInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.orderCargo.dao.IOrderCargoInfoDao;
import com.cy.dctms.orderCargo.service.IOrderCargoInfoService;

public class OrderCargoInfoServiceImp implements IOrderCargoInfoService {

	private IOrderCargoInfoDao orderCargoInfoDao;

	@Override
	public void queryOrderCargoInfoList(OrderCargoInfoDomain orderCargoInfoDomain) {
		orderCargoInfoDao.queryOrderCargoInfoList(orderCargoInfoDomain);
	}

	@Override
	public void exportOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain) {
		 orderCargoInfoDao.exportOrderCargoInfo(orderCargoInfoDomain);
	}

	@Override
	public OrderCargoInfoDomain queryOrderCargoInfoMxById(String id) {
		 return orderCargoInfoDao.queryOrderCargoInfoById(id);
	}

	@Override
	public void saveOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain ,String userId) {		
		orderCargoInfoDao.saveOrderCargoInfo(orderCargoInfoDomain,userId);
	}

	@Override
	public void deleteOrderCargoInfo(String id,String userId) {
		orderCargoInfoDao.deleteOrderCargoInfo(id,userId);
		
	}

	public void setOrderCargoInfoDao(IOrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}
}
