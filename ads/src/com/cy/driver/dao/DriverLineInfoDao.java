package com.cy.driver.dao;

import com.cy.driver.bo.DriverLineInfoBo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WEB司机线路dao
 * @date 2014-6-3
 * @author haoyong
 *
 */
@Repository("driverLineInfoDao")
public interface DriverLineInfoDao {
	/**
	 * 新增司机线路
	 * @param bo
	 * @return
	 */
	public int insertDriverLineInfo(DriverLineInfoBo bo);
	/**
	 * 修改司机线路
	 * @param bo
	 * @return
	 */
	public int updateDriverLineInfo(DriverLineInfoBo bo);
	/**
	 * 删除司机线路
	 * @param id
	 * @return
	 */
	public int deleteDriverLineInfo(String id);
	/**
	 * 查询司机线路
	 * @param driverId
	 * @return
	 */
	public List<?> selectDriverLineInfoList(String driverId);
	
	/**
	 * 检索司机线路数目
	 * @param driverId
	 * @return
	 */
	public int selectDriverLineInfoCount(String driverId);
}
