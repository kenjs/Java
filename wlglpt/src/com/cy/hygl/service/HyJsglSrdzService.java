package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 收入对账(订单).
 * 
 * @author HJH
 */

public interface HyJsglSrdzService extends BaseBusinessService {
	
	//批量对账
	public void plDz(BaseBusinessDomain baseBusinessDomain,
			UserDomain userDomain) throws Exception;

}