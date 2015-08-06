package com.cy.driver.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.common.bo.DriverUserInfoBo;
import com.cy.common.util.DateUtil;
import com.cy.common.util.HttpPostUtil;
import com.cy.common.util.MD5Util;
import com.cy.driver.cargo.dao.OrderCargoInfoDao;
import com.cy.driver.line.domain.DriverLineInfoDomain;
import com.cy.driver.line.service.DriverLineInfoService;
import com.cy.driver.order.domain.DriverBusinessLineInfoDomain;
import com.cy.driver.order.service.DriverBusinessLineInfoService;
import com.cy.driver.user.dao.DriverUserCargoInfoDao;
import com.cy.driver.user.domain.CompanyInfoDomain;
import com.cy.driver.user.domain.DriverNotificationInfoDomain;
import com.cy.driver.user.domain.DriverUserInfoDomain;
import com.cy.driver.user.service.DriverUserCargoInfoService;
/**
 * 货源信息service impl
 * @date 2014-5-29
 * @author haoyong
 *
 */
public class DriverUserCargoInfoServiceImpl implements DriverUserCargoInfoService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DriverUserCargoInfoDao driverUserCargoInfoDao;
	private DriverBusinessLineInfoService driverBusinessLineInfoService;
	private DriverLineInfoService driverLineInfoService;
	private OrderCargoInfoDao orderCargoInfoDao;
	
	private String sendNoteSDK;//短信接口访问地址
	private String sendNoteUSERID;//短信接口USERID
	private String sendNotePwdSDK;//短信接口密码
	private String sendNoteTERMID;//扩展号
	private String sendNoteTIME;//发送时间 1为立即发送,定时发送 格式为: yyyyMMddHHmmss

	
	public int selectSuitCargoCount(String driverId) {
		int count = 0;
		List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoService.selectDriverBusinessLineInfoList(driverId);
		
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
				
		count = driverUserCargoInfoDao.selectSuitCargoCount(map);

		return count;
	}

	public int selectNearByCargoCount(String driverId) {
		Map<String,Object> locationMap = driverUserCargoInfoDao.selectDriverLastLocation(driverId);
		Map<String,Object> map = new HashMap<String,Object>();
		if(locationMap != null) {
			if(locationMap.containsKey("province")) {
				map.put("startProvince",locationMap.get("province"));
			}
			if(locationMap.containsKey("city")) {
				map.put("startCity",locationMap.get("city"));
			}
		}
		return driverUserCargoInfoDao.selectNearByCargoCount(map);
	}

	public void setDriverUserCargoInfoDao(
			DriverUserCargoInfoDao driverUserCargoInfoDao) {
		this.driverUserCargoInfoDao = driverUserCargoInfoDao;
	}

	public int updateDriverUserInfo(DriverUserInfoBo bo) {
		return driverUserCargoInfoDao.updateDriverUserInfo(bo);
	}

	public DriverUserInfoDomain selectUserBasicInfo(String driverId) {
		return driverUserCargoInfoDao.selectUserBasicInfo(driverId);
	}

	@SuppressWarnings("unchecked")
	public List<DriverNotificationInfoDomain> queryDriverNotificationInfo(
			String driverId,String fromSize,String listSize) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("driverId", driverId);
		map.put("fromSize", fromSize);
		map.put("listSize", listSize);
		return (List<DriverNotificationInfoDomain>) driverUserCargoInfoDao.queryDriverNotificationInfo(map);
	}
	
	public String noteSend(String phoneNumber, String content) {
		StringBuffer posturl = new StringBuffer(sendNoteSDK);
		
		String md5 = MD5Util.MD5(sendNoteUSERID+"||"+phoneNumber+"||"+sendNotePwdSDK);
		
		try {
			content = URLEncoder.encode(content,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		Map<String, String> params = new  HashMap<String, String>();
		params.put("userid", sendNoteUSERID);
		params.put("smstype", "0");
		params.put("phones", phoneNumber);
		params.put("content", content);
		params.put("sendtermid", sendNoteTERMID);
		params.put("sendtime", sendNoteTIME); 
		params.put("md5", md5.toLowerCase());
		
		return HttpPostUtil.postXml(posturl.toString(), params);

	}

	public void setSendNoteSDK(String sendNoteSDK) {
		this.sendNoteSDK = sendNoteSDK;
	}

	public String getSendNotePwdSDK() {
		return sendNotePwdSDK;
	}

	public void setSendNotePwdSDK(String sendNotePwdSDK) {
		this.sendNotePwdSDK = sendNotePwdSDK;
	}

	public CompanyInfoDomain selectConpanyInfoById(String id) {
		return driverUserCargoInfoDao.selectConpanyInfoById(id);
	}

	public String cargoInfoRemind(Map<String, String> mapPar) {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		String driverId = mapPar.get("driverId");
		Map<String,Object> nearByMap = null;
		Map<String,Object> businessMap = null;
		Map<String,Object> lineMap = null;
		
		Map<String,Object> locationMap = driverUserCargoInfoDao.selectDriverLastLocation(driverId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nearByModifyTime", mapPar.get("nearByModifyTime"));
		if(locationMap != null) {
			if(locationMap.containsKey("province")) {
				map.put("startProvince",locationMap.get("province"));
			}
			if(locationMap.containsKey("city")) {
				map.put("startCity",locationMap.get("city"));
			}
		}
		nearByMap = orderCargoInfoDao.selectNearByCargoRemid(map);
		String nearByNum = nearByMap.get("nearByNum").toString();
		String nearByTime = nearByMap.get("nearByTime") == null ? "" : nearByMap.get("nearByTime").toString();
		
		json.accumulate("nearByNum", nearByNum);
		json.accumulate("nearByTime", nearByTime);
		
		jsonArray.add(json);
		json.clear();
		
		map.clear();
		map.put("businesslineModifyTime", mapPar.get("businesslineModifyTime"));
		List<DriverBusinessLineInfoDomain> list = driverBusinessLineInfoService.selectDriverBusinessLineInfoList(driverId);
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
					map.put("endTime" + (i +1), e.getEndTime());
				}
			}
		} catch (ParseException e1) {			
			e1.printStackTrace();
		}		
		businessMap = orderCargoInfoDao.selectBusinesslineCargoRemid(map);
		String businesslineNum = businessMap.get("businesslineNum").toString();
		String businesslineTime = businessMap.get("businesslineTime") == null ? "" : businessMap.get("businesslineTime").toString();
		
		json.accumulate("businesslineNum", businesslineNum);
		json.accumulate("businesslineTime", businesslineTime);
		
		jsonArray.add(json);
		json.clear();
		
		map.clear();
		map.put("driverLineModifyTime", mapPar.get("driverLineModifyTime"));
		@SuppressWarnings("unchecked")
		List<DriverLineInfoDomain> listLine = (List<DriverLineInfoDomain>) driverLineInfoService.selectDriverLineInfoList(driverId);
		if(listLine != null) {
			for (int i = 0;i<listLine.size();i ++) {
				DriverLineInfoDomain e = listLine.get(i);
				map.put("startProvice" + (i +1), e.getStartProvince());
				map.put("startCity" + (i +1), e.getStartCity());
				map.put("endProvince" + (i +1), e.getEndProvince());
				map.put("endCity" + (i +1), e.getEndCity());				
			}
		}	
		lineMap = orderCargoInfoDao.selectNeededCargoRemid(map);
		String driverLineNum = lineMap.get("driverLineNum").toString();
		String driverLineTime = lineMap.get("driverLineTime") == null ? "" : lineMap.get("driverLineTime").toString();
		
		json.accumulate("driverLineNum", driverLineNum);
		json.accumulate("driverLineTime", driverLineTime);
		
		jsonArray.add(json);
		json.clear();
		
		return jsonArray.toString();
	}

	public void setOrderCargoInfoDao(OrderCargoInfoDao orderCargoInfoDao) {
		this.orderCargoInfoDao = orderCargoInfoDao;
	}

	public void setDriverBusinessLineInfoService(
			DriverBusinessLineInfoService driverBusinessLineInfoService) {
		this.driverBusinessLineInfoService = driverBusinessLineInfoService;
	}

	public void setDriverLineInfoService(DriverLineInfoService driverLineInfoService) {
		this.driverLineInfoService = driverLineInfoService;
	}

	public void setSendNoteUSERID(String sendNoteUSERID) {
		this.sendNoteUSERID = sendNoteUSERID;
	}

	public void setSendNoteTERMID(String sendNoteTERMID) {
		this.sendNoteTERMID = sendNoteTERMID;
	}

	public void setSendNoteTIME(String sendNoteTIME) {
		this.sendNoteTIME = sendNoteTIME;
	}
	
}
