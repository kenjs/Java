package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for ��ҵ-��Ա��λ.
 * 
 * @author HaoY
 */
public interface QyRyGwDao extends ExtendDao {
	/**
	 * У���û���λ�Ƿ��ظ�
	 */
	public int checkYhgw(BaseBusinessDomain baseDomain) throws Exception;
}
