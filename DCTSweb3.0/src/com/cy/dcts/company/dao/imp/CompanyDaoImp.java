package com.cy.dcts.company.dao.imp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.bo.CompanyInfo;
import com.cy.dcts.common.dao.BaseDao;
import com.cy.dcts.company.dao.ICompanyDao;

public class CompanyDaoImp extends BaseDao implements ICompanyDao{

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@SuppressWarnings("unchecked")
	public List<CompanyInfo> queryCompanyInfo(Map <String, Object> queryMap) {
		try {
			return (List<CompanyInfo>) queryForList("query_company_info",queryMap);
		} catch (Exception e) {
			logger.error("query_company_info error!",e);
			throw new RuntimeException();
		}
	}
	
	public CompanyInfo queryCompanyInfoById(String id) {
		try {
			return (CompanyInfo) queryForObject("query_company_info_by_id", id);
		} catch (SQLException e) {
			logger.error("query_web_user_info_by_id error!",e);
			throw new RuntimeException();
		}
	}
	
	public String addCompanyInfo(CompanyInfo companyInfo) {
		try {
			return addObjectKeyString("insert_company_info",companyInfo);
		}catch (Exception e) {
			logger.error("insert_company_info error!",e);
			throw new RuntimeException();
		}
	}

	public String addSonCompanyInfo(CompanyInfo companyInfo) {
		try {
			return addObjectKeyString("insert_son_company_info",companyInfo);
		}catch (Exception e) {
			logger.error("insert_son_company_info error!",e);
			throw new RuntimeException();
		}
	}

	
	public int modifyCompanyInfoById(CompanyInfo companyInfo) {
		try {
			return saveObject("modify_company_info_by_id",companyInfo);
		} catch (Exception e) {
			logger.error("modify_company_info_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public int deleteSonCompanyInfoById(CompanyInfo companyInfo) {
		try {
			return saveObject("delete_son_company_info_by_id",companyInfo);
		} catch (Exception e) {
			logger.error("delete_son_company_info_by_id error!",e);
			throw new RuntimeException();
		}
	}

	public int modifySonCompanyInfoById(CompanyInfo companyInfo) {
		try {
			return saveObject("modify_son_company_info_by_id",companyInfo);
		} catch (Exception e) {
			logger.error("modify_son_company_info_by_id error!",e);
			throw new RuntimeException();
		}
	}

	
}
