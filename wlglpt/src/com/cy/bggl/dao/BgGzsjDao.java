package com.cy.bggl.dao;

import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.common.dao.ExtendDao;

/**
 * The DAO for 办公-工作时间.
 * 
 * @author HJH
 */
public interface BgGzsjDao extends ExtendDao {
	public int queryBgGzsjCount(BgGzsjDomain domain) throws Exception;

}
