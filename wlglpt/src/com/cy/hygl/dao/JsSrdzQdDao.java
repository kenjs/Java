package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.domain.JsSrdzQdDomain;

/**
 * The DAO for ����-�������-�嵥.
 * 
 * @author HJH
 */
public interface JsSrdzQdDao extends ExtendDao {

	/**
	 * ��ѯ�Ѷ��˵�list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryYdz(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	/**
	 * ��ѯ�����嵥��ϸlist
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryDzQdMxTemp(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	/**
	 * ��ѯ������ϸ��ʱ��list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryDzQdMx(BaseBusinessDomain baseDomain, UserDomain userDomain) throws Exception;
	/**
	 * ��������嵥��ϸ
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzQdMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception ;
	/**
	 * ��������嵥��ϸ��ʱ��
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveDzQdMxTempDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * ɾ�������嵥��ϸ��ʱ��
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteDzQdMxTemp(String qdDjxh,String ywDjxh, String ywMxXh, UserDomain userDomain) throws Exception;
	/**
	 * ɾ�������嵥��ϸ
	 * @param baseDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteDzQdMx(String qdDjxh,String ywDjxh, String ywMxXh, UserDomain userDomain) throws Exception;
	/**
	 * ���嵥��ϸ��ʱ�����ݱ��浽��ʽ��
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void saveQdMxDomainByTemp(String qdDjxh) throws Exception;
	/**
	 * ɾ���嵥��ϸ��ʱ��
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void deleteDzQdMxTempByQdDjxh(String qdDjxh) throws Exception;
	/**
	 * ����JS_SRDZ_DD����-�������-�����е��嵥�Ǽ����
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void updateJsSrDzDd(String qdDjxh) throws Exception;
	/**
	 *  ɾ���嵥��ϸʱ����JS_SRDZ_DD����-�������-�����е��嵥�Ǽ�����ÿ�
	 * @param jsDjxh
	 * @throws Exception
	 */
	public void deleteDzQdMxUpdateSrDzDdOfIsNull(String ywDjxh) throws Exception;
	
	/**
	 * ���ºϼƽ��
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	/**
	 * �������õǼ��б�
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public List<HyZyglFydjDomain> queryFydjList(JsSrdzQdDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * ���¶����嵥��ĺϼƽ��
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void updateQdHeJeByKey(String qdDjxh) throws Exception;
	
	public void updateJsSrdzQdxhToNullByQdDjxh(String qdDjxh) throws Exception;
	
	public Double calQdHjJe(String qdDjxh) throws Exception;

	/**
	 * ȡ���嵥�ж�Ӧ����ʧ�Ǽ����
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<JsSrdzQdDomain> selectSrdzQdMxWhenWlss(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * ���ɲ�����Ϣ
	 * @throws Exception
	 */
	public void cwYsfSrdz(BaseBusinessDomain baseDomain) throws Exception;
	
	/**
	 * ����Ʊ����ʱ�����ɿ�Ʊ��Ϣ
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveKp(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	
	/**
	 * ����嵥�Ƿ����ɾ��������Ѿ����뿪Ʊ���룬�򲻿���ɾ��
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkQdDel(String qdDjxh) throws Exception;
	
	/**
	 * �嵥����Ʊʱ��ɾ������֧���Ƿ�Ǽ�
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkQDCwInfo(String qdDjxh) throws Exception ;
	
	/**
	 * ɾ������֧����Ϣ
	 * @param qdDjxh
	 * @throws Exception
	 */
	public void deleCwInfo(String qdDjxh) throws Exception ;
}
