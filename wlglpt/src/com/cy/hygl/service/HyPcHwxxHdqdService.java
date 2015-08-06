package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-�ɳ�-������Ϣ-�ص��嵥.
 * 
 * @author HJH
 */

public interface HyPcHwxxHdqdService extends BaseBusinessService {
	/**
	 * �������
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void dbfs(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ���嵥
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void doQueryQd(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * �嵥����
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void qdjs(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * �嵥�˻�
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void qdth(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * ���嵥�лص�
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryHdByQd(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}