package com.cy.hygl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ��Ʊ����.
 * 
 * @author HCM
 */

public interface JsKpDzqdService extends BaseBusinessService {
	/**
	 * ������¼��ϸ
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doMyInitHxMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	/**
	 * ������¼�б���ϸ
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doMyInitAddHxMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
}