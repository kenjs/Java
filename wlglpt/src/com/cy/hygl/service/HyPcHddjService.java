package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-�ɳ�-�ص�.
 * 
 * @author HJH
 */

public interface HyPcHddjService extends BaseBusinessService {

	/**
	 * �ص���������
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void plSave(BaseBusinessDomain baseBusinessDomain) throws Exception;
	
	public void initViewMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void plJs(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
	public void saveWlssDj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
}