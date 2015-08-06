package com.cy.xtgl.dao;

import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;

public interface XtDemoDao extends BaseDao {

	public List<DmbGgDomain> queryGsBmList(UserDomain userDomain) throws Exception;
	
}
