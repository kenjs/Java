package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.RyxzCommonDomain;
import com.cy.framework.dao.BaseDao;

/**
 * 
 * @author hel
 * DESC:��Աѡ���DAO����
 *
 */
public interface RyxzCommonDao extends BaseDao {
	
	
	/**
	 * ��֯����
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryQyjgList(String qyzcXh) throws Exception;
	/**
	 * ��ҵ�ڲ���Ա
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryQyryList(String qyzcXh) throws Exception;
	
	/**
	 * ��ҵ�ڲ���Ա����֯����
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryQyryAndZzjgList(String qyzcXh) throws Exception;
	
	
	/**
	 * �ְ�����֯����
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryFbsZzjgList(String qyzcXh) throws Exception;
	
	/**
	 * �ְ��̵���Ա
	 * @param qyzcXh ��ҵע�����
	 * @return
	 * @throws Exception
	 */
	public List<RyxzCommonDomain> queryFbsryList(String qyzcXh) throws Exception;
}
