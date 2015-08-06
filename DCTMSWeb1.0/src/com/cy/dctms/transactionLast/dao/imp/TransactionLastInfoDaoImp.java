package com.cy.dctms.transactionLast.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.TransactionLastInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.transactionLast.dao.ITransactionLastInfoDao;


public class TransactionLastInfoDaoImp extends BaseDao implements ITransactionLastInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryTransactionLastInfoList(TransactionLastInfoDomain transactionLastInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", transactionLastInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (transactionLastInfoDomain.getPageInfo().getCurPage()-1)*transactionLastInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("transactionId", transactionLastInfoDomain.getTransactionId());
			transactionLastInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_transactionLast_info_count",queryMap));// 总页数
			
			List<TransactionLastInfoDomain> dataList = (List<TransactionLastInfoDomain>) queryForList("query_transactionLast_info_by_page",queryMap);
			transactionLastInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_transactionLastInfo_list",e);
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportTransactionLastInfo(TransactionLastInfoDomain transactionLastInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("transactionId", transactionLastInfoDomain.getTransactionId());
			List<TransactionLastInfoDomain> dataList = (List<TransactionLastInfoDomain>) queryForList("export_transactionLast_info",queryMap);
			transactionLastInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("export_transactionLastInfo_list",e);
			throw new RuntimeException();
		}
	}
	
}
