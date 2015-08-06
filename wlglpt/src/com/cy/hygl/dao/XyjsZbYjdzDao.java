package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyJsglSrdzDomain;

/**
 * The DAO for 下游结算-转包-月结对账.
 * 
 * @author XIAY
 */
public interface XyjsZbYjdzDao extends ExtendDao {

	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain)  throws Exception;
	
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain) throws Exception;
	
	public void saveDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain baseDomain) throws Exception;
	
	public void deleteByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void initDomainMx(BaseBusinessDomain baseDomain) throws Exception;
	
}
