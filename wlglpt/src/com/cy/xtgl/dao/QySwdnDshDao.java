package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.xtgl.domain.QySwdnDshDomain;
/**
 * The DAO for 企业-上网电脑-待审核.
 * 
 * @author HaoY
 */
public interface QySwdnDshDao extends ExtendDao {
	/**
	 * 审核通过
	 */
	public void pass(QySwdnDshDomain domain, UserDomain user) throws Exception;

	/**
	 * 审核不通过
	 */
	public void dispass(QySwdnDshDomain domain) throws Exception;
}
