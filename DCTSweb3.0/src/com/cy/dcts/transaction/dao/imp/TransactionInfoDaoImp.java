package com.cy.dcts.transaction.dao.imp;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.TransactionInfo;
import com.cy.dcts.common.bo.TransactionReceiptPath;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.common.domain.TransactionInfoDomain;
import com.cy.dcts.transaction.dao.ITransactionInfoDao;

public class TransactionInfoDaoImp extends BaseDao implements ITransactionInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String addTransactionInfos(TransactionInfo transactionInfo) {
		try {
			return addObjectKeyString("insert_transaction_infos", transactionInfo);
		} catch (Exception e) {
			logger.warn("insert_transaction_infos error", e);
			throw new RuntimeException();
		}
	}

	
	public TransactionInfoDomain queryImportTransactionInfoById(String id) {
		try {
			return (TransactionInfoDomain)this.queryForObject("query_import_transaction_info_byId",id);
		} catch (Exception e) {
			logger.debug("query_import_transaction_info_byId error",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyImportTransactionInfo(
			TransactionInfoDomain transactionInfoDomain) {
		try {
			return this.saveObject("update_import_transaction_info", transactionInfoDomain)==1;
		} catch (Exception e) {
			logger.debug("update_import_transaction_info error",e);
			throw new RuntimeException();
		}
	}
	
	public boolean modifyDeliveryOrArrivalTime(
			TransactionInfoDomain transactionInfoDomain) {
		
		try {
			return this.saveObject("update_transaction_deliveryOrArrival_time", transactionInfoDomain)==1;
		} catch (Exception e) {
			logger.debug("update_transaction_deliveryOrArrival_time error",e);
			throw new RuntimeException();
		}
	}
	
	public List<TransactionInfoDomain> queryTransactionInfoDomain(
			Map<String, Object> queryMap) {
		try {
			return (List<TransactionInfoDomain>)this.queryForList("query_transaction_info_domain",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_info_domain error",e);
			throw new RuntimeException();
		}
	}

	public List<TransactionInfoDomain> queryTransactionInfoDomainByPage(
			Map<String, Object> queryMap) {
		try {
			return (List<TransactionInfoDomain>)this.queryForList("query_transaction_info_domain_byPage",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_info_domain_byPage error",e);
			throw new RuntimeException();
		}
	}

	public List<TransactionInfoDomain> queryTransactionInfoDomainByPage2(
			Map<String, Object> queryMap) {
		try {
			return (List<TransactionInfoDomain>)this.queryForList("query_transaction_info_domain_byPage2",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_info_domain_byPage error",e);
			throw new RuntimeException();
		}
	}
	
	public Integer queryTransactionInfoDomainCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_transaction_info_domain_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_info_domain_count error",e);
			throw new RuntimeException();
		}
	}
	
	public Integer queryTransactionInfoDomainCount2(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_transaction_info_domain_count2",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_info_domain_count error",e);
			throw new RuntimeException();
		}
	}
	
	public boolean modifyTransactionInfoOrderStart(TransactionInfoDomain transactionInfoDomain) {
		try {
			return this.saveObject("update_transaction_info_order_start", transactionInfoDomain)==1;
		} catch (Exception e) {
			logger.debug("update_transaction_info_order_start error",e);
			throw new RuntimeException();
		}
	}

	public boolean modifyTransactionInfoTradeStart(
			TransactionInfoDomain transactionInfoDomain) {
		try {
			return this.saveObject("update_transaction_info_trade_start", transactionInfoDomain)==1;
		} catch (Exception e) {
			logger.debug("update_transaction_info_trade_start error",e);
			throw new RuntimeException();
		}
	}
	public TransactionInfo queryTransactionInfoById(String id) {
		try {
			return (TransactionInfo)this.queryForObject("query_transaction_info_byId",id);
		} catch (Exception e) {
			logger.debug("query_transaction_info_byId error",e);
			throw new RuntimeException();
		}
	}

	public Integer queryTransactionCount(Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_transaction_by_time_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_by_time_count error",e);
			throw new RuntimeException();
		}
	}
	
	
	public List<TransactionInfoDomain> queryInOrNotInSuccessCloseTrandeDomain(
			Map<String, Object> queryMap) {
		 try {
				return (List<TransactionInfoDomain>)this.queryForList("query_inOrNotInSuccessClose_transaction_domain",queryMap);
			} catch (Exception e) {
				logger.debug("query_successClose_transaction_domain error",e);
				throw new RuntimeException();
			}
	}
	
	public List<TransactionInfoDomain> queryInOrNotInSuccessCloseTrandeDomainByPage(
			Map<String, Object> queryMap) {
		  try {
				return (List<TransactionInfoDomain>)this.queryForList("query_inOrNotInSuccessClose_transaction_domain_byPage",queryMap);
			} catch (Exception e) {
				logger.debug("query_inOrNotInSuccessClose_transaction_domain_byPage error",e);
				throw new RuntimeException();
			}
	}

	public Integer queryInOrNotInSuccessCloseTrandeDomainCount(
			Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_inOrNotInSuccessClose_transaction_domain_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_inOrNotInSuccessClose_transaction_domain_count error",e);
			throw new RuntimeException();
		}
	}

	public String queryTransactionIdByCreateTime() {
		try {
			return (String)this.queryForObject("query_transaction_id_byCreateTime");
		} catch (Exception e) {
			logger.debug("query_transaction_id_byCreateTime error",e);
			throw new RuntimeException();
		}
	}

	public String addTransactionInfo(Map<String, Object> addMap) {
		try {
			return addObjectKeyString("isnert_transaction_info", addMap);
		} catch (Exception e) {
			logger.warn("isnert_transaction_info error", e);
			throw new RuntimeException();
		}
	}

	
	public List<TransactionInfoDomain> queryDriverCarTransactionInfoPage(
			Map<String, Object> queryMap) {
		try {
			List<TransactionInfoDomain> trandeList=(List<TransactionInfoDomain>)this.queryForList("query_transaction_car_bydriverid_list_page",queryMap);
			return trandeList;
					
		} catch (Exception e) {
			logger.debug("query_transaction_car_bydriverid_list_page error",e);
			throw new RuntimeException();
		}
	}
	
	public Integer queryDriverCarTransactionInfoPageCount(
			Map<String, Object> queryMap) {
		try {
			return (Integer)this.queryForObject("query_transaction_car_bydriverid_list_page_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_car_bydriverid_list_page_count error",e);
			throw new RuntimeException();
		}
	}

	public TransactionInfoDomain queryAllStartCount(String userId) {
		try {
			return (TransactionInfoDomain)this.queryForObject("query_allStart_count",userId);
		} catch (Exception e) {
			logger.debug("query_allStart_count error",e);
			throw new RuntimeException();
		}
	}

	public String queryAllStartNoAssessCount(Map<String, Object> queryMap) {
		try {
			return (String)this.queryForObject("query_allStart_noAssess_count",queryMap);
		} catch (Exception e) {
			logger.debug("query_allStart_noAssess_count error",e);
			throw new RuntimeException();
		}
	}

	public List<TransactionInfoDomain> queryTodayDynamicTimeAndStart(
			Map<String, Object> queryMap) {
		try {
			return (List<TransactionInfoDomain>)this.queryForList("query_today_dynamic_timeAndStart",queryMap);
		} catch (Exception e) {
			logger.debug("query_today_dynamic_timeAndStart error",e);
			throw new RuntimeException();
		}
	}

	public List<TransactionReceiptPath> queryTransactionReceiptPathByTradeId(
			Map<String,Object> queryMap) {
		try {
			return (List<TransactionReceiptPath>)this.queryForList("query_transaction_receiptpath_ByTradeId",queryMap);
		} catch (Exception e) {
			logger.debug("query_transaction_receiptpath_ByTradeId error",e);
			throw new RuntimeException();
		}
	}


	public boolean modifyTrandeDriverAndOrderStart(TransactionInfoDomain transactionInfoDomain){
		try {
			return this.saveObject("update_trande_driverAndOrderStart", transactionInfoDomain)==1;
		} catch (Exception e) {
			logger.debug("update_trande_driverAndOrderStart error",e);
			throw new RuntimeException();
		}
	}

}
