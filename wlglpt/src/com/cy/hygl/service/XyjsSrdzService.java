package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ���ν���-�������.
 * 
 * @author HJH
 */

public interface XyjsSrdzService extends BaseBusinessService {

	public void queryDzqdList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void savePldz(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
}