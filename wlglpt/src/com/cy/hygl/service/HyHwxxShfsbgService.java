package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 货运-货物信息-送货方式变更.
 * 
 * @author HJH
 */

public interface HyHwxxShfsbgService extends BaseBusinessService {
/**
 * 查看明细
 * @param baseBusinessDomain
 * @param userDomain
 * @throws Exception
 */
	public void viewMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}