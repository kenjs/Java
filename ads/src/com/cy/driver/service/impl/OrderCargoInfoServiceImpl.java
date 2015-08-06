package com.cy.driver.service.impl;

import com.cy.driver.bo.DriverCargoAssessInfoBo;
import com.cy.driver.bo.DriverCargoCollectInfoBo;
import com.cy.driver.common.util.DateUtil;
import com.cy.driver.dao.DriverUserCargoInfoDao;
import com.cy.driver.dao.OrderCargoInfoDao;
import com.cy.driver.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.domain.DriverLineInfoDomain;
import com.cy.driver.domain.OrderCargoInfoDomain;
import com.cy.driver.service.DriverBusinessLineInfoService;
import com.cy.driver.service.DriverLineInfoService;
import com.cy.driver.service.OrderCargoInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 货源service impl
 * @date 2014-5-30
 * @author haoyong
 *
 */
@Service("orderCargoInfoService")
public class OrderCargoInfoServiceImpl implements OrderCargoInfoService{
    @Resource
	private OrderCargoInfoDao orderCargoInfoDao;

    @Resource
	private DriverUserCargoInfoDao driverUserCargoInfoDao;

    @Resource
	private DriverBusinessLineInfoService driverBusinessLineInfoService;

    @Resource
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
		List<OrderCargoInfoDomain> dataList;
		Map<String,Object> map = new HashMap<String, Object>();
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

	public List<OrderCargoInfoDomain> selectCargoList(Map<String, Object> map) {
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

	public OrderCargoInfoDomain selectCargoDetailById(String driverId,String id) {
		OrderCargoInfoDomain domain = orderCargoInfoDao.selectCargoDetailById(id);
		if (domain != null) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("driverId", driverId);
			map.put("cargoId", id);
			int num = orderCargoInfoDao.cargoIsAttention(map);
			if (num > 0) {
				domain.setIsMyAttention("y");
			}
		}
		return domain;
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
