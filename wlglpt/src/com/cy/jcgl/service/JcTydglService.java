package com.cy.jcgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.jcgl.domain.JcTydglDomain;

/**
 * The SERVICE for ����-���˵�����.
 * 
 * @author LYY
 */

public interface JcTydglService extends BaseBusinessService {

	/**
	 * ˢ�»����б�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void refreshHwList(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	public void queryJcTydPcxx(JcTydglDomain domain) throws Exception;
	
	public void queryJcSjcxClgzxx(JcTydglDomain domain) throws Exception;
	
	public void queryJcYfZfxx(JcTydglDomain domain) throws Exception;
	
	public void queryJcWlssxx(JcTydglDomain domain) throws Exception;
	
	public void queryWlssMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
}