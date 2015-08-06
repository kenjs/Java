package com.cy.dcts.driverCar.service;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.UserDriverInfo;
import com.cy.dcts.common.domain.DriverUserInfoDomain;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;

public interface IDriverUserCarInfoService {
	/**
	 * 根据司机手机号查询司机 
	 * @param code 司机手机号
	 * @return 司机车辆对象
	 */
	DriverUserInfo queryDriverInfoByCode(String code);
	
	/**
	 * 今日动态 没有交易时再查询今日新增司机
	 * @param deleteFlag 删除标志
	 * @return
	 */
	List<DriverUserInfoDomain> queryTodayDynamicDriverCarByTime();
	
	/**
	 * 根据司机车辆Id查询司机及车辆信息
	 * @param id 司机车辆ID
	 * @return
	 */
	DriverUserInfoDomain queryDriverUserInfoDomainById(String id);
	/**
	 * 删除车源（修改删除状态）
	 * @param queryMap
	 * @return
	 */
	boolean modifyDriverCarDeleteFlagByID(String id);
	/**
	 * 查询我的常用车辆
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryDriverUserInfoDomain(DriverUserInfoDomain driverUserInfoDomain,String userId);
	
	/**
	 * 司机 报价
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryDriverQuoteInfoByCargoId(DriverUserInfoDomain driverUserInfoDomain);
	
	
	/**
	 * 首页当前车源查询
	 * @author nxj
	 * @param driverUserInfoDomain
	 * @return
	 */
	List<DriverUserInfoDomain> queryRealDriverCarByPage(DriverUserInfoDomain driverUserInfoDomain);
	
	
	/**
	 * 首页当前车源更多车源查询
	 * @author nxj
	 * @param driverUserInfoDomain
	 * @return
	 */
	List<DriverUserInfoDomain> queryRealMoreDriverCarByPage(DriverUserInfoDomain driverUserInfoDomain);
	
	/**
	 * 司机数量
	 * 每日新增
	 * 或者
	 * 总数量
	 * @author nxj
	 * @param createTime
	 * @return
	 */
	Integer queryDriverCarCount(String createTime);
	
	/**
	 * 收藏司机
	 * @param userDriverInfo
	 * @return
	 */
	String addUserDriver(String driverId,String userId);
	
	/**
	 * 根据Id查询收藏的司机
	 * @author nxj
	 * @param driverId
	 * @return
	 */
	List<UserDriverInfo> queryUserDriverInfoByDriverID(String driverId,String userId);
	
	
	/**
	 * 查询订车
	 * @author nxj
	 * @param driverId
	 * @return
	 */
	List<DriverUserInfoDomain> queryDriverTheCarInfo(DriverUserInfoDomain driverUserInfoDomain,String linetypeset);
	
	/**
	 * 推荐车源
	 * @author nxj
	 * @param 
	 * @return
	 */
	List<DriverUserInfoDomain> queryRealDriver(DriverUserInfoDomain driverUserInfoDomain);
	
}
