package com.cy.bggl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The SERVICE for �칫-������ϵ.
 * 
 * @author HJH
 */

public interface BgGzlxService extends BaseBusinessService {
	/**
	 * ��ȡ������Ϣ
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryFjDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ��ȡ�ռ�����Ϣ
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryForSjxDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ��ȡ�ݸ�����Ϣ
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryForCgxDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ɾ��������Ϣ
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteFjDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * �޸Ľ�������Ϣ
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void updateJsrDomain(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void doMyDownloadForSjx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void doMyDownloadForCgx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}