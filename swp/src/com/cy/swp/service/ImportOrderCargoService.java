package com.cy.swp.service;

import java.util.List;

import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.domain.OrderCargoInfoDomain;

public interface ImportOrderCargoService {

	
	
	/**
	 * 货源导入
	 * @param list
	 * @throws Exception
	 */
	public List<OrderCargoInfo> batchAddOrderCargoInfo(List<OrderCargoInfoDomain> list,String id) throws Exception;
	
	
	public void sendNoteBetweenCompanyAndDriver(List<OrderCargoInfo> list) throws Exception;

}
