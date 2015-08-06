package com.cy.hygl.dao;





import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DAO for ��Ʊ����.
 * 
 * @author HCM
 */
public interface JsKpDzqdDao extends ExtendDao {
	/**
	 * ��ȡ������¼��ϸ
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public void initHxMx(BaseBusinessDomain baseDomain)throws Exception;
	/**
	 * ��ȡ������¼�б���ϸ
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public void initAddHxMx(BaseBusinessDomain baseDomain)throws Exception;
	/**
	 * ɾ����¼
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void deleteJsKpsqMxByKey(String kpsqmxDjxh) throws Exception;
	/**
	 * �������޸ģ�ɾ�� ��������
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void callPHyglJsglYkphx(String kpsqmxDjxh) throws Exception;
}
