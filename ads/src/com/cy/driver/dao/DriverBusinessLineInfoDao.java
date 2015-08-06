package com.cy.driver.dao;

import com.cy.driver.bo.DriverBusinessLineInfo;
import com.cy.driver.domain.DriverBusinessLineInfoDomain;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 预约dao
 * 2014-5-27
 * @author haoyong
 *
 */
@Repository("driverBusinessLineInfoDao")
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
	 * @param driverId
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
