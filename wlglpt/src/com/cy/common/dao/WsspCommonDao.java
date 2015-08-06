package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WsspCommonDomain;
import com.cy.common.domain.WssplzDomain;
import com.cy.framework.dao.BaseDao;

/**
 * 
 * @author hel
 * DESC:����������DAO����
 *
 */
public interface WsspCommonDao extends BaseDao {
	/**
	 * ��ѯ�Ƿ���������
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void queryWszsJudge(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * ��������
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveJudge(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * �����˻�
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveBack(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * ����������ת��ѯ
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public List<WssplzDomain> querySplzcx(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * �����б�
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public WsspCommonDomain queryFsSelect(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * ����
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveSend(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * ��ȡ������ͨ�������˻���
	 * @param spJgbm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> querySprListBySpJgbm(String spJgbm) throws Exception;
	/**
	 * ��ȡ������ͨ����λ�Ǽ����
	 * @param gwDjxh
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> querySprListByGwDjxh(String gwDjxh) throws Exception;
	/**
	 * ȡ������url
	 * @param wsspxh
	 * @return
	 * @throws Exception
	 */
	public String queryWsspjUrl(String wsspxh) throws Exception;
	
	/**
	 * �״η����б�
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WsspCommonDomain queryScFsSelect(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * �״η���
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public void saveScSend(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * ��ȡ���� ���������������
	 * @param domain
	 * @param user
	 * @throws Exception
	 */
	public String queryWssplcszxh(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * ��ȡ������ͨ������λ
	 * @param bdwDm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryWssprByBdwList(String bdwDm) throws Exception;
	/**
	 * ��ȡ������ͨ���ܹ�˾
	 * @param zgsDm
	 * @return
	 * @throws Exception
	 */
	public List<DmbGgDomain> queryWssprByZgsList(String zgsDm) throws Exception;
	
}
