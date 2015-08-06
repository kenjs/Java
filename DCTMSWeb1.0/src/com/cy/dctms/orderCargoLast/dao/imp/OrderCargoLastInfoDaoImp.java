package com.cy.dctms.orderCargoLast.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.OrderCargoLastInfoDomain;
import com.cy.dctms.common.util.MD5Util;
import com.cy.dctms.orderCargoLast.dao.IOrderCargoLastInfoDao;


public class OrderCargoLastInfoDaoImp extends BaseDao implements IOrderCargoLastInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryOrderCargoLastInfoList(OrderCargoLastInfoDomain orderCargoLastInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", orderCargoLastInfoDomain.getPageInfo().getPageSize());//每页记录数
			queryMap.put("start", (orderCargoLastInfoDomain.getPageInfo().getCurPage()-1)*orderCargoLastInfoDomain.getPageInfo().getPageSize());
			//检索条件
			queryMap.put("cargoId", orderCargoLastInfoDomain.getCargoId());
			orderCargoLastInfoDomain.getPageInfo().setTotalRecords((Integer) queryForObject("query_orderCargoLast_info_count",queryMap));// 总页数
			
			List<OrderCargoLastInfoDomain> dataList = (List<OrderCargoLastInfoDomain>) queryForList("query_orderCargoLast_info_by_page",queryMap);
			orderCargoLastInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("query_orderCargoLastInfo_list",e);
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportOrderCargoLastInfo(OrderCargoLastInfoDomain orderCargoLastInfoDomain) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			//检索条件
			queryMap.put("cargoId", orderCargoLastInfoDomain.getCargoId());
			List<OrderCargoLastInfoDomain> dataList = (List<OrderCargoLastInfoDomain>) queryForList("export_orderCargoLast_info",queryMap);
			orderCargoLastInfoDomain.setDataList(dataList);
		} catch (Exception e) {
			logger.error("export_orderCargoLastInfo_list",e);
			throw new RuntimeException();
		}
	}
	
}
