package com.cy.zygl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ��ҵ-����-������Ϣ.
 * 
 * @author HJH
 */

public interface QyYlClxxShclService extends BaseBusinessService {

	/**
	 * ɾ��˾��
	 * @param domain
	 * @throws Exception
	 */
	public void deleteSjxx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	/**
	 * ˾����ϸ
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doMyInitSjMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
}