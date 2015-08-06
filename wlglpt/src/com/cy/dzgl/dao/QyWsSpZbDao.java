package com.cy.dzgl.dao;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 企业-文书-审批-子表.
 * 
 * @author HJH
 */
public interface QyWsSpZbDao extends BaseDao {
	/**
	 * 更新企业-文书-审批-子表.
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 查找企业-文书-审批-子表
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 删除
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;


}
