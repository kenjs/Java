package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 财务-货币资产-转换记录.
 * 
 * @author HJH
 */
public interface CwHbzcZhjlDao extends ExtendDao {

	public void saveZcBdjl(BaseBusinessDomain businessDomain,UserDomain user) throws Exception;
	
	public String getYe(BaseBusinessDomain businessDomain) throws Exception;
	
	public void updateYe(BaseBusinessDomain businessDomain) throws Exception;
	
	public String getYhCshDjxh(BaseBusinessDomain businessDomain) throws Exception;

	public void deleteByYwxh(String ywxh) throws Exception;
}
