package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 企业-人员岗位.
 * 
 * @author HaoY
 */
public interface QyRyGwDao extends ExtendDao {
	/**
	 * 校验用户岗位是否重复
	 */
	public int checkYhgw(BaseBusinessDomain baseDomain) throws Exception;
}
