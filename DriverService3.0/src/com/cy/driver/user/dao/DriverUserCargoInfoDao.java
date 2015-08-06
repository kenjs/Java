package com.cy.driver.user.dao;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.user.domain.CompanyInfoDomain;
import com.cy.driver.user.domain.DriverUserInfoDomain;
/**
 * 货源信息dao
 * @date 2014-5-29
 * @author haoyong
 *
 */
public interface DriverUserCargoInfoDao {

	/**
	 * 查找司机预约线路
	 * @param driverId
	 * @return
	 */
	public List<DriverBusinessLineInfoDomain> selectDriverBusinessLineInfoByDriverId(String driverId);
	
	/**
	 * 符合预约的货物数量
	 * @param map
	 * @return
	 */
	public int selectSuitCargoCount(Map<String,Object> map);
	
	/**
	 * 附近的货源数量
	 * @param positioning
	 * @return
	 */
	public int selectNearByCargoCount(Map<String,Object> map);
	
	/**
	 * 根据登录手机号修改司机信息
	 * @param bo
	 * @return
	 */
	public int updateDriverUserInfo(DriverUserInfoBo bo);
	
	/**
	 * 查询司机最新地址
	 * @param driverId
	 * @return
	 */
	public Map<String,Object> selectDriverLastLocation(String driverId );
	
	/**
	 * 查询用户基本信息
	 * @param code
	 * @return
	 */
	public DriverUserInfoDomain selectUserBasicInfo(String driverId);
	
	/**
	 * 司机个人消息查询
	 * @param id
	 * @return
	 */
	public List<?> queryDriverNotificationInfo(Map<String,Object> map);
	
	/**
	 * 查询公司信息
	 * @param id
	 * @return
	 */
	public CompanyInfoDomain selectConpanyInfoById(String id);
}
