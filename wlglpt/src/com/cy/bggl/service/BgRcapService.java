package com.cy.bggl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for �칫-�ճ̰���.
 * 
 * @author HJH
 */

public interface BgRcapService extends BaseBusinessService {
	
	/**
	 * �������ڻ�ȡ��Ϣ
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryForDate(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception; 
}