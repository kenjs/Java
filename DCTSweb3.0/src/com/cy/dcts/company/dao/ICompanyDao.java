package com.cy.dcts.company.dao;

import java.util.List;
import java.util.Map;

import com.cy.dcts.common.bo.CompanyInfo;

public interface ICompanyDao {
	
	/**
	 * 查询对象
	 * 单个或多个条件查询
	 * @author nxj
	 * @param queryMap
	 * @return
	 */
	public List<CompanyInfo> queryCompanyInfo(Map <String, Object> queryMap);
	
	/**
	 * 根据公司id查找信息
	 * @author haoyong
	 * @param id
	 * @return
	 */
	public CompanyInfo queryCompanyInfoById(String id);
	
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
	public int modifyCompanyInfoById(CompanyInfo companyInfo);

	
	public int modifySonCompanyInfoById(CompanyInfo companyInfo); 
	
	
	public int deleteSonCompanyInfoById(CompanyInfo companyInfo);
}
