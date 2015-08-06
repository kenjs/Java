package com.cy.dcts.driverLine.service;

import java.util.List;

import com.cy.dcts.common.bo.DriverLineInfo;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;

public interface IDriverCarLineService {

	/**
	 * 首页查询预约车源
	 * @author nxj
	 * @param driverBusinessLineInfoDomain
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> queryReturnDriverCar(DriverBusinessLineInfoDomain driverBusinessLineInfoDomain);
	
	
	
	/**
	 * 查询预约车源分页
	 * @author nxj
	 * @param driverBusinessLineInfoDomain
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> queryDriverLineByPage(DriverBusinessLineInfoDomain driverBusinessLineInfoDomain);
	
	
	/**
	 * 根据driverId查询车辆预约信息
	 * @author nxj
	 * @param driverId
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainByDriverId(String driverId);
	
	
	/**
	 * 根据driverId查询车辆运行线路信息
	 * @author nxj
	 * @param driverId
	 * @return
	 */
	public List<DriverLineInfo> queryDriverLineInfoByDriverId(String driverId);
}
