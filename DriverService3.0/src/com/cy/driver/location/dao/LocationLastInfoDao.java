package com.cy.driver.location.dao;

import com.cy.common.bo.LocationCollectLastInfoBo;

/**
 * 位置信息dao
 * @date 2014-6-6
 * @author haoyong
 *
 */
public interface LocationLastInfoDao {

	/**
	 * 最新位置信息上传
	 * @param bo
	 * @return
	 */
	public int insertLastLocation(LocationCollectLastInfoBo bo);
	
	/**
	 * 位置信息上传
	 * @param bo
	 * @return
	 */
	public int insertLocation(LocationCollectLastInfoBo bo);
	
	/**
	 * 查看司机是否已经上传位置信
	 * @param driverId
	 * @return
	 */
	public int checkLocationExist(String driverId);
	
	/**
	 * 跟新位置信息
	 * @param bo
	 */
	public void updateLastLocation(LocationCollectLastInfoBo bo);
}
