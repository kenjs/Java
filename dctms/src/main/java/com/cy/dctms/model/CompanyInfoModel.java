package com.cy.dctms.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dctms.entity.CompanyInfo;
import com.cy.dctms.orm.MybatisDaoSurrport;

@Service
public class CompanyInfoModel {

	@Autowired
	private MybatisDaoSurrport mybatisDaoSurrport;
	
	public String queryIdByCompanyName(String name) throws Exception{
		return (String) mybatisDaoSurrport.queryObject("com.cy.dctms.entity.CompanyInfo.iBatisSelectIdByCompanyName", name);
	}
	
	public String queryIdByCompanyId(String id) throws Exception{
		return (String) mybatisDaoSurrport.queryObject("com.cy.dctms.entity.CompanyInfo.iBatisSelectIdByCompanyId", id);
	}
	
	public String addCompanyInfo(CompanyInfo bo) throws Exception{
		mybatisDaoSurrport.add("com.cy.dctms.entity.CompanyInfo.insertCompanyInfo", bo);
		return bo.getId();
	}
}
