package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAO for ��ҵ��֯��������ά��
 * 
 * @Author Yu huan
 * @Date 2013-01-8
 */

public interface BmwhDao extends ExtendDao {
	/**
	 * ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public void startUse(QyZzjgDomain domain) throws Exception;

	/**
	 * ͣ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void stopUse(QyZzjgDomain domain) throws Exception;

	/**
	 * ���в���������֤�Ƿ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkQyzzBmwhMc(QyZzjgDomain domain) throws Exception;

	/**
	 * ��ȡ���ŵ���󼶱����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMaxJbdm(QyZzjgDomain domain) throws Exception;
}
