package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for �������Թ���.
 * 
 * @author HaoY
 */
public interface QySwdnDao extends ExtendDao{
	/**
	 * ͣ��
	 */
	public void stopUse(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * ����
	 */
	public void startUse(BaseBusinessDomain domain) throws Exception;
}
