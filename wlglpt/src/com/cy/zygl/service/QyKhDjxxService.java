package com.cy.zygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.zygl.domain.QyKhDjxxDomain;

/**
 * The SERVICE for 企业-客户-登记信息.
 * 
 * @author HJH
 */

public interface QyKhDjxxService extends BaseBusinessService {
	/**
	 * 校验客户名称是否重复
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void check(QyKhDjxxDomain domain, UserDomain userDomain) throws Exception;
	
	public String[] queryXzqhList(QyKhDjxxDomain domain, UserDomain userDomain) throws Exception;

}