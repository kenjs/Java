package com.cy.dctms.webUser.service.imp;


import com.cy.dctms.common.domain.WebUserInfoDomain;
import com.cy.dctms.webUser.dao.IWebUserInfoDao;
import com.cy.dctms.webUser.service.IWebUserInfoService;

public class WebUserInfoServiceImp implements IWebUserInfoService {

	private IWebUserInfoDao webUserInfoDao;

	@Override
	public void queryWebUserInfoList(WebUserInfoDomain webUserInfoDomain) {
		webUserInfoDao.queryWebUserInfoList(webUserInfoDomain);
	}
	
	@Override
	public void auditWebUserInfoList(WebUserInfoDomain webUserInfoDomain) {
		webUserInfoDao.auditWebUserInfoList(webUserInfoDomain);
	}
	
	@Override
	public void queryWebUserInfoTransactionList(WebUserInfoDomain webUserInfoDomain) {
		webUserInfoDao.queryWebUserInfoTransactionList(webUserInfoDomain);
	}

	@Override
	public WebUserInfoDomain queryWebUserInfoMxById(String id) {
		 return webUserInfoDao.queryWebUserInfoById(id);
	}

	@Override
	public void saveWebUserInfo(WebUserInfoDomain webUserInfoDomain ,String userId) {		
		webUserInfoDao.saveWebUserInfo(webUserInfoDomain,userId);
	}

	@Override
	public void deleteWebUserInfo(WebUserInfoDomain webUserInfoDomain,String userId) {
		webUserInfoDao.deleteWebUserInfo(webUserInfoDomain,userId);
		
	}

	public void setWebUserInfoDao(IWebUserInfoDao webUserInfoDao) {
		this.webUserInfoDao = webUserInfoDao;
	}

	@Override
	public void auditWebUserInfo(WebUserInfoDomain webUserInfoDomain,
			String userId) {
		webUserInfoDao.auditWebUserInfo(webUserInfoDomain,userId);
	}
}
