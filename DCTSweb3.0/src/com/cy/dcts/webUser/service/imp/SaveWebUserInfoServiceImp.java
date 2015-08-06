package com.cy.dcts.webUser.service.imp;

import java.util.HashMap;
import java.util.Map;

import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.util.PrimaryGenerater;
import com.cy.dcts.webUser.dao.IWebUserInfoDao;
import com.cy.dcts.webUser.service.ISaveWebUserInfoService;

public class SaveWebUserInfoServiceImp implements ISaveWebUserInfoService{

	private IWebUserInfoDao webUserInfoDao;
	
	public String addWebUserInfo(WebUserInfo webUserInfo) {
		String encoded = webUserInfoDao.queryWebUserInfoEncoded().getEncoded();
		webUserInfo.setEncoded(PrimaryGenerater.generaterNextNumber(encoded));
		webUserInfo.setParentId("0");
		webUserInfo.setDeliveryFlag("1");
		webUserInfo.setArrivalSure("1");
		webUserInfo.setReceiveSure("1");
		return webUserInfoDao.addWebUserInfo(webUserInfo);
	}
	
	public String addSonWebUserInfo(WebUserInfo webUserInfo) {
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("parentId", webUserInfo.getParentId());
		WebUserInfo sonWebUserInfo = webUserInfoDao.queryWebUserInfoEncodedByParentId(queryMap);
		if(sonWebUserInfo == null) {
			webUserInfo.setEncoded(PrimaryGenerater.generaterNextNumber(""));
		}else {
			webUserInfo.setEncoded(PrimaryGenerater.generaterNextNumber(sonWebUserInfo.getEncoded()));
		}
		return webUserInfoDao.addWebUserInfo(webUserInfo);
	}

	public boolean modifyWebUserInfoById(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifyWebUserInfoById(webUserInfo) == 1;
	}

	public boolean modifyWebUserInfoEnterpriseFlagById(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifyWebUserInfoEnterpriseFlagById(webUserInfo);
	}

	public boolean modifyWebUserInfoPanymentFlagById(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifyWebUserInfoPanymentFlagById(webUserInfo);
	}

	public boolean modifyWebUserInfoPasswordById(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifyWebUserInfoPasswordById(webUserInfo);
	}

	public boolean modifyWebUserInfoPersonageFlagById(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifyWebUserInfoPersonageFlagById(webUserInfo);
	}
	
	public boolean modifyWebUserInfoMobilephoneById(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifyWebUserInfoMobilephoneById(webUserInfo);
	}
	
	public IWebUserInfoDao getWebUserInfoDao() {
		return webUserInfoDao;
	}

	public void setWebUserInfoDao(IWebUserInfoDao webUserInfoDao) {
		this.webUserInfoDao = webUserInfoDao;
	}
	public boolean modifyWebUserInfoEncodedById(WebUserInfo webUserInfo) {		
		return webUserInfoDao.modifyWebUserInfoEncodedById(webUserInfo);
	}

	public boolean modifySonWebUserById(WebUserInfo webUserInfo) {
		return webUserInfoDao.modifySonWebUserInfoById(webUserInfo);
	}
	
}
