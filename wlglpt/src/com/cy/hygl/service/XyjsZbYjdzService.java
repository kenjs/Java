package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ���ν���-ת��-�½����.
 * 
 * @author XIAY
 */

public interface XyjsZbYjdzService extends BaseBusinessService {
	//��������
	public void plDz(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
	
	//��ӡԤ��
	public void dyyl(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void viewMx(BaseBusinessDomain baseDomain) throws Exception;
}