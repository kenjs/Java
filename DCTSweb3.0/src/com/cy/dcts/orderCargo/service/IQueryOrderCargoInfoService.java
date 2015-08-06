package com.cy.dcts.orderCargo.service;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.QuoteInfoDomain;

public interface IQueryOrderCargoInfoService {
	/**
	 * 根据Id查询货源
	 * @param id 货源ID
	 * @return OrderCargoInfoDomain 返回Domain
	 */
	OrderCargoInfoDomain queryOrderCargoInfoDomainById(String id);
	
	/**
	 * 根据Id查询货源
	 * @param id 货源ID
	 * @return OrderCargoInfo 返回bo
	 */
	OrderCargoInfo queryOrderCargoInfoById(String id);
	
	/**
	 * 我的货源分页
	 * @author nxj
	 * @param orderCargoInfoDomain
	 * @return
	 */
	List<OrderCargoInfoDomain> queryOrderCargoInfoPage(OrderCargoInfoDomain orderCargoInfoDomain);
	/**
	 * 主页最新货源查询
	 * @author nxj
	 * @param orderCargoInfoDomain
	 * @return
	 */
	List<OrderCargoInfoDomain> queryStartDeployOrderCargoInfoByPage(OrderCargoInfoDomain orderCargoInfoDomain);
	
	/**
	 * 货源数量
	 * 每日新增
	 * 或者
	 * 总数量
	 * @author nxj
	 * @param createTime
	 * @return
	 */
	Integer queryOrderCargoCount(String createTime);
	
	/**
	 * 根据用户id取有效货源
	 * @author nxj
	 * @param userId
	 * @return
	 */
	List<OrderCargoInfoDomain> queryOrderCargoQuoteInfoList(OrderCargoInfoDomain orderCargoInfoDomain);
	
	
	/**
	 * 根据货源ID查询报价列表信息
	 * @param quoteInfoDomain
	 * @return
	 */
	List<QuoteInfoDomain> queryQuoteInfoList(QuoteInfoDomain quoteInfoDomain);
	
}
