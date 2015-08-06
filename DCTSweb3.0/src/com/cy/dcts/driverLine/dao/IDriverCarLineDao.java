package com.cy.dcts.driverLine.dao;

import java.util.List;

import java.util.Map;

import com.cy.dcts.common.bo.DriverLineInfo;
import com.cy.dcts.common.domain.DriverBusinessLineInfoDomain;

public interface IDriverCarLineDao {
	
	/**
	 * 根据driverId查询车辆运行线路信息
	 * @author nxj
	 * @param driverId
	 * @return
	 */
	public List<DriverLineInfo> queryDriverLineInfoByDriverId(String driverId);
	
	
	/**
	 * 根据driverId查询车辆预约线路信息
	 * @author nxj
	 * @param driverId
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainByDriverId(String driverId);
	
	/**
	 * 查询所有有效的预约车源
	 * 根据预约线路得到预约车源信息
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainList(Map <String, Object> queryMap);

	/**
	 * 预约车源分页查询
	 * 根据预约线路得到预约车源信息
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> queryDriverBusinessLineInfoDomainByPage(Map <String, Object> queryMap);
	
	/**
	 * 预约车源总数量
	 * 根据预约线路来判断总数的
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	public Integer queryDriverBusinessLineInfoDomainByPageCount(Map <String, Object> queryMap);
}
