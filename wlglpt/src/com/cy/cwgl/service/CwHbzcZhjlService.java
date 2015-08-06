package com.cy.cwgl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-货币资产-转换记录.
 * 
 * @author HJH
 */

public interface CwHbzcZhjlService extends BaseBusinessService {

	public void initOldYe(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	public void initNewYe(BaseBusinessDomain baseBusinessDomain) throws Exception;
}