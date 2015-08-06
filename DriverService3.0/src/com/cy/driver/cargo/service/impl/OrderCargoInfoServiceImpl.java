package com.cy.driver.cargo.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.common.bo.DriverCargoAssessInfoBo;
import com.cy.common.bo.DriverCargoCollectInfoBo;
import com.cy.common.util.DateUtil;
import com.cy.driver.cargo.dao.OrderCargoInfoDao;
import com.cy.driver.cargo.domain.OrderCargoInfoDomain;
import com.cy.driver.cargo.service.OrderCargoInfoService;
import com.cy.driver.line.domain.DriverLineInfoDomain;
import com.cy.driver.line.service.DriverLineInfoService;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.order.service.DriverBusinessLineInfoService;
import com.cy.driver.user.dao.DriverUserCargoInfoDao;
/**
 * 货源service impl
 * @date 2014-5-30
 * @author haoyong
 *
 */
public class OrderCargoInfoServiceImpl implements OrderCargoInfoService{
	private OrderCargoInfoDao orderCargoInfoDao;
	private DriverUserCargoInfoDao driverUserCargoInfoDao;
	private DriverBusinessLineInfoService driverBusinessLineInfoService;
	private DriverLineInfoService driverLineInfoService;
	
	public List<OrderCargoInfoDomain> selectNearByCargoList(String driverId,String fromSize,String listSize) {	
		Map<String,Object> locationMap = driverUserCargoInfoDao.selectDriverLastLocation(driverId);
		Map<String,Object> map = new HashMap<String, Object>();
		if(locationMap != null) {
			if(locationMap.containsKey("province")) {
				map.put("startProvince",locationMap.get("province"));
			}
			if(locationMap.containsKey("city")) {
				map.put("startCity",locationMap.get("city"));
			}
		}
		map.put("fromSize", fromSize);
		map.put("listSize", listSize);
		return orderCargoInfoDao.selectNearByCargoList(map);
	}

	public List<OrderCargoInfoDomain> selectCargoSuitOrderList(String driverId,String fromSize,String listSize) {
		List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoService.selectDriverBusinessLineInfoList(driverId);
		List<OrderCargoInfoDomain> dataList = null;
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String currDate = DateUtil.getNowStr();
			if(list != null) {
				for (int i = 0;i<list.size();i ++) {
					DriverBusinessLineInfoDomain e = list.get(i);
					map.put("startProvice" + (i +1), e.getStartProvince());
					map.put("startCity" + (i +1), e.getStartCity());
					map.put("endProvince" + (i +1), e.getEndProvince());
					map.put("endCity" + (i +1), e.getEndCity());
					String st = e.getStartTime();
					if(DateUtil.isEarly(st,currDate)) {
						st = currDate;
					}
					map.put("startTime" + (i +1), st);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		map.put("fromSize", fromSize);
		map.put("listSize", listSize);
		dataList = orderCargoInfoDao.selectCargoSuitOrderList(map);
		return dataList;
	}

	public void setOrderCargoInfoDao(OrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}

	public void setDriverLineInfoService(DriverLineInfoService driverLineInfoService) {
		this.driverLineInfoService = driverLineInfoService;
	}

	public void setDriverUserCargoInfoDao(
			DriverUserCargoInfoDao driverUserCargoInfoDao) {
		this.driverUserCargoInfoDao = driverUserCargoInfoDao;
	}

	public void setDriverBusinessLineInfoService(
			DriverBusinessLineInfoService driverBusinessLineInfoService) {
		this.driverBusinessLineInfoService = driverBusinessLineInfoService;
	}

	public List<OrderCargoInfoDomain> selectCargoList(Map<String, String> map) {
		return orderCargoInfoDao.selectCargoList(map);
	}

	public int selectCargoNumByDriverLine(String driverId) {
		@SuppressWarnings("unchecked")
		List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
		Map<String,Object> map = new HashMap<String, Object>();
		if(list != null) {
			for (int i = 0;i<list.size();i ++) {
				DriverLineInfoDomain e = list.get(i);
				map.put("startProvice" + (i +1), e.getStartProvince());
				map.put("startCity" + (i +1), e.getStartCity());
				map.put("endProvince" + (i +1), e.getEndProvince());
				map.put("endCity" + (i +1), e.getEndCity());				
			}
		}		
		int count = orderCargoInfoDao.selectCargoNumByDriverLine(map);
		return count;
	}

	public List<OrderCargoInfoDomain> selectCargoListByDriverLine(String driverId,String fromSize,String listSize) {
		@SuppressWarnings("unchecked")
		List<DriverLineInfoDomain> list = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
		Map<String,Object> map = new HashMap<String, Object>();
		List<OrderCargoInfoDomain> dataList = null;
		if(list != null) {
			for (int i = 0;i<list.size();i ++) {
				DriverLineInfoDomain e = list.get(i);
				map.put("startProvice" + (i +1), e.getStartProvince());
				map.put("startCity" + (i +1), e.getStartCity());
				map.put("endProvince" + (i +1), e.getEndProvince());
				map.put("endCity" + (i +1), e.getEndCity());				
			}
		}				
		map.put("fromSize", fromSize);
		map.put("listSize", listSize);
		
		dataList = orderCargoInfoDao.selectCargoListByDriverLine(map);
		
		return dataList;
	}

	public OrderCargoInfoDomain selectCargoDetailById(String id) {
		return orderCargoInfoDao.selectCargoDetailById(id);
	}

	public int selectByDriverAndCargoId(String driverId,String cargoId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("driverId", driverId);
		map.put("cargoId", cargoId);
		return orderCargoInfoDao.selectByDriverAndCargoId(map);
	}

	public int updateAssess(DriverCargoAssessInfoBo bo) {
		return orderCargoInfoDao.updateAssess(bo);
	}

	public int attentionCargoInfo(DriverCargoCollectInfoBo bo) {
		return orderCargoInfoDao.attentionCargoInfo(bo);
	}

	public int commentCargoInfo(DriverCargoAssessInfoBo bo) {
		return orderCargoInfoDao.commentCargoInfo(bo);
	}

	public boolean cargoIsAttention(String driverId, String cargoId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("driverId", driverId);
		map.put("cargoId", cargoId);
		return orderCargoInfoDao.cargoIsAttention(map) == 1 ? true : false;
	}

	public void updateCargoInfo(Map<String, Object> map) {
		orderCargoInfoDao.updateCargoInfo(map);
	}

	public int selectDriverCargoAssessNum(String cargoId) {
		return orderCargoInfoDao.selectDriverCargoAssessNum(cargoId);
	}

}
