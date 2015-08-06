package com.cy.xtgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The SERVICE for ��ҵ-��λ.
 * 
 * @author HaoY
 */

public interface QyGwService extends BaseBusinessService {

	/**
	 *�������еĸ�λ
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void initXtgwMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	/**
	 * �����λ
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void saveXtgw(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
}