package com.cy.dctms.orderCargoLast.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.domain.OrderCargoLastInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.orderCargoLast.dao.IOrderCargoLastInfoDao;
import com.cy.dctms.orderCargoLast.service.IOrderCargoLastInfoService;

public class OrderCargoLastInfoServiceImp implements IOrderCargoLastInfoService {

	private IOrderCargoLastInfoDao orderCargoLastInfoDao;

	@Override
	public void queryOrderCargoLastInfoList(OrderCargoLastInfoDomain orderCargoLastInfoDomain) {
		orderCargoLastInfoDao.queryOrderCargoLastInfoList(orderCargoLastInfoDomain);
	}

	@Override
	public void exportOrderCargoLastInfo(OrderCargoLastInfoDomain orderCargoLastInfoDomain) {
		 orderCargoLastInfoDao.exportOrderCargoLastInfo(orderCargoLastInfoDomain);
	}

	public void setOrderCargoLastInfoDao(IOrderCargoLastInfoDao orderCargoLastInfoDao) {
		this.orderCargoLastInfoDao = orderCargoLastInfoDao;
	}
}
