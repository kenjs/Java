package com.cy.dcts.company.service;

import java.util.List;

import com.cy.dcts.common.bo.CompanyInfo;

public interface ICompanyService {

	
	/**
	 * 查询对象
	 * 单个或多个条件查询
	 * @author nxj
	 * @param companyInfo
	 * @return
	 */
	public List<CompanyInfo> queryCompanyInfo(CompanyInfo companyInfo);
	
	
	
	
	/**
	 * 查询对象
	 * 单个或多个条件查询
	 * @author nxj
	 * @param companyInfo
	 * @return
	 */
	public List<CompanyInfo> querySonCompanyInfo(CompanyInfo companyInfo);
	
	
	
	/**
	 * 注册新增
	 * @author nxj
	 * @param companyInfo
	 * @return
	 */
	public String addCompanyInfo(CompanyInfo companyInfo);
	
	/**
	 * 维护子账号新增
	 * @author nxj
	 * @param companyInfo
	 * @return
	 */
	public String addSonCompanyInfo(CompanyInfo companyInfo);
	
	
	/**
	 * 根据Id修改公司信息
	 * @author nxj
	 * @param companyInfo
	 * @return
	 */
	public boolean modifyCompanyInfoById(CompanyInfo companyInfo);
	/**
	 * 根据Id查询公司
	 * 
	 */
	CompanyInfo queryCompanyInfoById(String id);
	
	
	public boolean modifySonCompanyInfoById(CompanyInfo companyInfo); 
	
	
	public boolean deleteSonCompanyInfoById(CompanyInfo companyInfo);
}
