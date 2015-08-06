package com.cy.cwgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-资金日报.
 * 
 * @author HJH
 */

public interface CwZjrbService extends BaseBusinessService {
	public void cxTjMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void qtsrMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void doMySaveQtsr(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void deleteQtsr(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
	public void querySzMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}