package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ªı‘À-≈‰‘ÿ.
 * 
 * @author HJH
 */

public interface HyPzService extends BaseBusinessService {

	public void saveWfhxx4Pz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void queryPzHwxx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void deleteWfhxx4Pz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void initPcxxFromPz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void onQingdan(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void viewPz(BaseBusinessDomain baseDomain) throws Exception;
	
	public void queryPzHw4View(BaseBusinessDomain baseDomain) throws Exception;
}