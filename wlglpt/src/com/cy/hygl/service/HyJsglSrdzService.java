package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for �������(����).
 * 
 * @author HJH
 */

public interface HyJsglSrdzService extends BaseBusinessService {
	
	//��������
	public void plDz(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception;

}