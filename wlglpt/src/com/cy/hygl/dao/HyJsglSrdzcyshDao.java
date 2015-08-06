package com.cy.hygl.dao;

import java.util.List;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for ������˲������
 * 
 * @author HJH
 */
public interface HyJsglSrdzcyshDao extends BaseDao {
	/**
	 * ��ѯ������˲������list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain baseDomain,UserDomain userDomain)  throws Exception;
}
