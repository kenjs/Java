package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-������Ϣ-�ͻ���ʽ���.
 * 
 * @author HJH
 */

public interface HyHwxxShfsbgService extends BaseBusinessService {
/**
 * �鿴��ϸ
 * @param baseBusinessDomain
 * @param userDomain
 * @throws Exception
 */
	public void viewMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}