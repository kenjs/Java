package com.cy.dzgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.dzgl.domain.QySpwsSplcszDomain;
import com.cy.dzgl.domain.QySpwsSplcszZbDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for ��ҵ-��������-������������.
 * 
 * @author anq
 */
public interface QySpwsSplcszDao extends ExtendDao {

	/**
	 * 
	* @Description: ��ʼ�� ��ҵ-��������-������������-�ӱ�
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @throws Exception
	 */
	public void initSpjcDomainMx(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * 
	* @Description: ���� ��ҵ-��������-������������-�ӱ�
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @throws Exception
	 */
	public void saveSpjc(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: ������������ȡ�������������ӱ��б� 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param splcSzxh ��������������������
	* @return
	* @throws Exception
	 */
	public List<QySpwsSplcszZbDomain> querySplcszZbList(Long splcSzxh) throws Exception;
	
	/**
	 * 
	* @Description: ɾ�� ��ҵ-��������-��������-�ӱ� ��������������һ����¼ 
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @param userDomain
	* @throws Exception
	 */
	public void deleteSplcZb(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	public void deleteSpjcBySplcSzxh(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: ������������ ��ҵ-��������  �����õ���Ŀ�����־
	* @Note
	* @author anq
	* @since 2013-2-4
	* @param baseDomain
	* @param userDomain
	* @return
	* @throws Exception
	 */
	public String queryQySpwsXmflbzByKey(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	
	/**
	 * ����������Ϣ
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWsxx(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * ����splcSzxh��ȡ����������������
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWssplcsz(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * ����������������
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveCxszSplc(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * ȡ��������������
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteQxszSplc(QySpwsSplcszDomain domain, UserDomain userDomain) throws Exception;

}
