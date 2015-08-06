package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.cwgl.domain.CwFybxsqDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for 财务-开票登记
 * 
 * @author LYY
 */
public interface CwFyBxSqDao  extends ExtendDao {
	
	public List<BaseBusinessDomain> queryAllList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception;
	
	public List<BaseBusinessDomain> queryMxList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception;
	
	public void saveMx(BaseBusinessDomain baseDomain,UserDomain user,CwFybxsqDomain domain) throws Exception;
	
	public void updateMx(BaseBusinessDomain baseDomain,UserDomain user,CwFybxsqDomain domain) throws Exception;
	
	public void deleteMx(BaseBusinessDomain baseDomain,UserDomain user,CwFybxsqDomain domain) throws Exception;
	
	
	public String checkXmfl(BaseBusinessDomain baseDomain) throws Exception;
	
	public String getMcByJgbm(String jgbm) throws Exception;
}
