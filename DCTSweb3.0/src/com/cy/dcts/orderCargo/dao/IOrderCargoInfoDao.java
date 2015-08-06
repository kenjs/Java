package com.cy.dcts.orderCargo.dao;

import java.util.List;

import java.util.Map;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.QuoteInfoDomain;

public interface IOrderCargoInfoDao {
	
	/**
	 * 修改导入的订单货源信息
	 */
	boolean modifyImportOrderCargoInfo(OrderCargoInfo orderCargoInfo);
	
	/**
	 * 根据Id查询货源
	 * @param id 货源ID
	 * @return OrderCargoInfo
	 */
	OrderCargoInfo queryOrderCargoInfoById(String id);
	/**
	 * 根据Id查询货源
	 * @param id 货源ID
	 * @return OrderCargoInfoDomain
	 */
	OrderCargoInfoDomain queryOrderCargoInfoDomainById(String id);
	/**
	 * 新增自已公司货源信息
	 */
	String addOrderCargoInfo(OrderCargoInfo orderCargoInfo);
	
	/**
	 * 修改自已公司货源信息
	 */
	boolean modifyOrderCargoInfo(OrderCargoInfo orderCargoInfo);
	
	/**
	 * 修改自已公司货源状态--(货源状态-待交易-0 ,货源状态-交易成功-2)
	 */
	boolean modifyOrderCargoFlag(Map<String, Object> modifyMap);
    
	/**
	 * 删除自已公司货源--(-1删除，0未删除)
	 * @param modifyMap
	 * @return
	 */
	boolean modifyOrderDeleteFlag(Map<String, Object> modifyMap);
	
	/**
	 * 我的货源查询不分页
	 * @author nxj
	 * @param queryMap
	 * @return 
	 */
	List<OrderCargoInfoDomain> queryOrderCargoInfoDomain(Map<String, Object> queryMap);
	
	/**
	 * 我的货源查询分页
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	List<OrderCargoInfoDomain> queryOrderCargoInfoByPage(Map<String, Object> queryMap);
	
	/**
	 * query_orderCargoInfo_domain_count 查询我的货源的总记录数
	 * @param queryMap
	 * @return
	 */
	Integer queryOrderCargoInfoCount(Map<String, Object> queryMap);
	
	/**
	 * 主页货源查询
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	List<OrderCargoInfoDomain> queryStartDeployOrderCargoInfoByPage(Map<String, Object> queryMap);

	/**
	 * 货源数量
	 * 每日新增
	 * 或者
	 * 总数量
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	Integer queryOrderCargoCount(Map<String, Object> queryMap);
	
	/**
	 * 根据用户id取有效货源
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	List<OrderCargoInfoDomain> queryOrderCargoQuoteInfoList(Map<String, Object> queryMap);
	
	/**
	 * 根据货源ID查询报价列表信息
	 * @param queryMap
	 * @return
	 */
	List<QuoteInfoDomain> queryQuoteInfoList(Map<String, Object> queryMap);
	
	/**
	 * 更多车源信息总数
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	Integer queryStartDeployOrderCargoInfoByPageCount(Map<String, Object> queryMap);
}
