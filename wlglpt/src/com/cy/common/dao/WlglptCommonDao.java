package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptCommonDomain;
import com.cy.framework.dao.BaseDao;
import com.cy.xtgl.domain.QyJsDomain;

public interface WlglptCommonDao extends BaseDao {
	
	/**
	 * ���ڻ�ȡ����ҵ��Ĺ���DAO����
	 * @param domain
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryCommonList(WlglptCommonDomain domain, UserDomain userDomain) throws Exception;
	
	public List<DmbGgDomain> queryBmList(WlglptCommonDomain domain) throws Exception;
	
	public List<DmbGgDomain> queryGwList(WlglptCommonDomain domain) throws Exception;
	
	public List<DmbGgDomain> queryRyList(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * ���ݻ�������ȡ�������
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getQyJbdmByJgbm(String jgbm) throws Exception;
	
	/**
	 * ���ݻ�������ȡ��ʹ�õĽ�ɫ�б�
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public List<QyJsDomain> getJsListByJgbm(String jgbm) throws Exception;
	
	/**
	 * ����һ���������룬���������ϼ���������
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getSjJgbmByJgbm(String jgbm) throws Exception;
	
	/**
	 * �������ݿ⺯����ȡ����˳���
	 * @param sxhflbm
	 * @param jgbm
	 * @return
	 * @throws Exception
	 */
	public String getFunXtSxh(String sxhflbm, String jgbm) throws Exception;
	
	/**
	 * ȡϵͳ����
	 * @param csxh
	 * @param jgbm
	 * @param cslb
	 * @return
	 * @throws Exception
	 */
	public String getFunXtXtcs(String csxh, String jgbm, String cslb) throws Exception;
	
	/**
	 * ȡ��˾��ά���ķְ���
	 * @param ssJgbm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryFbsByJgbm(WlglptCommonDomain domain, String ssJgbm) throws Exception;
	
	/**
	 * ȡ������ά������Ŀά��
	 * @param baseDomain
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryWs(WlglptCommonDomain domain) throws Exception;
	
	/**
	 * ȡsequence
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public String selectSequence(String seqName) throws Exception;
	
	/**
	 * �ɳ���ʽ�����(��Ҫ���ڵ�ѡ��ť����������)
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryPcfsRadioList() throws Exception;
}
