package com.cy.dzgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ��ҵ-��������-������������.
 * 
 * @author anq
 */

public interface QySpwsSplcszService extends BaseBusinessService {

	/**
	 * 
	* @Description: ��ҵ-��������-�������̽ڵ����� ҳ���ʼ�� 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void initSpjcMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description:  ��ҵ-��������-�������̽ڵ����� ����
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void saveSpjc(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: ɾ�� ��ҵ-��������-��������-�ӱ� ��������������һ����¼
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void deleteSplcZb(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: ������������ ��ҵ-��������  �����õ���Ŀ�����־ 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseBusinessDomain
	* @param userDomain
	* @throws Exception
	 */
	public void queryQySpwsXmflbzByKey(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception ;
	
}