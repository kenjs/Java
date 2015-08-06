package com.cy.driver.line.service;

import java.util.List;

import com.cy.common.bo.DriverLineInfoBo;

/**
 * WEB司机线路service
 * @date 2014-6-3
 * @author haoyong
 *
 */
public interface DriverLineInfoService {
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
