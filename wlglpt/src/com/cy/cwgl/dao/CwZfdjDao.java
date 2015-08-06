package com.cy.cwgl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.cwgl.domain.CwZfdjDomain;
import com.cy.framework.domain.BaseBusinessDomain;
/**
 * The DAO for ����-֧���Ǽ�
 * 
 * @author LYY
 */
public interface CwZfdjDao  extends ExtendDao {

	/**
	 * ��������ɾ��
	 * @param zfDjxh  ֧���Ǽ����
	 * @throws Exception
	 */
	public void deleteCwZfdjByKey(String zfDjxh)throws Exception;
	/**
	 * ��������
	 * @param zfDjxh
	 * @param bz
	 * @throws Exception
	 */
	public void callPHyglCwglZfdjHxcl(String zfDjxh, String bz) throws Exception;
	/**
	 * ���㷽����
	 * @return
	 * @throws Exception
	 */
	public List<CwZfdjDomain> getMcList(String ssJgbm,String yfjsfDm) throws Exception;
	/**
	 * ���㷽���
	 * @return
	 * @throws Exception
	 */
	public List<CwZfdjDomain> getLbList() throws Exception;
	/**
	 * ȡ����
	 * @param domain
	 * @throws Exception
	 */
	public void doGetYh(BaseBusinessDomain domain)throws Exception;
	/**
	 * ����
	 * @param domain
	 * @throws Exception
	 */
	public void doCancle(BaseBusinessDomain domain)throws Exception;
}
