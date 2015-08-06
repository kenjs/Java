package com.cy.dcts.orderCargo.service;

import java.util.List;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.bo.WebUserInfo;

public interface ISaveOrderCargoInfoService {
	/**
	 * 修改导入的订单货源信息
	 */
	boolean modifyImportOrderCargoInfo(OrderCargoInfo orderCargoInfo);
	
	/**
	 * 新增自已公司货源信息
	 */
	String addOrderCargoInfo(OrderCargoInfo orderCargoInfo, WebUserInfo userInfo);
	
	/**
	 * 修改保存自已公司货源信息
	 */
	boolean modifyOrderCargoInfo(OrderCargoInfo orderCargoInfo, WebUserInfo userInfo);
	
	/**
	 * 修改自已公司货源状态--(货源状态-待交易-0 ,货源状态-交易成功-2)
	 * @param orderId 货源Id
	 * @param webId 用户Id
	 * @param cargoFlag 货源状态
	 * @return
	 */
	boolean modifyOrderCargoFlag(String orderId, String webId,String cargoFlag);
	
	/**
	 * 修改货源的删除状态
	 * @param orderId 货源Id
	 * @param webId  用户Id
	 * @param deleteFlag 删除状态 
	 * @return
	 */
	boolean modifyOrderDeleteFlag(String orderId, String webId,int deleteFlag);
	

	/**
	 * 货源导入
	 * @param list
	 * @throws Exception
	 */
	public List<String> batchAddOrderCargoInfo(List<OrderCargoInfo> list,WebUserInfo userInfo) throws Exception;
}
