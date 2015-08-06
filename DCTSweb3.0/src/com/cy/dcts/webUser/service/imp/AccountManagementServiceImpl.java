package com.cy.dcts.webUser.service.imp;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.bo.WebUserInfo;
import com.cy.dcts.common.domain.WebUserInfoDamain;
import com.cy.dcts.company.dao.ICompanyDao;
import com.cy.dcts.webUser.dao.IWebUserInfoDao;
import com.cy.dcts.webUser.service.AccountManagementService;

public class AccountManagementServiceImpl implements AccountManagementService {
	
	private IWebUserInfoDao webUserInfoDao;
	private ICompanyDao companyDao;

	public WebUserInfo initUserInfor(String code) {
		
		return webUserInfoDao.queryWebUserInfoByCode(code);
	}
	
	public WebUserInfoDamain initAccoutInfo(String id,String companyId) {
		WebUserInfoDamain domain = new WebUserInfoDamain();
		CompanyInfo companyInfo = null;
		WebUserInfo webUserInfo = webUserInfoDao.queryWebUserInfoById(id);
		
		if(StringUtils.isNotBlank(companyId)) {
			companyInfo = companyDao.queryCompanyInfoById(companyId);
			//页面显示拼装省-市-区
			if(StringUtils.isNotEmpty(companyInfo.getCompanyCounty())){
				domain.setProvinceCityCountyStr(companyInfo.getCompanyProvince()+"-"+companyInfo.getCompanyCity()+"-"+companyInfo.getCompanyCounty());
			}else if(StringUtils.isNotEmpty(companyInfo.getCompanyCity())){
				domain.setProvinceCityCountyStr(companyInfo.getCompanyProvince()+"-"+companyInfo.getCompanyCity());	
			}else{
				domain.setProvinceCityCountyStr(companyInfo.getCompanyProvince());	
			}
			
			domain.setCompanyName(companyInfo.getCompanyName());
			domain.setCompanyAddress(companyInfo.getCompanyAddress());
			domain.setBusinessLicence(companyInfo.getBusinessLicence());
			domain.setOrganizationCode(companyInfo.getOrganizationCode());
			domain.setContactTelephone(companyInfo.getContactTelephone());
		}
		
		domain.setName(webUserInfo.getName());
		domain.setEmail(webUserInfo.getEmail());
		domain.setUserQq(webUserInfo.getUserQq());
		
		return domain;
	}
	
	@Transactional
	public int updateAccountInfo(WebUserInfoDamain domain) {
		CompanyInfo comBo = new CompanyInfo();
		WebUserInfo webUserBo = new WebUserInfo();
		int i1= 0,i2 = 0;
		comBo.setId(domain.getCompanyId());
		comBo.setBusinessLicence(domain.getBusinessLicence());
		comBo.setOrganizationCode(domain.getOrganizationCode());
		comBo.setCompanyAddress(domain.getCompanyAddress());
		//comBo.setCompanyName(domain.getCompanyName());
		comBo.setContactTelephone(domain.getContactTelephone());
		comBo.setCompanyProvince(domain.getCompanyProvince());
		comBo.setCompanyCity(domain.getCompanyCity());
		comBo.setCompanyCounty(domain.getCompanyCounty());
		
		webUserBo.setId(domain.getId());
		webUserBo.setName(domain.getName());
		webUserBo.setUserQq(domain.getUserQq());
		webUserBo.setEmail(domain.getEmail());
		
		i1 = companyDao.modifyCompanyInfoById(comBo);
		i2 = webUserInfoDao.modifyWebUserInfoById(webUserBo);
		
		return i1 * i2;
	}

	public IWebUserInfoDao getWebUserInfoDao() {
		return webUserInfoDao;
	}

	public void setWebUserInfoDao(IWebUserInfoDao webUserInfoDao) {
		this.webUserInfoDao = webUserInfoDao;
	}

	public void setCompanyDao(ICompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
}
