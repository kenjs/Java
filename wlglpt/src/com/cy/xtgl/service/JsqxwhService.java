package com.cy.xtgl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * THE ACTION FOR ��ҵ-��֯���� ��ɫȨ��ά��
 * @author ��ΰ
 * @date 2013.1.17
*/ 	


public interface JsqxwhService extends BaseBusinessService {
	
	/**
	 * ��ȡ��ƴ���ַ���
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param domain
	* @throws Exception
	 */
	public void getTreeStr(BaseBusinessDomain domain,UserDomain user)throws Exception;
	/**
	 * ���ݽ�ɫ�����ȡ����ģ�����
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param baseBusinessDomain
	* @throws Exception
	 */
	public void getGnmkDmsByJsDm(BaseBusinessDomain domain)throws Exception;
	/**
	 * ���ݹ���ģ������ȡ��ɫƴ���ַ���
	* @Description: 
	* @Note
	* @author 
	* @since 2011-6-28
	* @param baseBusinessDomain
	* @throws Exception
	 */
	public void getJsInnerHtmlByGnmkDm(BaseBusinessDomain baseBusinessDomain)throws Exception;
	
	
	
}
