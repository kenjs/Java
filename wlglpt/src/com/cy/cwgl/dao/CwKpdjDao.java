package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;
/**
 * The DAO for ����-�����ʲ���ˮ��¼
 * 
 * @author HCM
 */
public interface CwKpdjDao  extends ExtendDao {

	/**
	 * ����Ʊ���Ϻ�������
	 * @param businessDomain
	 * @throws Exception
	 */
	public void cwKpdjZfhxcl(String kpDjxh)throws Exception; 
}
