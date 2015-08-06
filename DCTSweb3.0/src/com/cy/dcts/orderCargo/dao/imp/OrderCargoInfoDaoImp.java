package com.cy.dcts.orderCargo.dao.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.QuoteInfoDomain;
import com.cy.dcts.orderCargo.dao.IOrderCargoInfoDao;

public class OrderCargoInfoDaoImp extends BaseDao implements IOrderCargoInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public boolean modifyImportOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		try {
			return this.saveObject("update_import_cargo_info", orderCargoInfo)==1;
		} catch (Exception e) {
			logger.warn("update_import_cargo_info error",e);
			throw new RuntimeException();
		}
	}
	public OrderCargoInfo queryOrderCargoInfoById(String id){
		try {
			return (OrderCargoInfo)this.queryForObject("query_orderCargo_info_byId", id);
		} catch (Exception e) {
			logger.warn("query_orderCargo_info_byId error", e);
			throw new RuntimeException();
		}
	}
	
	public OrderCargoInfoDomain queryOrderCargoInfoDomainById(String id){
		try {
			return (OrderCargoInfoDomain)this.queryForObject("query_orderCargo_domain_info_byId", id);
		} catch (Exception e) {
			logger.warn("query_orderCargo_info_byId error", e);
			throw new RuntimeException();
		}
	}
	
	public String addOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		try {
			return addObjectKeyString("insert_order_cargo_info", orderCargoInfo);
		} catch (Exception e) {
			logger.warn("insert_order_cargo_info error", e);
			throw new RuntimeException();
		}
		
	}

	public boolean modifyOrderCargoInfo(OrderCargoInfo orderCargoInfo) {
		try {
			return this.saveObject("update_order_cargo_info", orderCargoInfo)==1;
		} catch (Exception e) {
			logger.warn("update_order_cargo_info error",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyOrderCargoFlag(Map<String, Object> modifyMap) {
		try {
			return this.saveObject("update_order_cargo_flag", modifyMap)==1;
		} catch (Exception e) {
			logger.warn("update_order_cargo_flag error",e);
			throw new RuntimeException();
		}
	}

	
	public boolean modifyOrderDeleteFlag(Map<String, Object> modifyMap) {
		try {
			return this.saveObject("update_order_deleted_flag", modifyMap)==1;
		} catch (Exception e) {
			logger.warn("update_order_deleted_flag error",e);
			throw new RuntimeException();
		}
	}
	
	public List<OrderCargoInfoDomain> queryOrderCargoInfoDomain(
			Map<String, Object> queryMap) {
		try{
			return (List<OrderCargoInfoDomain>)queryForList("query_orderCargoInfo_domain",queryMap);
		}catch(Exception e) {
			logger.warn("query_orderCargoInfo_domain error.", e);
			throw new RuntimeException();
		}
	}
	
	public List<OrderCargoInfoDomain> queryOrderCargoInfoByPage(Map<String, Object> queryMap) {
		try{
			return (List<OrderCargoInfoDomain>)queryForList("query_orderCargoInfo_domain_by_page",queryMap);
		}catch(Exception e) {
			logger.warn("query_orderCargoInfo_domain_by_page error.", e);
			throw new RuntimeException();
		}
	}
	
	public Integer queryOrderCargoInfoCount(Map<String, Object> queryMap) {
		try{
			return (Integer) queryForObject("query_orderCargoInfo_domain_count", queryMap);
		}catch(Exception e) {
			logger.warn("query_orderCargoInfo_domain_count error.", e);
			throw new RuntimeException();
		}
	}

	public List<OrderCargoInfoDomain> queryStartDeployOrderCargoInfoByPage(Map<String, Object> queryMap) {
		try{
			return (List<OrderCargoInfoDomain>)queryForList("query_start_deploy_orderCargoInfo_domain",queryMap);
		}catch(Exception e) {
			logger.warn("query_start_deploy_orderCargoInfo_domain error.", e);
			throw new RuntimeException();
		}
	}
	
	public Integer queryStartDeployOrderCargoInfoByPageCount(Map<String, Object> queryMap) {
		try{
			return (Integer) queryForObject("query_start_deploy_orderCargoInfo_domain_count",queryMap);
		}catch(Exception e) {
			logger.warn("query_start_deploy_orderCargoInfo_domain_count error.", e);
			throw new RuntimeException();
		}
	}

	public Integer queryOrderCargoCount(Map<String, Object> queryMap) {
		try{
			return (Integer) queryForObject("query_orderCargoInfo_by_time_count", queryMap);
		}catch(Exception e) {
			logger.warn("query_orderCargoInfo_by_time_count error.", e);
			throw new RuntimeException();
		}
	}

	public List<OrderCargoInfoDomain> queryOrderCargoQuoteInfoList(
			Map<String, Object> queryMap) {
		try{
			return (List<OrderCargoInfoDomain>)queryForList("query_orderCargoInfo_quote_domain_list",queryMap);
		}catch(Exception e) {
			logger.warn("query_orderCargoInfo_quote_domain_list error.", e);
			throw new RuntimeException();
		}
	}

	public List<QuoteInfoDomain> queryQuoteInfoList(Map<String, Object> queryMap) {
		try{
			return (List<QuoteInfoDomain>)queryForList("query_quote_domain_list",queryMap);
		}catch(Exception e) {
			logger.warn("query_quote_domain_list error.", e);
			throw new RuntimeException();
		}
	}

}
