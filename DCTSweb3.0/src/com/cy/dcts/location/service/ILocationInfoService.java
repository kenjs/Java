package com.cy.dcts.location.service;

import java.util.List;

import com.cy.dcts.common.bo.LocationCollectLastInfo;
import com.cy.dcts.common.domain.LocationCollectInfoDomain;
import com.cy.dcts.common.domain.LocationCollectLastInfoDomain;

public interface ILocationInfoService {
	/**
	 * 根据DriverId查询司机的历史位置
	 * @return
	 */
	List<LocationCollectInfoDomain> queryLocationCollectInfoByDriverId(LocationCollectInfoDomain locationCollectInfoDomain);
	

	/**
	 * 根据DriverId查询司机的当前位置 返回bo
	 * @return
	 */
	LocationCollectLastInfo queryLocationCollectLastByDriverId(String driverId);
	
	/**
	 * 根据DriverId查询司机的当前位置 返回Domain
	 * @return LocationCollectLastInfoDomain
	 */
	LocationCollectLastInfoDomain queryLocationCollectLastDomainByDriverId(String driverId);
}
