package com.cy.xtgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ��ҵ-�����ӡ����.
 * 
 * @author HJH
 */

public interface QyWsdyszService extends BaseBusinessService {
	public void selectBj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}