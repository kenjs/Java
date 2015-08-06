package com.cy.dctms.driverUser.dao;

import com.cy.dctms.common.domain.DriverUserInfoDomain;

public interface IDriverUserInfoDao {

	/**
	 * 查询司机信息列表
	 * @author:wjl
	 */
	public void queryDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain);
	

	/**
	 * 审核司机信息列表
	 * @author:wjl
	 */
	public void auditDriverUserInfoList(DriverUserInfoDomain driverUserInfoDomain);
	
	/**
	 * 查询司机交易信息列表
	 * @author:wjl
	 */
	public void queryDriverUserTransactionInfoList(DriverUserInfoDomain driverUserInfoDomain);
	
	/**
	 * 查询司机存活率列表
	 * @author:wjl
	 */
	public void driverUserTotalDataList(DriverUserInfoDomain driverUserInfoDomain);
	
	/**
	 * 查司机信息明细根据ID
	 * @author:wjl
	 */
	public DriverUserInfoDomain queryDriverUserInfoById(String id);	
	
	/**
	 * 查司机信息明细根据Code
	 * @author:wjl
	 */
	public DriverUserInfoDomain queryDriverUserInfoByCode(String code);	

	/**
	 * 保存司机信息
	 * @author:wjl
	 */
	public void saveDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId);

	/**
	 * 删除司机信息
	 * @author:wjl
	 */
	public void deleteDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId);
	
	/**
	 * 审核司机信息
	 * @author:wjl
	 */
	public void auditDriverUserInfo(DriverUserInfoDomain driverUserInfoDomain,String userId);
}
