package com.cy.dcts.webUser.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.common.util.PrimaryGenerater;
import com.cy.dcts.webUser.dao.IWebUserInfoDao;
import com.cy.dcts.webUser.service.IQueryWebUserInfoService;

public class QueryWebUserInfoServiceImp implements IQueryWebUserInfoService {

	private IWebUserInfoDao webUserInfoDao;
	
	public WebUserInfoDamain queryWebUserCompanyoyById(String id) {
		return webUserInfoDao.queryWebUserCompanyoyById(id);
	}
	
	public Integer queryWebUserByParentIdUsertypeEncoded(String parentId,String encoded,String userType) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("parentId", parentId);
		queryMap.put("userType", userType);
		queryMap.put("encoded", encoded);  
		return webUserInfoDao.queryWebUserByParentIdUsertypeEncoded(queryMap);
	}
	public List<WebUserInfo> queryWebUserInfo(WebUserInfo webUserInfo) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", webUserInfo.getId());
		queryMap.put("code", webUserInfo.getCode());
		queryMap.put("mobilephone", webUserInfo.getMobilephone());
		queryMap.put("companyId", webUserInfo.getCompanyId());
		queryMap.put("deletedFlag", webUserInfo.getDeletedFlag());
		return webUserInfoDao.queryWebUserInfo(queryMap);
	}

	public List<WebUserInfoDamain> querySonWebUserInfoPage(
			WebUserInfoDamain webUserInfoDamain) {
		List<WebUserInfoDamain> list = new ArrayList<WebUserInfoDamain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(webUserInfoDamain != null) {
			if(StringUtils.isNotEmpty(webUserInfoDamain.getUserType())) {
				queryMap.put("userType", webUserInfoDamain.getUserType());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getParentId())) {
				queryMap.put("parentId", webUserInfoDamain.getParentId());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getCompanyName())) {
				queryMap.put("companyName", webUserInfoDamain.getCompanyName());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getCreateTimeStart())) {
				queryMap.put("createTimeStart", webUserInfoDamain.getCreateTimeStart());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getCreateTimeEnd())) {
				queryMap.put("createTimeEnd", webUserInfoDamain.getCreateTimeEnd());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getDeletedFlag())) {
				queryMap.put("deletedFlag", webUserInfoDamain.getDeletedFlag());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getCompanyProvince())) {
				queryMap.put("companyProvince", webUserInfoDamain.getCompanyProvince());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getCompanyCity())) {
				queryMap.put("companyCity", webUserInfoDamain.getCompanyCity());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getCompanyCounty())) {
				queryMap.put("companyCounty", webUserInfoDamain.getCompanyCounty());
			}
			if(webUserInfoDamain.getPageInfo() != null) {
				webUserInfoDamain.getPageInfo().setTotalRecords(webUserInfoDao.querySonWebUserInfoPageCount(queryMap));
				queryMap.put("pageSize", webUserInfoDamain.getPageInfo().getPageSize());
				queryMap.put("curPage", webUserInfoDamain.getPageInfo().getCurPage());
				list =  webUserInfoDao.querySonWebUserInfoPage(queryMap);
			}else {
				list = webUserInfoDao.querySonWebUserInfoList(queryMap);
			}
		}
		return list;
	}

	public List<WebUserInfoDamain> querySonWebUserInfoById(
			WebUserInfoDamain webUserInfoDamain) {
		List<WebUserInfoDamain> list = new ArrayList<WebUserInfoDamain>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(webUserInfoDamain != null) {
			if(StringUtils.isNotEmpty(webUserInfoDamain.getUserType())) {
				queryMap.put("userType", webUserInfoDamain.getUserType());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getParentId())) {
				queryMap.put("parentId", webUserInfoDamain.getParentId());
			}
			if(StringUtils.isNotEmpty(webUserInfoDamain.getId())) {
				queryMap.put("id", webUserInfoDamain.getId());
			}
			list = webUserInfoDao.querySonWebUserInfoList(queryMap);
		}
		return list;
	}
	
	public String getNextEncode() throws Exception {
		WebUserInfo info = webUserInfoDao.queryWebUserInfoEncoded();
		return PrimaryGenerater.generaterNextNumber(info.getEncoded());
	}
	public WebUserInfo queryWebUserInfoById(String userId) {
		return webUserInfoDao.queryWebUserInfoById(userId);
	}
	
	public WebUserInfo queryWebUserInfoByCode(String code) {
		return webUserInfoDao.queryWebUserInfoByCode(code);
	}

	public WebUserInfo queryWebUserInfoByMobilephone(String mobilephone) {
		return webUserInfoDao.queryWebUserInfoByMobilephone(mobilephone);
	}
	
	public IWebUserInfoDao getWebUserInfoDao() {
		return webUserInfoDao;
	}

	public void setWebUserInfoDao(IWebUserInfoDao webUserInfoDao) {
		this.webUserInfoDao = webUserInfoDao;
	}

	public void updateUserFlag(Map<String,Object> map) {
		webUserInfoDao.updateUserFlag(map);
	}
	

}
