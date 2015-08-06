package com.cy.bggl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 办公-日程安排.
 * 
 * @author HJH
 */

public interface BgRcapService extends BaseBusinessService {
	
	/**
	 * 根据日期获取信息
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryForDate(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception; 
}