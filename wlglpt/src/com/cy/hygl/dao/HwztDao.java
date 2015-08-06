package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HwztDomain;

/**
 * The DAO for ��������.
 * 
 * @author LYY
 */
public interface HwztDao extends BaseDao {

	/**
	 * ��ѯ�б�
	 * @return
	 * @throws Exception
	 */
	public List<HwztDomain> queryList(HwztDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public List<HwztDomain> downloadList(HwztDomain domain) throws Exception;

	/**
	 * ������޸�
	 * @param domain
	 * @throws Exception
	 */
	public void saveDomain(BaseBusinessDomain domain,UserDomain user)throws Exception;

	/**
	 * �Ǽ�
	 * @param domain
	 * @param userDomain
	 */
	public void register(BaseBusinessDomain domain,UserDomain user) throws Exception;
	
	/**
	 * ����
	 * @param domain
	 * @param userDomain
	 */
	public void delete(BaseBusinessDomain domain,UserDomain user) throws Exception;

}
