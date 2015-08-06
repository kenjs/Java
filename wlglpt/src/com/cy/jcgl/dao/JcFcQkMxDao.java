package com.cy.jcgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICEIMP for 货运-派车信息管理
 * time  2013-5-4
 * @author LYY
 */

public interface JcFcQkMxDao extends ExtendDao {
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception;
	
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseDomain,UserDomain user) throws Exception;
}
