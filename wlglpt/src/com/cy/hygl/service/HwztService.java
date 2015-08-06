package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 货物自提.
 * 
 * @author LYY
 */

public interface HwztService extends BaseBusinessService {

	public void register(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;

	
	public void delete(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
}