package com.cy.dctms.driverUserAssess.service;

import com.cy.dctms.common.domain.DriverUserAssessInfoDomain;

public interface IDriverUserAssessInfoService {
	
	/**
	 * 查询司机对企业评价信息列表
	 * @author:wjl
	 */
	public void queryDriverUserAssessInfoList(DriverUserAssessInfoDomain driverUserAssessInfoDomain);
	
	/**
	 * 导出司机对企业评价信息列表
	 * @author:wjl
	 */
	public void exportDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain);

	/**
	 * 查司机对企业评价信息明细根据ID
	 * @author:wjl
	 */
	public DriverUserAssessInfoDomain queryDriverUserAssessInfoMxById(String id);

	/**
	 * 保存司机对企业评价信息
	 * @author:wjl
	 */
	public void saveDriverUserAssessInfo(DriverUserAssessInfoDomain driverUserAssessInfoDomain ,String userId);

	/**
	 * 删除司机对企业评价信息
	 * @author:wjl
	 */
	public void deleteDriverUserAssessInfo(String id,String userId);
	
	
}
	
