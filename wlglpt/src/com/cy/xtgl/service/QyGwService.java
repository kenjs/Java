package com.cy.xtgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The SERVICE for 企业-岗位.
 * 
 * @author HaoY
 */

public interface QyGwService extends BaseBusinessService {

	/**
	 *检索所有的岗位
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void initXtgwMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	/**
	 * 保存岗位
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void saveXtgw(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
}