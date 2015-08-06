package com.cy.zyegl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-�ɳ�-Э��Ǽ�.
 * 
 * @author HJH
 */

public interface HyPcXydjService extends BaseBusinessService {

	public void initXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void initHwxxXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void saveHwmxXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void deleteHwmxXydj(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void initXybd(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
}