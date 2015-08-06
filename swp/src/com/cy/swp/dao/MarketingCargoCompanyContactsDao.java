package com.cy.swp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.MarketingCargoCompanyContacts;

@Repository("marketingCargoCompanyContactsDao")
public interface MarketingCargoCompanyContactsDao {
	/**
	 * 修改公司回复结果
	 * @param id
	 * @return
	 */
	boolean updateCompanyContacts(MarketingCargoCompanyContacts marketingCargoCompanyContacts);
	
	/**
	 * 添加货源企业电话回访记录
	 * @param marketingCargoCompanyContacts
	 * @return
	 */
	int addCargoCompanyContacts(MarketingCargoCompanyContacts marketingCargoCompanyContacts);
	
	/**
	 * 根据联系人号码查询企业的回复记录
	 * @param contactTelephone 联系人号码
	 * @return
	 */
	MarketingCargoCompanyContacts queryCompanyContactByTelephone(String contactMobilephone);
	
	
	/**
	 * 根据联系人号码查询企业的回复记录List集合
	 * @param contactMobilephone
	 * @return
	 */
	List<MarketingCargoCompanyContacts> queryCompanyContactByTelephoneList(String contactMobilephone);
}
