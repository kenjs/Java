package com.cy.dctms.orderCargo.dao;

import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.OrderCargoInfoDomain;

public interface IOrderCargoInfoDao {

	/**
	 * 查询货物信息信息列表
	 * @author:wjl
	 */
	public void queryOrderCargoInfoList(OrderCargoInfoDomain orderCargoInfoDomain);

	/**
	 * 导出货物信息信息列表
	 * @author:wjl
	 */
	public void exportOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain);

	/**
	 * 查货物信息信息明细根据ID
	 * @author:wjl
	 */
	public OrderCargoInfoDomain queryOrderCargoInfoById(String id);	

	/**
	 * 保存货物信息信息
	 * @author:wjl
	 */
	public void saveOrderCargoInfo(OrderCargoInfoDomain orderCargoInfoDomain,String userId);

	/**
	 * 删除货物信息信息
	 * @author:wjl
	 */
	public void deleteOrderCargoInfo(String id,String userId);
}
