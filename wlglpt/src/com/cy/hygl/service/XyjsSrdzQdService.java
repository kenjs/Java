package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;

/**
 * The SERVICE for 下游结算-收入对帐-清单.
 * 
 * @author HJH
 */

public interface XyjsSrdzQdService extends BaseBusinessService {

	public void queryJsxxMx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void saveJsmx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void queryQdMx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void deleteQdmx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void queryHjjeCalculate(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void sendDzqd(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void initconfirm(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void querySydzqdConf(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void initSydzqdQrMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	public void saveSydzqdqr(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	public void viewQrMx(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	
}