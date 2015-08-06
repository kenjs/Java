package com.cy.dzgl.dao;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for ��ҵ-����-����-�ӱ�.
 * 
 * @author HJH
 */
public interface QyWsSpZbDao extends BaseDao {
	/**
	 * ������ҵ-����-����-�ӱ�.
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * ������ҵ-����-����-�ӱ�
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * ɾ��
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;


}
