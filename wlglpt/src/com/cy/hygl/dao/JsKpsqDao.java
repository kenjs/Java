package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.bo.JsKpsqMx;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.JsKpsqDomain;

/**
 * The DAO for ��Ʊ����.
 * 
 * @author HJH
 */
public interface JsKpsqDao extends ExtendDao {
	/**
	 * ��ȡ�嵥��Ϣlist
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryDzQdList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception;
	/**
	 * ������ǰά���Ŀ�Ʊ��������嵥�б�list
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryJsKpsqMxList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception;
	/**
	 * ������ǰά���Ŀ�Ʊ��������嵥�б�list����ʱ��ȡ����
	 * @param baseDomain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryJsKpsqMxTempList(BaseBusinessDomain baseDomain, UserDomain userDomain)throws Exception;
	/**
	 * ɾ������-��Ʊ����-�����嵥
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void deleteJsKpsqMxByKey(String kpsqmxDjxh) throws Exception;
	/**
	 * ɾ������-��Ʊ����-�����嵥��ʱ��
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void deleteJsKpsqMxTempByKey(String kpsqmxDjxh) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveJsKpsqMxDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxDomainByKey(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void saveJsKpsqMxTempDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * 
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxTempDomainByKey(BaseBusinessDomain baseDomain) throws Exception; 
	
	/**
	 * �������뿪Ʊ���ϼ�
	 * @param baseDomain
	 * @param user
	 * @throws Exception
	 */
	public void updateDomain(BaseBusinessDomain baseDomain, UserDomain user) throws Exception;
	/**
	 * ����ʱ�����ݲ�����ʽ�����-��Ʊ����-�����嵥
	 * @param baseDomain
	 * @throws Exception
	 */
	public void insertJsKpsqMxByTemp(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * ɾ����ʱ�����-��Ʊ����-�����嵥-��ʱ��
	 * @param baseDomain
	 * @throws Exception
	 */
	public void deleteJsKpsqMxTemp(BaseBusinessDomain baseDomain) throws Exception;
	/**
	 * ���ݿ�Ʊ����Ǽ���ź��嵥�Ǽ���Ų����Ƿ��Ѿ����ڶ����嵥��¼����ʽ��
	 * @param kpsqDjxh
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxDomainByXh(String kpsqDjxh,String qdDjxh) throws Exception;
	/**
	 * ���ݿ�Ʊ����Ǽ���ź��嵥�Ǽ���Ų����Ƿ��Ѿ����ڶ����嵥��¼����ʱ��
	 * @param kpsqDjxh
	 * @param qdDjxh
	 * @return
	 * @throws Exception
	 */
	public BaseBusinessDomain getJsKpsqMxTempDomainByXh(String kpsqDjxh,String qdDjxh) throws Exception;
	
	public void callProKpsqHxcl(String kpsqDjxh, UserDomain userDomain) throws Exception;
	
	public List<JsKpsqDomain> querySrKpMx(JsKpsqDomain domain) throws Exception;
	
	public void savaSrKpMxTemp(JsKpsqMx bo) throws Exception;
	
	public List<JsKpsqDomain> querySrKpsqMxList(JsKpsqDomain domain,int i) throws Exception;
	
	public void deleteSqKpTemp(JsKpsqMx bo) throws Exception;
	
	public void savaSrKpMx(JsKpsqMx bo) throws Exception;
	
	public int checkSrSpMx(JsKpsqMx bo) throws Exception;
	
	public void deleteSrKpMx(JsKpsqMx bo) throws Exception;
	
	public void updateSrKpJeByDjxh(JsKpsqDomain domain,double zje) throws Exception;
	
	
}
