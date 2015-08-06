package com.cy.driver.order.dao;

import java.util.List;

import com.cy.common.bo.DriverBusinessLineInfo;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;

/**
 * 预约dao
 * 2014-5-27
 * @author haoyong
 *
 */
public interface DriverBusinessLineInfoDao {

	/**
	 * 新增预约
	 * @param bo
	 * @return
	 */
	public int insertDriverBusinessLineInfo(DriverBusinessLineInfo bo);
	
	/**
	 * 修改预约
	 * @param bo
	 * @return
	 */
	public int updateDriverBusinessLineInfo(DriverBusinessLineInfo bo);
	
	/**
	 * 删除预约
	 * @param id
	 * @return
	 */
	public int deleteDriverBusinessLineInfo(String id);
	
	/**
	 * 预约查询
	 * @param map
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> selectDriverBusinessLineInfoList(String driverId);
	
	/**
	 * 检索司机预约数目
	 * @param driverId
	 * @return
	 */
	public int selectDriverBusinessLineInfoCount(String driverId);
}
