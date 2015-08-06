package com.cy.swp.dao;

import org.springframework.stereotype.Repository;

import com.cy.swp.bo.MarketingCargoAssist;

@Repository("marketingCargoAssistDao")
public interface MarketingCargoAssistDao {
	/**
	 * 导入货源时向货源专员协助表
	 * @param marketingCargoAssist bo
	 * @return
	 */
	 int addCargoAssist(MarketingCargoAssist marketingCargoAssist);
	
	 /**
	  * 修改 货源专员协助表信息
	  * @param marketingCargoAssist
	  * @return
	  */
	 boolean updateMarketingCargoAssist(MarketingCargoAssist marketingCargoAssist);
}
