package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyFbsLxdjDomain;

/**
 * The DAO for ��ҵ-�ְ���-·�ߵǼ�.
 * 
 * @author HJH
 */
public interface QyFbsLxdjDao extends ExtendDao {

	/**
	 * ���·�������Ƿ��ظ�
	 * 
	 * @param qyFbsLxdjDomain
	 * @return
	 * @throws Exception
	 */
	public int checkLxmc(QyFbsLxdjDomain qyFbsLxdjDomain) throws Exception;
}
