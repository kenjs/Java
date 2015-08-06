package com.cy.hygl.dao;
import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.XyjsZbYjdzShDomain;

/**
 * The DAO for ����-��Ʊ�Ǽ�
 * 
 * @author LYY
 */
public interface XyjsZbYjdzShDao  extends ExtendDao {
	public List<XyjsZbYjdzShDomain> queryAllList(BaseBusinessDomain baseDomain,UserDomain user)  throws Exception;
}
