package com.cy.hygl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 开票申请.
 * 
 * @author HCM
 */

public interface JsKpDzqdService extends BaseBusinessService {
	/**
	 * 核销记录明细
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doMyInitHxMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	/**
	 * 核销记录列表明细
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doMyInitAddHxMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
}