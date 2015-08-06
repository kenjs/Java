package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;

/**
 * The SERVICE for 调度成本审核.
 * 
 * @author HJH
 */

public interface HyWlSsDjGlService extends BaseBusinessService {
	public void getHw(HyZyglFydjDomain domain, UserDomain userDomain) throws Exception;
}