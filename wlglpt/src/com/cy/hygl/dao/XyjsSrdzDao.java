package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.domain.XyjsSrdzDomain;
import com.cy.hygl.domain.XyjsSrdzQdDomain;

/**
 * The DAO for 下游结算-收入对帐.
 * 
 * @author HJH
 */
public interface XyjsSrdzDao extends ExtendDao {

	public List<XyjsSrdzQdDomain> queryDzqdList(XyjsSrdzDomain domain,UserDomain user) throws Exception;
	
	public void savePldz(XyjsSrdzDomain domain,UserDomain user) throws Exception;
	
	public XyjsSrdzDomain initDzxxByJsxx(String ywDjxh, String ywMxXh) throws Exception;

}
