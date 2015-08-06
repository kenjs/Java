package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The SERVICE for 费用登记管理.
 * 
 * @author hy
 */
public interface HyFydjglService extends BaseBusinessService{

	public void getKhHwXx(BaseBusinessDomain baseBusinessDomain)throws Exception ;
	
	public void deleteFydj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;

}
