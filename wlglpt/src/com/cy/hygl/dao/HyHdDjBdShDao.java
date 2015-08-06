package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for ���ȳɱ����.
 * 
 * @author HJH
 */
public interface HyHdDjBdShDao extends BaseDao {

	/**
	 * ��ѯ�б�
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain,UserDomain use) throws Exception;
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain domain) throws Exception;

	/**
	 * ������޸�
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;

}
