package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgGzlxDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for �칫-������ϵ.
 * 
 * @author HJH
 */
public interface BgGzlxDao extends ExtendDao {

	/**
	 * ��ȡ����
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void  getFjDomain(BgGzlxDomain domain) throws Exception;
	
	/**
	 * ɾ������
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void  deleteFjDomain(BgGzlxDomain domain) throws Exception;
	
	/**
	 * �޸Ľ�������Ϣ
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public void  updateJsrDomain(BgGzlxDomain domain) throws Exception;
	
	/**
	 * ��ȡ�ռ�����Ϣ
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryListForSjx(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * ��ȡ�ݸ�����Ϣ
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryListForCgx(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * �����ռ���
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadListForSjx(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * ���زݸ���
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadListForCgx(BaseBusinessDomain baseDomain) throws Exception;

}
