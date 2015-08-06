package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.DdcbshDomain;

/**
 * The DAO for ���ȳɱ����.
 * 
 * @author HJH
 */
public interface DdcbshDao extends BaseDao {

	/**
	 * ��ѯ�б�
	 * @return
	 * @throws Exception
	 */
	public List<DdcbshDomain> queryList(DdcbshDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public List<DdcbshDomain> downloadList(DdcbshDomain domain) throws Exception;

	/**
	 * ������޸�
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;

}
