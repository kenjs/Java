package com.cy.cwgl.dao;



import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 财务-费用项目-维护.
 * 
 * @author HJH
 */
public interface CwFyxmWhDao extends ExtendDao {
	public List<BaseBusinessDomain> queryListById(BaseBusinessDomain domain,UserDomain user) throws Exception;

}
