package com.cy.bggl.dao;

import com.cy.bggl.domain.BgRcapDomain;
import com.cy.common.dao.ExtendDao;

/**
 * The DAO for 办公-日程安排.
 * 
 * @author HJH
 */
public interface BgRcapDao extends ExtendDao {

	public void  queryForDate(BgRcapDomain domain) throws Exception;
}
