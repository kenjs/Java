package com.cy.bggl.dao;

import com.cy.bggl.domain.BgRcapDomain;
import com.cy.common.dao.ExtendDao;

/**
 * The DAO for �칫-�ճ̰���.
 * 
 * @author HJH
 */
public interface BgRcapDao extends ExtendDao {

	public void  queryForDate(BgRcapDomain domain) throws Exception;
}
