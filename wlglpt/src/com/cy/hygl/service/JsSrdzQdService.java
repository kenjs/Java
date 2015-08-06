package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 结算-收入对帐-清单.
 * 
 * @author HJH
 */

public interface JsSrdzQdService extends BaseBusinessService {

	/**
	 *  检索费用登记记录
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryFydjList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 *  保存费用登记记录到清单
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveFydjMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 保存物流损失登记记录到清单
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveWlssMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 查看对账清单明细
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void viewDzQdMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
}