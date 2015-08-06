package com.cy.hygl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.hygl.domain.HyPcHwxxHdqdDomain;

/**
 * The DAO for ����-�ɳ�-������Ϣ-�ص��嵥.
 * 
 * @author HJH
 */
public interface HyPcHwxxHdqdDao extends ExtendDao {
	public List<BaseBusinessDomain> queryQdList(BaseBusinessDomain baseDomain)  throws Exception;
	/**
	 * ����
	 * @param hdqdDjxh
	 * @throws Exception
	 */
	public void updatejsztWhenJs(String hdqdDjxh) throws Exception;
	/**
	 * ����ܷ��˻�
	 * @param hdqdDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkTh(String hdqdDjxh) throws Exception;
	/**
	 * �˻�
	 * @param hdqdDjxh
	 * @throws Exception
	 */
	public void updatejsztWhenTh(String hdqdDjxh) throws Exception;
	/**
	 * ��ɾ�� �嵥������Щ�ص�
	 * @param hdqdDjxh
	 * @return
	 * @throws Exception
	 */
	public List<HyPcHwxxHdqdDomain> hdDjxhList(String hdqdDjxh) throws Exception;
	/**
	 * ���Ӧ�ص� ���յ��嵥��
	 * @param hdDjxh
	 * @param ssJgbm
	 * @return
	 * @throws Exception
	 */
	public String selectQdDjxh(String hdDjxh,String ssJgbm) throws Exception;
	/**
	 * ��ص����嵥
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> queryHdByQd(BaseBusinessDomain baseDomain)  throws Exception;
}
