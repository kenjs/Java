package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.framework.domain.BaseBusinessDomain;

public interface ExtendDao extends BaseDao {

	/**
	 * ��ѯ�б�
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryList(BaseBusinessDomain domain) throws Exception;
	
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
	
	/**
	 * ����������ȡdomain
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getDomainByKey(BaseBusinessDomain domain)throws Exception;

	/**
	 * �������������ݿ���ɾ��domain
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void deleteByKey(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;

	/**
	 * ����domain�����е����ԣ���Ҫ�������������ݿ��л�ȡ���󣬶�����Ϣ����domain��
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void initDomainMx(BaseBusinessDomain domain) throws Exception;
	
}
