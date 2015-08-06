package com.cy.driver.dao;

import com.cy.driver.bo.LocationCollectLastInfoBo;
import org.springframework.stereotype.Repository;

/**
 * 位置信息dao
 * @date 2014-6-6
 * @author haoyong
 *
 */
@Repository("locationLastInfoDao")
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
	 * 更新位置信息
	 * @param bo
	 */
	public void updateLastLocation(LocationCollectLastInfoBo bo);
}
