package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.xtgl.domain.QySwdnDshDomain;
/**
 * The DAO for ��ҵ-��������-�����.
 * 
 * @author HaoY
 */
public interface QySwdnDshDao extends ExtendDao {
	/**
	 * ���ͨ��
	 */
	public void pass(QySwdnDshDomain domain, UserDomain user) throws Exception;

	/**
	 * ��˲�ͨ��
	 */
	public void dispass(QySwdnDshDomain domain) throws Exception;
}
