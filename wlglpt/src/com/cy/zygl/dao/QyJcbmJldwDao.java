package com.cy.zygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.domain.QyJcbmJldwDomain;

/**
 * The DAO for ��ҵ-��������-������λ.
 * 
 * @author HaoY
 */
public interface QyJcbmJldwDao extends ExtendDao {

	/**
	 * �������м�����λ
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<QyJcbmJldwDomain> queryAllJldw(BaseBusinessDomain domain) throws Exception;

}
