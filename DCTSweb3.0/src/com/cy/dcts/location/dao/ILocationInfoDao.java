package com.cy.dcts.location.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.LocationCollectInfo;
import com.cy.dcts.common.bo.LocationCollectLastInfo;
import com.cy.dcts.common.domain.LocationCollectInfoDomain;
import com.cy.dcts.common.domain.LocationCollectLastInfoDomain;

public interface ILocationInfoDao {
	/**
	 * 根据DriverId查询司机的历史位置-分页
	 * @return
	 */
	List<LocationCollectInfoDomain> queryLocationCollectInfoByDriverIdByPage(Map<String, Object> queryMap);
	
	
	/**
	 * 根据DriverId查询司机的历史位置-不分页
	 * @return
	 */
	List<LocationCollectInfoDomain> queryLocationCollectInfoByDriverId(Map<String, Object> queryMap);
	
	/**
	 * 根据DriverId查询司机的历史位置总记录数
	 * @param locationCollectInfoDomain
	 * @return
	 */
	Integer queryLocationCollectInfoByDriverIdCount(Map<String, Object> queryMap);
	/**
	 * 根据DriverId查询司机的当前位置 返回bo
	 * @return LocationCollectLastInfo
	 */
	LocationCollectLastInfo queryLocationCollectLastByDriverId(String driverId);
	
	/**
	 * 根据DriverId查询司机的当前位置 返回Domain
	 * @return LocationCollectLastInfoDomain
	 */
	LocationCollectLastInfoDomain queryLocationCollectLastDomainByDriverId(String driverId);
}
