package com.cy.driver.user.service;

import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.driver.user.domain.CompanyInfoDomain;
import com.cy.driver.user.domain.DriverUserInfoDomain;


/**
 * 货源信息service
 * @date 2014-5-29
 * @author haoyong
 *
 */
public interface DriverUserCargoInfoService {
	/**
	 * 符合预约的货物数量
	 * @param code
	 * @return
	 */
	public int selectSuitCargoCount(String driverId);
	
	/**
	 * 附近的货源数量
	 * @param positioning
	 * @return
	 */
	public int selectNearByCargoCount(String driverId);
	
	/**
	 * 根据登录手机号修改Driver信息
	 * @param bo
	 * @return
	 */
	public int updateDriverUserInfo(DriverUserInfoBo bo);
	
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
	public List<?> queryDriverNotificationInfo(String driverId,String fromSize,String listSize);
	
	/**
	 * 短信发送
	 * @param phoneNumber
	 * @param content
	 * @return
	 */
	public String noteSend(String phoneNumber,String content);
	
	/**
	 * 查询公司信息
	 * @param id
	 * @return
	 */
	public CompanyInfoDomain selectConpanyInfoById(String id);
	
	/**
	 * 货源提醒
	 * @param map
	 * @return
	 */
	public String cargoInfoRemind(Map<String,String> map);
}
