package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhHwxhDomain;

/**
 * The DAO for ��ҵ-�ͻ�-������Ϣ.
 * 
 * @author HJH
 */
public interface QyKhHwxhDao extends ExtendDao {
	/**
	 * �����������Ƿ��ظ�
	 * @return
	 * @throws Exception
	 */
	public int checkHwxhmc(QyKhHwxhDomain qyKhHwxhdomain) throws Exception;

}
