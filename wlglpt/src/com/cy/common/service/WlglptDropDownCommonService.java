package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptDropDownCommonDomain;

public interface WlglptDropDownCommonService extends BaseBusinessService{

	/**
	 * ��������autocomplete��������
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryXzqhList(WlglptDropDownCommonDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description �����������������
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryXzqhInputSel(WlglptDropDownCommonDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * �ͻ�autocomplete��������
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHykhList(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	/**
	 * ����autocomplete��������
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHyhwList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * װ����ַautocomplete��������
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryZhdzList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * �ջ��˵�ַautocomplete��������
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHyShrDzList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * �ջ���λautocomplete��������
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHyShdwList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * ������Ϣautocomplete��������
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryQyClxx(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	public String[] queryQyGcxx(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	public String[] queryZrbmThShdz(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	public void queryGsDz(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
}
