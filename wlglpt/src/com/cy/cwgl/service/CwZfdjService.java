package com.cy.cwgl.service;



import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 财务-支付登记
 * 
 * @author LYY
 */

public interface CwZfdjService extends BaseBusinessService {

	/**
	 * 取结算方名称
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void getMcList(BaseBusinessDomain baseBusinessDomain)throws Exception;
	/**
	 * 取银行
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doGetYh(BaseBusinessDomain baseBusinessDomain)throws Exception;
	/**
	 * 撤销
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doCancle(BaseBusinessDomain baseBusinessDomain)throws Exception;
	
	public void plDj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
}