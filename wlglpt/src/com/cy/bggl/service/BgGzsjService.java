package com.cy.bggl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for �칫-����ʱ��.
 * 
 * @author HJH
 */

public interface BgGzsjService extends BaseBusinessService {
	public void checkSave(BaseBusinessDomain baseBusinessDomain) throws Exception ;
}