
package com.cy.dcts.driverCar.dao;


import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.DriverUserInfo;
import com.cy.dcts.common.bo.UserDriverInfo;
import com.cy.dcts.common.domain.DriverUserInfoDomain;

public interface IDriverUserCarInfoDao {
	
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
	List<DriverUserInfoDomain> queryTodayDynamicDriverCarByTime(String deleteFlag);
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
	boolean modifyDriverCarDeleteFlagByID(Map <String, Object> queryMap);
	/**
	 * 查询我的常用车辆-不分页
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryDriverUserInfoDomain(Map<String,Object> queryMap);
	
	/**
	 * 查询我的常用车辆-分页
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryDriverUserInfoDomainByPage(Map<String,Object> queryMap);
	/**
	 *  查询我的常用车辆的总记录数
	 */
	Integer queryDriverUserInfoDomainCount(Map<String,Object> queryMap);
	
	/**
	 * 司机 报价-不分页
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryDriverQuoteInfoByCargoId(Map<String,Object> queryMap);
	
	/**
	 * 司机 报价-分页
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryDriverQuoteInfoByCargoIdPage(Map<String,Object> queryMap);
	
	/**
	 * 司机 报价记录数
	 * @param queryMap
	 * @return
	 */
	Integer queryDriverQuoteInfoByCargoIdCount(Map<String,Object> queryMap);
	
	
	/**
	 * 首页当前车源分页查询
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryRealDriverCarByPage(Map<String,Object> queryMap);
	
	/**
	 * 首页当前车源分页查询(总数量)
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	Integer queryRealDriverCarByPageCount(Map<String,Object> queryMap);
	
	
	
	/**
	 * 首页当前车源查询所有
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryRealDriverCarByPageList(Map<String,Object> queryMap);
	
	
	/**
	 * 车源查询所有(订车)
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryRealDriverCarByPageListcount(Map<String,Object> queryMap);
	
	
	/**
	 * 根据车源id查询详情(订车)
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	public List<DriverUserInfoDomain> queryRealDriverCarByPageListcountById(Map<String,Object> queryMap);
	
	/**
	 * 司机数量
	 * 每日新增
	 * 或者
	 * 总数量
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	Integer queryDriverCarCount(Map<String,Object> queryMap);
	
	/**
	 * 收藏司机
	 * @param userDriverInfo
	 * @return
	 */
	String addUserDriver(Map<String,Object> queryMap);
	
	/**
	 * 根据Id查询收藏的司机
	 * @param driverId
	 * @return
	 */
	List<UserDriverInfo> queryUserDriverInfoByDriverID(Map<String,Object> queryMap);
	
	
	/**
	 * 推荐车源
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	List<DriverUserInfoDomain> queryRealDriverByPage(Map<String,Object> queryMap);
	
}
