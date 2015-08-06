package com.cy.dcts.orderCargo.service.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.bo.OrderCargoInfo;
import com.cy.dcts.common.constants.Constants;
import com.cy.dcts.common.domain.OrderCargoInfoDomain;
import com.cy.dcts.common.domain.QuoteInfoDomain;
import com.cy.dcts.common.util.SysToolsUtil;
import com.cy.dcts.orderCargo.dao.IOrderCargoInfoDao;
import com.cy.dcts.orderCargo.service.IQueryOrderCargoInfoService;

public class QueryOrderCargoInfoServiceImp implements IQueryOrderCargoInfoService{
	private IOrderCargoInfoDao orderCargoInfoDao;
	
	public OrderCargoInfo queryOrderCargoInfoById(String id){
		return orderCargoInfoDao.queryOrderCargoInfoById(id);
	}
	public OrderCargoInfoDomain queryOrderCargoInfoDomainById(String id){
		return orderCargoInfoDao.queryOrderCargoInfoDomainById(id);
	}
	public List<OrderCargoInfoDomain> queryOrderCargoInfoPage(OrderCargoInfoDomain orderCargoInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
	    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		if(orderCargoInfoDomain != null) { 
			queryMap.put("deployUserid", orderCargoInfoDomain.getDeployUserid());
			queryMap.put("deletedFlag", Constants.DELETED_FLAG_FALSE);
			queryMap.put("isExpireTure", Constants.IS_EXPIRE_TRUE);
			 queryMap.put("isExpireFalse", Constants.IS_EXPIRE_FALSE);
			queryMap.put("cargoName", orderCargoInfoDomain.getCargoName());
			queryMap.put("cargoType", orderCargoInfoDomain.getCargoType());
			queryMap.put("cargoFlag", orderCargoInfoDomain.getCargoFlag());
			try {
				//格式日期
				if(!SysToolsUtil.isNullOrEmpty(orderCargoInfoDomain.getStartTime())){
					queryMap.put("startTime", dateFormat.parse(orderCargoInfoDomain.getStartTime()));
				}
				if(!SysToolsUtil.isNullOrEmpty(orderCargoInfoDomain.getEndTime())){
					queryMap.put("endTime", dateFormat.parse(orderCargoInfoDomain.getEndTime()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			queryMap.put("startProCityCounty", orderCargoInfoDomain.getStartProCityCounty());
			queryMap.put("endProCityCounty", orderCargoInfoDomain.getEndProCityCounty());
			if(orderCargoInfoDomain.getPageInfo()!=null){
				int pageSizes=orderCargoInfoDomain.getPageInfo().getPageSize();
				int curPages=orderCargoInfoDomain.getPageInfo().getCurPage();
				queryMap.put("beginNum",pageSizes*(curPages-1) );
				queryMap.put("endNum",pageSizes);
				orderCargoInfoDomain.getPageInfo().setTotalRecords(orderCargoInfoDao.queryOrderCargoInfoCount(queryMap));
				return orderCargoInfoDao.queryOrderCargoInfoByPage(queryMap);
			}
		}
		return orderCargoInfoDao.queryOrderCargoInfoDomain(queryMap);
	}
	
	public List<OrderCargoInfoDomain> queryStartDeployOrderCargoInfoByPage(OrderCargoInfoDomain orderCargoInfoDomain) {
		List<OrderCargoInfoDomain> list;
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(orderCargoInfoDomain != null) {
			queryMap.put("cargoFlag",orderCargoInfoDomain.getCargoFlag());
			queryMap.put("deletedFlag",orderCargoInfoDomain.getDeletedFlag());
			queryMap.put("curPage", orderCargoInfoDomain.getPageInfo().getCurPage()*orderCargoInfoDomain.getPageInfo().getPageSize());
			queryMap.put("pageSize", orderCargoInfoDomain.getPageInfo().getPageSize());
		}
		orderCargoInfoDomain.getPageInfo().setTotalRecords(orderCargoInfoDao.queryStartDeployOrderCargoInfoByPageCount(queryMap));
		list = orderCargoInfoDao.queryStartDeployOrderCargoInfoByPage(queryMap);
		return list;
	}
	
	public Integer queryOrderCargoCount(String createTime) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(createTime)) {
			queryMap.put("createTime", createTime);
		}
		Integer count = orderCargoInfoDao.queryOrderCargoCount(queryMap);
		return count;
	}
	
	
	public List<OrderCargoInfoDomain> queryOrderCargoQuoteInfoList(OrderCargoInfoDomain orderCargoInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(orderCargoInfoDomain != null) {
			if(StringUtils.isNotEmpty(orderCargoInfoDomain.getDeployUserid())) {
				queryMap.put("deployUserid", orderCargoInfoDomain.getDeployUserid());
			}
			if(StringUtils.isNotEmpty(orderCargoInfoDomain.getDeletedFlag())) {
				queryMap.put("deletedFlag", orderCargoInfoDomain.getDeletedFlag());
			}
			if(StringUtils.isNotEmpty(orderCargoInfoDomain.getCargoFlag())) {
				queryMap.put("cargoFlag", orderCargoInfoDomain.getCargoFlag());
			}
			if(StringUtils.isNotEmpty(orderCargoInfoDomain.getId())) {
				queryMap.put("id", orderCargoInfoDomain.getId());
			}
		}
		return orderCargoInfoDao.queryOrderCargoQuoteInfoList(queryMap);
	}
	
	
	public List<QuoteInfoDomain> queryQuoteInfoList(
			QuoteInfoDomain quoteInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("quoteTypeVehiclesValue", Constants.QUOTE_TYPE_VEHICLES_VALUE);
		queryMap.put("quoteTypeByTonValue", Constants.QUOTE_TYPE_BY_TON_VALUE);
		queryMap.put("quoteTypePressSquareValue", Constants.QUOTE_TYPE_PRESS_SQUARE_VALUE);
		if(quoteInfoDomain != null) {
			if(StringUtils.isNotEmpty(quoteInfoDomain.getCargoId())) {
				queryMap.put("cargoId", quoteInfoDomain.getCargoId());
			}
			if(StringUtils.isNotEmpty(quoteInfoDomain.getStart())) {
				queryMap.put("start", quoteInfoDomain.getStart());
			}
		}
		return orderCargoInfoDao.queryQuoteInfoList(queryMap);
	}
	
	
	public IOrderCargoInfoDao getOrderCargoInfoDao() {
		return orderCargoInfoDao;
	}
	public void setOrderCargoInfoDao(IOrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}
	
	
}
