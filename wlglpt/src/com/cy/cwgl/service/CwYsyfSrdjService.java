package com.cy.cwgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.cwgl.domain.CwYsyfSrdjDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The SERVICE for 财务-收入登记
 * 
 * @author HCM
 */
public interface CwYsyfSrdjService extends BaseBusinessService{
	/**
	 * 取结算方名称
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void getMcList(BaseBusinessDomain baseBusinessDomain)throws Exception;
	/**
	 * 撤销
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doCancle(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain)throws Exception;
	
	public void checkZfFs(CwYsyfSrdjDomain domain)throws Exception;
	
	public void plDj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
}
