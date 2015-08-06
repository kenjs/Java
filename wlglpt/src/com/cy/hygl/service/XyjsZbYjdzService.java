package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 下游结算-转包-月结对账.
 * 
 * @author XIAY
 */

public interface XyjsZbYjdzService extends BaseBusinessService {
	//批量对账
	public void plDz(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
	//打印预览
	public void dyyl(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void viewMx(BaseBusinessDomain baseDomain) throws Exception;
}