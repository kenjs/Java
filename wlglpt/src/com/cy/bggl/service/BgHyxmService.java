package com.cy.bggl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for �칫-��ҵ����.
 * 
 * @author HJH
 */

public interface BgHyxmService extends BaseBusinessService {
	
	public void queryFj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;

}