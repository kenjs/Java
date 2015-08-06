package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.XtszDomain;

public interface XtszDao extends BaseDao {

	public boolean checkCzypwd(XtszDomain domain,UserDomain userDomain) throws Exception;
	
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	public void initDomainMx(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	public List<XtszDomain> queryXzxmList() throws Exception;
}
