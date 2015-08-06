package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcDhajDomain;

/**
 * The DAO for ����-�ɳ�-�绰����.
 * 
 * @author HJH
 */
public interface HyPcDhajDao extends ExtendDao {

	/**
	 * �������еİ���绰
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<HyPcDhajDomain> queryAjdhList(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * �������еİ�����Ƭ
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<HyPcDhajDomain> queryAjzpList(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * �����ɳ���Ϣ
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain queryPcxx(BaseBusinessDomain baseDomain) throws Exception;
}
