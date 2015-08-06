package com.cy.zygl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 企业-分包商-结算价格.
 * 
 * @author HJH
 */

public interface QyFbsJsjgService extends BaseBusinessService {
	
	public void checkSave(BaseBusinessDomain baseBusinessDomain) throws Exception ;
}