package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgZlgxDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;

/**
 * The DAO for �칫-���Ϲ���.
 * 
 * @author HJH
 */
public interface BgZlgxDao extends ExtendDao {

	/**
	 * ɾ������
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void deleteFj(BgZlgxDomain domain,UserDomain user)throws Exception;
	/**
	 * ��ѯ�����б�
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<BgZlgxDomain> queryFj(BgZlgxDomain domain,UserDomain user)throws Exception;
	
	/**
	 * ��ѯ����
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void queryFjByKey(BgZlgxDomain domain,UserDomain user)throws Exception;
	

}
