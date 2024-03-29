package com.cy.dctms.orderCargoLast.service;

import java.util.List;

import com.cy.dctms.common.domain.OrderCargoLastInfoDomain;

public interface IOrderCargoLastInfoService {
	
	/**
	 * 查询货源历史状态信息列表
	 * @author:wjl
	 */
	public void queryOrderCargoLastInfoList(OrderCargoLastInfoDomain orderCargoLastInfoDomain);
	
	/**
	 * 导出货源历史状态信息列表
	 * @author:wjl
	 */
	public void exportOrderCargoLastInfo(OrderCargoLastInfoDomain orderCargoLastInfoDomain);

	
}
	
