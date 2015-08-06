package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.domain.HyPzDomain;
import com.cy.hygl.domain.HyPzHwxxDomain;

/**
 * The DAO for 货运-配载-货物信息.
 * 
 * @author HJH
 */
public interface HyPzHwxxDao extends ExtendDao {
	
	public void deleteByKey(String pzDjxh, String wfhDjxh) throws Exception;

	public void deleteWfhxxTmp4Pz(HyPzDomain domain) throws Exception;
	
	public void updateWfhxx4Pz(HyPzHwxxDomain domain) throws Exception;
	
	public void savePcHwxxTmp(HyPzHwxxDomain domain, UserDomain user) throws Exception;
	
	public void savePcHwxxTmpFromSavedPz(HyPzHwxxDomain pzHwxxDomain) throws Exception;
	
}
