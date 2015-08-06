package com.cy.zygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.zygl.domain.QyKhDjxxDomain;

/**
 * The SERVICE for ��ҵ-�ͻ�-�Ǽ���Ϣ.
 * 
 * @author HJH
 */

public interface QyKhDjxxService extends BaseBusinessService {
	/**
	 * У��ͻ������Ƿ��ظ�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void check(QyKhDjxxDomain domain, UserDomain userDomain) throws Exception;
	
	public String[] queryXzqhList(QyKhDjxxDomain domain, UserDomain userDomain) throws Exception;

}