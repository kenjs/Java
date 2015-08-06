package com.cy.swp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.DriverUserInfo;
import com.cy.swp.domain.DriverUserInfoDomain;

@Repository("driverUserInfoDao")
public interface DriverUserInfoDao {
	
	public List<DriverUserInfoDomain> selectDriverUserInfo(Map<String, Object> queryMap);
	
	/**
	 * 根据Id查询司机车辆
	 * @param id 司机Id
	 * @return
	 */
	DriverUserInfo queryDriverUserInfoById(String id);
	
	/**
	 * 根据导入货源匹配id获取司机信息
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryCarByAssisterId(String assistId);

	 /**
     * 货找车-不分页
     *
     * @param queryMap
     * @return
     */
    List<DriverUserInfoDomain> queryCargoMateDriverDomain(Map<String, Object> queryMap);

    /**
     * 货找车-分页
     *
     * @param queryMap
     * @return
     */
    List<DriverUserInfoDomain> queryCargoMateDriverDomainByPage(Map<String, Object> queryMap);

    /**
     * 某条货所匹配到的车的总记录数
     */
    Integer queryCargoMateDriverDomainCount(Map<String, Object> queryMap);

    
    /**
     * 预约线路（单向） & 预约时间
     * 
     * @author nxj
     */
    List<DriverUserInfoDomain> queryDriverMapPageSizeOne(Map<String, Object> queryMap);
    
    
    /**
     * 线路（双向） & 当前位置（24小时内
     * 
     * @author nxj
     * 
     */
    List<DriverUserInfoDomain> queryDriverMapPageSizeTow(Map<String, Object> queryMap);
    
    /**
     * 线路（双向）
     * 
     * @author nxj
     * 
     */
    List<DriverUserInfoDomain> queryDriverMapPageSizeThree(Map<String, Object> queryMap);
    
    
    /**
     * 当前位置（24小时内)
     * 
     * @author nxj
     * 
     */
    List<DriverUserInfoDomain> queryDriverMapPageSizeFour(Map<String, Object> queryMap);

    /**
     * 根据手机号码查询，计数司机运营线路条数，计数司机活跃记录表的条数
     * @param queryMap
     * @author wyh
     */
    DriverUserInfoDomain queryByPhone(Map<String, String> queryMap);


    /**
     *  修改司机信息（姓名、车牌号、车长、车板、车栏、运力（体积、重量））
     * @param driverUserInfo
     * @author nxj
     */
    public boolean updateDriverUserInfoSetDriverInfo(DriverUserInfo driverUserInfo);



    //根据id获取定位信息（24小时）
    public DriverUserInfo queryDriverUserInfoLocation(Integer driverId);


}
