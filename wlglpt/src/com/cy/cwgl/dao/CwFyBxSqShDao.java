package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.domain.CwFybxsqShDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for 财务-开票登记
 * 
 * @author LYY
 */
public interface CwFyBxSqShDao  extends ExtendDao {
	
	public List<CwFybxsqShDomain> queryAllList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception;
	

}
