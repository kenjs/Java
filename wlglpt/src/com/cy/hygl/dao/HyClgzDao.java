package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyClgzDomain;
/**
 * The DAO for ��������.
 * 
 * @author HCM
 */
public interface HyClgzDao extends ExtendDao {

/**
 * �ɳ���Ϣ
 * @param pcDjxh
 * @return
 * @throws Exception
 */
	public BaseBusinessDomain getHyClgzPcxx(String pcDjxh) throws Exception;
	/**
	 * ����������Ϣ�б�
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> getHyClgzList(String pcDjxh) throws Exception;
	/**
	 * �������ɾ��
	 * @param clgzDjxh
	 * @throws Exception
	 */
	public void deleteClgzByKey(String clgzDjxh)throws Exception;
	/**
	 * ������Ϣ
	 * @param clgzDjxh
	 * @return
	 * @throws Exception
	 */
	public HyClgzDomain getClgzByKey(String clgzDjxh)throws Exception;
}
