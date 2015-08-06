package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyFbsJsjgDomain;

/**
 * The DAO for 企业-分包商-结算价格.
 * 
 * @author HJH
 */
public interface QyFbsJsjgDao extends ExtendDao {

	public int queryQyFbsJsjgCount(QyFbsJsjgDomain domain) throws Exception;
}
