package com.cy.dctms.transaction.dao.imp;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JApplet;

import oracle.sql.JAVA_STRUCT;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.bo.TransactionInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.TransactionInfoDomain;
import com.cy.dctms.common.util.FlagChangeName;
import com.cy.dctms.transaction.dao.ITransactionInfoDao;


public class TransactionInfoDaoImp extends BaseDao implements ITransactionInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryTransactionInfoList(TransactionInfoDomain transactionInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", transactionInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (transactionInfoDomain.getPageInfo().getCurPage()-1)*transactionInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("orderNumber", transactionInfoDomain.getOrderNumber());
			queryMap.put("cargoId", transactionInfoDomain.getCargoId());
			queryMap.put("driverId", transactionInfoDomain.getDriverId());
			queryMap.put("deployUserid", transactionInfoDomain.getDeployUserid());
			queryMap.put("companyId", transactionInfoDomain.getCompanyId());
			queryMap.put("tradeFair", transactionInfoDomain.getTradeFair());
			queryMap.put("tradeStart", transactionInfoDomain.getTradeStart());
			queryMap.put("tradeStartTime", transactionInfoDomain.getTradeStartTime());
			queryMap.put("orderStart", transactionInfoDomain.getOrderStart());
			queryMap.put("remark", java.net.URLDecoder.decode(transactionInfoDomain.getRemark(),"utf-8"));
			queryMap.put("tradeCancelOrigin", transactionInfoDomain.getTradeCancelOrigin());
			queryMap.put("queryTimeQ", transactionInfoDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", transactionInfoDomain.getQueryTimeZ());
			transactionInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_transaction_info_count",queryMap));// 总页数
			
			List<TransactionInfoDomain> dataList = (List<TransactionInfoDomain>) queryForList("query_transaction_info_by_page",queryMap);
			for (TransactionInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getTradeStart())) {
					domain.setTradeStart(FlagChangeName.tradeState(domain.getTradeStart()));
				}
				if (StringUtils.isNotBlank(domain.getOrderStart())) {
					domain.setOrderStart(FlagChangeName.orderState(domain.getOrderStart()));
				}
				if (StringUtils.isNotBlank(domain.getTradeCancelOrigin())) {
					domain.setTradeCancelOrigin(FlagChangeName.orderCancelOrigin(domain.getTradeCancelOrigin()));
				}
			}
			transactionInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_transactionInfo_list",e);
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportTransactionInfo(TransactionInfoDomain transactionInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("orderNumber", transactionInfoDomain.getOrderNumber());
			queryMap.put("cargoId", transactionInfoDomain.getCargoId());
			queryMap.put("driverId", transactionInfoDomain.getDriverId());
			queryMap.put("deployUserid", transactionInfoDomain.getDeployUserid());
			queryMap.put("companyId", transactionInfoDomain.getCompanyId());
			queryMap.put("tradeFair", transactionInfoDomain.getTradeFair());
			queryMap.put("tradeStart", transactionInfoDomain.getTradeStart());
			queryMap.put("tradeStartTime", transactionInfoDomain.getTradeStartTime());
			queryMap.put("orderStart", transactionInfoDomain.getOrderStart());
			queryMap.put("remark", transactionInfoDomain.getRemark());
			queryMap.put("tradeCancelOrigin", transactionInfoDomain.getTradeCancelOrigin());
			queryMap.put("queryTimeQ", transactionInfoDomain.getQueryTimeQ());
			queryMap.put("queryTimeZ", transactionInfoDomain.getQueryTimeZ());
			List<TransactionInfoDomain> dataList = (List<TransactionInfoDomain>) queryForList("export_transaction_info",queryMap);
			for (TransactionInfoDomain domain : dataList) {
				if (StringUtils.isNotBlank(domain.getTradeStart())) {
					domain.setTradeStart(FlagChangeName.tradeState(domain.getTradeStart()));
				}
				if (StringUtils.isNotBlank(domain.getOrderStart())) {
					domain.setOrderStart(FlagChangeName.orderState(domain.getOrderStart()));
				}
				if (StringUtils.isNotBlank(domain.getTradeCancelOrigin())) {
					domain.setTradeCancelOrigin(FlagChangeName.orderCancelOrigin(domain.getTradeCancelOrigin()));
				}
			}
			transactionInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("export_transactionInfo_list",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public TransactionInfoDomain queryTransactionInfoById(String id) {
		try {
			return (TransactionInfoDomain) queryForObject("query_transaction_info_by_id", id);
		} catch (Exception e) {
			logger.error("query_transactionInfo_by_id",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void saveTransactionInfo(TransactionInfoDomain transactionInfoDomain,String userId) {
		try {
			TransactionInfo bo = new TransactionInfo();
			bo.setOrderNumber(java.net.URLDecoder.decode(transactionInfoDomain.getOrderNumber(),"utf-8"));
			bo.setCargoId(Long.valueOf(transactionInfoDomain.getCargoId()));
			bo.setDriverId(Long.valueOf(transactionInfoDomain.getDriverId()));
			bo.setDeployUserid(Long.valueOf(transactionInfoDomain.getDeployUserid()));
			bo.setCompanyId(Long.valueOf(transactionInfoDomain.getCompanyId()));
			bo.setTradeFair(Double.valueOf(transactionInfoDomain.getTradeFair()));
			bo.setTradeStart(Long.valueOf(transactionInfoDomain.getTradeStart()));
			bo.setTradeStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(transactionInfoDomain.getTradeStartTime()));
			bo.setOrderStart(Long.valueOf(transactionInfoDomain.getOrderStart()));
			bo.setRemark(java.net.URLDecoder.decode(transactionInfoDomain.getRemark(),"utf-8"));
			bo.setTradeCancelOrigin(Long.valueOf(transactionInfoDomain.getTradeCancelOrigin()));
			bo.setId(Long.valueOf(transactionInfoDomain.getId()));
			saveObject("modify_transaction_info", bo);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("修改交易信息信息");
		 	MWLIbo.setColumnId(Long.valueOf(transactionInfoDomain.getId()));
		 	String content = "";
		 	content = content + "orderNumber:"+bo.getOrderNumber() +";";
		 	content = content + "cargoId:"+bo.getCargoId() +";";
		 	content = content + "driverId:"+bo.getDriverId() +";";
		 	content = content + "deployUserid:"+bo.getDeployUserid() +";";
		 	content = content + "companyId:"+bo.getCompanyId() +";";
		 	content = content + "tradeFair:"+bo.getTradeFair() +";";
		 	content = content + "tradeStart:"+bo.getTradeStart() +";";
		 	content = content + "tradeStartTime:"+bo.getTradeStartTime() +";";
		 	content = content + "orderStart:"+bo.getOrderStart() +";";
		 	content = content + "remark:"+bo.getRemark() +";";
		 	content = content + "tradeCancelOrigin:"+bo.getTradeCancelOrigin() +";";
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_transaction_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("save_transactionInfo",e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteTransactionInfo(String id ,String userId) {
		try {
			 deleteObject("delete_transaction_info_by_id", id);
			 //添加操作日志
		 	ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("删除交易信息信息");
		 	MWLIbo.setColumnId(Long.valueOf(id));
		 	MWLIbo.setContent("id:"+id);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_transaction_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			logger.error("delete_transactionInfo",e);
			throw new RuntimeException();
		}
		
	}
}
