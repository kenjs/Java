package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-�������-�嵥.
 * 
 * @author HJH
 */

public interface JsSrdzQdService extends BaseBusinessService {

	/**
	 *  �������õǼǼ�¼
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryFydjList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 *  ������õǼǼ�¼���嵥
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveFydjMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ����������ʧ�ǼǼ�¼���嵥
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveWlssMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * �鿴�����嵥��ϸ
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void viewDzQdMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
}