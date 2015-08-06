package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyMbTydDomain;

/**
 * The DAO for ����-ģ��-���˵�.
 * 
 * @author HJH
 */
public interface HyMbTydDao extends ExtendDao {

	/**
	 * @description ���˵�����Ϊģ��
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveAsTemplate(HyMbTydDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description ���ͬһ��˾��ģ�������Ƿ��ظ�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void checkTemplateName(HyMbTydDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description �������˵�����-ģ��ѡ��
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryMb4Tydgl(HyMbTydDomain domain, UserDomain userDomain) throws Exception;
	
}
