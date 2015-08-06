package com.cy.zygl.service;

import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 企业-运力-车辆信息.
 * 
 * @author HJH
 */

public interface QyYlClxxShclService extends BaseBusinessService {

	/**
	 * 删除司机
	 * @param domain
	 * @throws Exception
	 */
	public void deleteSjxx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	/**
	 * 司机明细
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doMyInitSjMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
}