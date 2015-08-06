package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyFbsLxdjDomain;

/**
 * The DAO for 企业-分包商-路线登记.
 * 
 * @author HJH
 */
public interface QyFbsLxdjDao extends ExtendDao {

	/**
	 * 检查路线名称是否重复
	 * 
	 * @param qyFbsLxdjDomain
	 * @return
	 * @throws Exception
	 */
	public int checkLxmc(QyFbsLxdjDomain qyFbsLxdjDomain) throws Exception;
}
