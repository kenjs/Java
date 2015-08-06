package com.cy.swp.dao;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.CompanyInfo;

@Repository("companyDao")
public interface CompanyDao {
	/**
	 * 根据公司电话查询 
	 * @param contactTelephone 公司电话
	 * @return
	 */
	CompanyInfo queryCompanyInfoByTelephone(String contactTelephone);
}
