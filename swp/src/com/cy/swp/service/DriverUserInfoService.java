package com.cy.swp.service;

import java.util.List;
import java.util.Map;

import com.cy.swp.bo.DriverUserInfo;
import com.cy.swp.bo.MarketingUserInfo;
import com.cy.swp.domain.DriverUserInfoDomain;

public interface DriverUserInfoService {
	
	public List<DriverUserInfoDomain> selectDriverUserInfo(Map<String, Object> queryMap);
	
	/**
	 * 根据Id查询司机车辆
	 * @param id 司机Id
	 * @return
	 */
	DriverUserInfo queryDriverUserInfoById(String id);
	
    /**
     * 货找车(货匹配车源)
     *
     * @param queryMap
     * @return
     */
    List<DriverUserInfoDomain> queryCargoMateDriverDomainInfo(DriverUserInfoDomain driverUserInfoDomain,MarketingUserInfo marketingUserInfo);
    
    
    /**
     * 导入获取匹配车辆
     * 最大匹配出来3条
     * 
     * @author nxj
     */
    List<DriverUserInfoDomain> queryDriverMapList(Map<String, Object> queryMap);
    
    
    /**
	 * 根据导入货源匹配id获取司机信息
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryCarByAssisterId(String assistId);


    /**
     * 修改车辆信息
     * @param driverUserInfo
     * @author nxj
     */
    boolean updateDriverUserInfo(DriverUserInfo driverUserInfo);


    //根据id获取定位信息（24小时）
    public DriverUserInfo queryDriverUserInfoLocation(Integer driverId);
    

}
