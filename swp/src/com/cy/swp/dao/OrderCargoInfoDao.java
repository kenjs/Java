package com.cy.swp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.OrderCargoInfo;
import com.cy.swp.domain.OrderCargoInfoDomain;

@Repository("orderCargoInfoDao")
public interface OrderCargoInfoDao {

	public Integer addOrderCargoInfo(OrderCargoInfo orderCargoInfo);
	
	/**
	 * 查询今天导入货源总记录数
	 * @param queryMap 查询的参数Map
	 * @return
	 */
	Integer queryTodayImportCargoCount(Map<String, Object> queryMap);
	
	/**
     * 根据Id查询货源
     *
     * @param id 货源ID
     * @return OrderCargoInfo
     */
    OrderCargoInfo queryOrderCargoInfoById(String id);
	
	/**
	 * 查询今天导入动态-分页
	 * @param queryMap 查询的参数Map
	 * @return 
	 */
	public List<OrderCargoInfoDomain> queryTodayImportInfoByPage(Map<String, Object> queryMap);
	
	/**
	 * 查询今天导入动态-不分页
	 * @param queryMap 查询的参数Map
	 * @return 
	 */
	public List<OrderCargoInfoDomain> queryTodayImportInfo(Map<String, Object> queryMap);
	
	/**
	 * 查询今天导入动态-总记录数
	 * @param queryMap 查询的参数Map
	 * @return
	 */
	Integer queryTodayImportInfoCount(Map<String, Object> queryMap);
	
	/**
	 * 根据导入的联系人号码查询该人的货源 
	 * @param queryMap  查询的参数Map
	 * @return
	 */
	List<OrderCargoInfoDomain> queryTodayImportCargoInfoByPhone(Map<String, Object> queryMap);
	
	/**
	 * 根据公司联系人的号码删除导入的货源（即修改状态）
	 * @param queryMap
	 * @return
	 */
	boolean modifyDeletedFlagByPhone(Map<String, Object> queryMap);
	/**
	 * 根据Id修改删除状态
	 * @param queryMap
	 * @return
	 */
	boolean modifyOrderDeleteFlagById(Map<String, Object> queryMap);


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
	 * @param map（动态sql语句）
	 * @auothr nxj
	 */
	List<OrderCargoInfoDomain> queryOrderCargoInfoDomainDriverInfoHsql(Map<String,String> map);
}
