package com.cy.zygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 企业-车辆型号维护.
 * 
 * @author HJH
 */

public interface QyClxhwhService extends BaseBusinessService {

	public void queryClxhByKey(BaseBusinessDomain domain, UserDomain user) throws Exception;
	
}