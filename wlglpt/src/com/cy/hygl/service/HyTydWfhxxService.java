package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-���˵�-δ����(���)��Ϣ.
 * 
 * @author HJH
 */

public interface HyTydWfhxxService extends BaseBusinessService {
	public void doUpdate(BaseBusinessDomain baseBusinessDomain,UserDomain user) throws Exception;

}