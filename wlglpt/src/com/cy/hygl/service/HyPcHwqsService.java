package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 货运-派车-货物签收.
 * 
 * @author HJH
 */

public interface HyPcHwqsService extends BaseBusinessService {

	public void initPsfMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	public void savePsf(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
	public void checkWlssSfDj(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
}