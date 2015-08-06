package com.cy.dcts.company.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.company.dao.ICompanyDao;
import com.cy.dcts.company.service.ICompanyService;

public class CompanyServiceImp implements ICompanyService {
	
	private ICompanyDao companyDao;

	public List<CompanyInfo> queryCompanyInfo(CompanyInfo companyInfo) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", companyInfo.getId());
		queryMap.put("companyName", companyInfo.getCompanyName());
		queryMap.put("deletedFlag", companyInfo.getDeletedFlag());
		queryMap.put("parentCompanyId", "0");
		return companyDao.queryCompanyInfo(queryMap);
	}
	
	public List<CompanyInfo> querySonCompanyInfo(CompanyInfo companyInfo) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", companyInfo.getId());
		queryMap.put("companyName", companyInfo.getCompanyName());
		queryMap.put("deletedFlag", companyInfo.getDeletedFlag());
		queryMap.put("parentCompanyId", companyInfo.getParentCompanyId());
		queryMap.put("companyType", companyInfo.getCompanyType());
		return companyDao.queryCompanyInfo(queryMap);
	}

	public String addCompanyInfo(CompanyInfo companyInfo) {
		return companyDao.addCompanyInfo(companyInfo);
	}
	
	public String addSonCompanyInfo(CompanyInfo companyInfo) {
		return companyDao.addSonCompanyInfo(companyInfo);
	}

	public boolean modifyCompanyInfoById(CompanyInfo companyInfo) {
		return companyDao.modifyCompanyInfoById(companyInfo) == 1;
	}

	public CompanyInfo queryCompanyInfoById(String id) {
		return companyDao.queryCompanyInfoById(id);
	}
	
	public ICompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(ICompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public boolean deleteSonCompanyInfoById(CompanyInfo companyInfo) {
		return companyDao.deleteSonCompanyInfoById(companyInfo) == 1;
	}

	public boolean modifySonCompanyInfoById(CompanyInfo companyInfo) {
		return companyDao.modifySonCompanyInfoById(companyInfo) == 1;
	}

}
