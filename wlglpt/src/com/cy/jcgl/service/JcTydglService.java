package com.cy.jcgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.jcgl.domain.JcTydglDomain;

/**
 * The SERVICE for 货运-托运单管理.
 * 
 * @author LYY
 */

public interface JcTydglService extends BaseBusinessService {

	/**
	 * 刷新货物列表
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