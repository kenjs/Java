package com.cy.xtgl.dao;

import com.cy.common.bo.QyZzjg;
import com.cy.common.dao.ExtendDao;

import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * THE ACTION FOR ��ҵ-��֯���� �ֹ�˾
 * @author ��ΰ
 * @date 2013.1.7
*/ 	

public interface QyZzjgFgsDao extends ExtendDao {
	
	/**
	 * ͣ��
	 * @return
	 * @throws Exception
	 */
	public void stopUser(QyZzjgDomain domain)throws Exception;
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public void startUser(QyZzjgDomain domain)throws Exception;
	/**
	 * ��ȡ���ļ������
	 * @return
	 * @throws Exception
	 */
	public String getMaxJbdm(QyZzjg bo)throws Exception;
	/**
	 * �����ظ���֤�Ƿ�����
	 * @return
	 * @throws Exception
	 */
	public int checkQyzzFgsMc(QyZzjgDomain domain)throws Exception;
}
