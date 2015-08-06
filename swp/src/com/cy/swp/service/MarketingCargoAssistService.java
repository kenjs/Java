package com.cy.swp.service;

import com.cy.swp.bo.MarketingCargoAssist;
import com.cy.swp.bo.MarketingCargoCompanyContacts;
import com.cy.swp.domain.MarketingCargoAssistDomain;

public interface MarketingCargoAssistService {
	/**
	 * 保存公司和该公司下的货源回复记录
	 * @param marketingCargoAssistDomain 专员协助domain
	 * @param MarketingCargoCompanyContacts 公司电话记录bo
	 */
	String saveCompAndCargoContacts(MarketingCargoAssist marketingCargoAssist,String userId);
	
	
	
	/**
	 * 保存公司回复记录
	 * @param marketingCargoAssistDomain 专员协助domain
	 * @param MarketingCargoCompanyContacts 公司电话记录bo
	 */
	void saveCompContacts(MarketingCargoCompanyContacts marketingCargoCompanyContacts,String userId);
	
	/**
	  * 修改 货源专员协助表信息
	  * @param marketingCargoAssist
	  * @return
	  */
	 boolean updateMarketingCargoAssist(MarketingCargoAssist marketingCargoAssist);
}
