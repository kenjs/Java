package com.cy.common.service;

import com.cy.common.domain.RyxzCommonDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.service.BaseService;

public interface RyxzCommonService extends BaseService {
	
	/**
	 * ���ڻ�ȡ��Ա�ķ���
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryRyList(RyxzCommonDomain domain, UserDomain userDomain) throws Exception;


	
}
