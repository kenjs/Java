package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 财务-周转金下拨.
 * 
 * @author HJH
 */
public interface CwZzjxbDao extends ExtendDao {

	public void getCwZzjxbxxWhenAdd(BaseBusinessDomain baseDomain) throws Exception;
	
	public void callPCwglZzjxbHxcl(String zjbdDjxh, String ssJgbm,String czyDjxh) throws Exception;
	
}
