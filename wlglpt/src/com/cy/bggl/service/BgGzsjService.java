package com.cy.bggl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 办公-工作时间.
 * 
 * @author HJH
 */

public interface BgGzsjService extends BaseBusinessService {
	public void checkSave(BaseBusinessDomain baseBusinessDomain) throws Exception ;
}