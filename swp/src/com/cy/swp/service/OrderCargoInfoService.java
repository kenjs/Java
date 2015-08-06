package com.cy.swp.service;

import java.util.List;
import java.util.Map;

import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.domain.OrderCargoInfoDomain;

public interface OrderCargoInfoService {

	public Integer addOrderCargoInfo(OrderCargoInfo orderCargoInfo);
	
	/**
     * 根据Id查询货源
     *
     * @param id 货源ID
     * @return OrderCargoInfo
     */
    OrderCargoInfo queryOrderCargoInfoById(String id);
	
	/**
	 * 查询今天导入货源总记录数
	 * @param userId
	 * @return
	 */
	Integer queryTodayImportCargoCount(String userId,String deletedFlag);
	
	/**
	 * 查询今天导入动态
	 * @param queryMap 查询的参数Map
	 * @return 
	 */
	public List<OrderCargoInfoDomain> queryTodayImportInfo(String userId,OrderCargoInfoDomain orderCargoInfoDomain);
	
	/**
	 * 根据导入的联系人号码查询该人的货源 
	 * @param queryMap  查询的参数Map
	 * @return
	 */
	List<OrderCargoInfoDomain> queryTodayImportCargoInfoByPhone(String userId,OrderCargoInfoDomain orderCargoInfoDomain);
	
	/**
	 * 根据公司联系人的号码删除导入的货源（即修改状态）
	 * @param queryMap
	 * @return
	 */
	boolean modifyDeletedFlagByPhone(String contactMobilephone,String userId);
	/**
	 * 根据Id修改删除状态
	 * @param queryMap
	 * @return
	 */
	boolean modifyOrderDeleteFlagById(String id,String userId);


	/**
	 * 营销平台客户管理车辆匹配货源
	 *
	 * @param hsql（动态sql语句）
	 * @auothr nxj
	 */
	/**
	 * 营销平台客户管理车辆匹配货源
	 * 未安装：
	 1.有效预约线路（条件：预约时间、省、市）单向
	 2.经营线路 双向
	 3.常跑城市 （默认为发货地）

	 已安装：
	 1.有效预约线路（条件：预约时间、省、市）单向
	 2.经营线路 双向
	 3.当前位置（24小时有效，默认为发货地）
	 4.常跑城市 （默认为发货地）
	 * @param id  客户司机id
	 * @param start 客户安装状态（N表示未安装，Y表示已安装）
	 * @return
	 */
	List<OrderCargoInfoDomain> queryOrderCargoInfoDomainDriverInfoHsql(String id,String start);


	/**
	 * 客户管理手动匹配货源封装货源
	 * @param list
	 * @return
	 */
	public String getOrderCargoInfoString(List<OrderCargoInfoDomain> list,String start);
}
