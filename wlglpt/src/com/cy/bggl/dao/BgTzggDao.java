package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgTzggDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for �칫-֪ͨ����.
 * 
 * @author HJH
 */
public interface BgTzggDao extends ExtendDao {
	/**
	 * ɾ������
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void deleteFj(BgTzggDomain domain,UserDomain user)throws Exception;
	/**
	 * ��ѯ�����б�
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<BgTzggDomain> queryFj(BgTzggDomain domain,UserDomain user)throws Exception;
	
	/**
	 * ��ѯ����
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void queryFjByKey(BgTzggDomain domain,UserDomain user)throws Exception;
	
	/**
	 * �칫-֪ͨ����-���ļ�¼
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	


}
