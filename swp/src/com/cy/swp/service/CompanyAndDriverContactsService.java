package com.cy.swp.service;

import java.util.List;

import com.cy.swp.bo.MarketingCargoCompanyContacts;
import com.cy.swp.bo.MarketingCargoDriverContacts;

public interface CompanyAndDriverContactsService {
	/**
	 * 根据联系人号码查询企业的回复记录
	 * @param contactTelephone 联系人号码
	 * @return
	 */
	MarketingCargoCompanyContacts queryCompanyContactByTelephone(String contactTelephone);
	
	/**
	 * 添加货源司机电话回访记录
	 * @param marketingCargoDriverContacts
	 * @return
	 */
	String addCargoDriverContacts(MarketingCargoDriverContacts marketingCargoDriverContacts);
	
	/**
	 * 添加货源企业电话回访记录
	 * @param marketingCargoCompanyContacts
	 * @return
	 */
	String addCargoCompanyContacts(MarketingCargoCompanyContacts marketingCargoCompanyContacts);
	
	
	/**
	 * 根据联系人号码查询企业的回复记录List集合
	 * @param contactMobilephone
	 * @return
	 */
	List<MarketingCargoCompanyContacts> queryCompanyContactByTelephoneList(String contactMobilephone);
}
