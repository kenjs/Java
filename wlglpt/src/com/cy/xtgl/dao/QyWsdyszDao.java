package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 企业-文书打印设置.
 * 
 * @author HJH
 */
public interface QyWsdyszDao extends ExtendDao {
	public BaseBusinessDomain getDomainByWsdm(BaseBusinessDomain baseDomain) throws Exception;
}
