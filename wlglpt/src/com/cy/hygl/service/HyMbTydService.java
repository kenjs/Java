package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for ����-ģ��-���˵�.
 * 
 * @author HJH
 */

public interface HyMbTydService extends BaseBusinessService {
	
	/**
	 * ˢ�»����б�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void refreshHwList(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;

	/**
	 * @description �����˵�����Ϊģ��
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveAsTemplate(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description ���ͬһ��˾��ģ�������Ƿ��ظ�
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void checkTemplateName(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description ��ʼ�����˵�����-ģ��ѡ��ҳ��
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initMb4Tydgl(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description �������˵�����-ģ��ѡ��
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryMb4Tydgl(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description ����ģ���ʼ�����˵�
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initTydByMb(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	
	public void initHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	public void deleteHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	public void saveHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
}