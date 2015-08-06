package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.domain.XyjsSrdzQdMxDomain;

/**
 * The DAO for 下游结算-收入对帐-清单-明细.
 * 
 * @author HJH
 */
public interface XyjsSrdzQdMxDao extends ExtendDao {

	public void saveDomainTemp(XyjsSrdzQdMxDomain domain, UserDomain user) throws Exception;
	
	public void saveQdmxTempToFormal(String qdDjxh) throws Exception;
	
	public void deleteQdmxTempByQddjxh(String qdDjxh) throws Exception;
	
	public void deleteDomain(String qdDjxh, String ywDjxh, String ywMxXh) throws Exception;
	
	public void deleteDomainTemp(String qdDjxh, String ywDjxh, String ywMxXh) throws Exception;

}
