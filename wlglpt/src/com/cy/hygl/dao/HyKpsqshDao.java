package com.cy.hygl.dao;

import java.util.List;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for ����-��Ʊ�������.
 * 
 * @author LYY
 */
public interface HyKpsqshDao extends BaseDao {

	/**
	 * ��ѯ�б�
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain,UserDomain userDomain) throws Exception;

}
