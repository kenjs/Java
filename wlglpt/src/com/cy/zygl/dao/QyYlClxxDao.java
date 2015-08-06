package com.cy.zygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.domain.QyYlClxxDomain;

/**
 * The DAO for ��ҵ-����-������Ϣ.
 * 
 * @author HJH
 */
public interface QyYlClxxDao extends ExtendDao {
	/**
	 * ����˾��
	 * @return
	 * @throws Exception
	 */
	public List<QyYlClxxDomain> querySj(String clDjxh) throws Exception;
	
	/**
	 * ɾ��˾��
	 * @param domain
	 * @throws Exception
	 */
	public void deleteSj(String xh,String clDjxh) throws Exception;
	
	/***
	 * ����˾��
	 * @param domain
	 * @throws Exception
	 */
	public void saveSj(QyYlClxxDomain domain) throws Exception;
	public int getXh(QyYlClxxDomain domain) throws Exception;
	/**
	 * ���泵����Ϣ
	 * @param domain
	 * @throws Exception
	 */
	public void saveCl(QyYlClxxDomain domain,UserDomain user) throws Exception;
	
	/**
	 * ����˾����Ϣ
	 * @return
	 * @throws Exception
	 */
	public QyYlClxxDomain getSjxx(String clDjxh,String xh) throws Exception;
	
	/**
	 * ��鳵�������Ƿ��ظ�
	 * @return
	 * @throws Exception
	 */
	public int checkClhm(QyYlClxxDomain domain) throws Exception;
	
	/**
	 * �����ȡ��ǰxh
	 * @param domain
	 * @throws Exception
	 */
	public String getCurrentXh(QyYlClxxDomain domain) throws Exception;
}
