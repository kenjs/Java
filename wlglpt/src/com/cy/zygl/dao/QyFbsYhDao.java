package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.zygl.domain.QyFbsYhDomain;

/**
 * The DAO for �ְ����û�����.
 * 
 * @author HJH
 */
public interface QyFbsYhDao extends ExtendDao {

	/**
	 * �����������Ƿ��ظ�
	 * @return
	 * @throws Exception
	 */
	public int checkYhzh(QyFbsYhDomain domain, UserDomain user) throws Exception;
	
	/**
	 * ͣ��
	 * @return
	 * @throws Exception
	 */
	public void saveEnable(QyFbsYhDomain domain)throws Exception;
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public void saveDisable(QyFbsYhDomain domain)throws Exception;
}
