package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.cwgl.domain.CwZjrbSzmxDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 财务-资金日报.
 * 
 * @author HJH
 */
public interface CwZjrbDao extends ExtendDao {

	public void cxTjMx(BaseBusinessDomain baseDomain) throws Exception;
	
	public List<CwZjrbSzmxDomain> querySzmxList(BaseBusinessDomain baseDomain)  throws Exception;
}
