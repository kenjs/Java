package com.cy.cwgl.service;



import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-֧���Ǽ�
 * 
 * @author LYY
 */

public interface CwZfdjService extends BaseBusinessService {

	/**
	 * ȡ���㷽����
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void getMcList(BaseBusinessDomain baseBusinessDomain)throws Exception;
	/**
	 * ȡ����
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doGetYh(BaseBusinessDomain baseBusinessDomain)throws Exception;
	/**
	 * ����
	 * @param baseBusinessDomain
	 * @throws Exception
	 */
	public void doCancle(BaseBusinessDomain baseBusinessDomain)throws Exception;
	
	public void plDj(BaseBusinessDomain baseBusinessDomain,UserDomain userDomain) throws Exception;
}