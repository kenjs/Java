package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhHwxxDomain;

/**
 * The DAO for ��ҵ-�ͻ�-������Ϣ.
 * 
 * @author HJH
 */
public interface QyKhHwxxDao extends ExtendDao {
	/**
	 * �����������Ƿ��ظ�
	 * @return
	 * @throws Exception
	 */
	public int checkHwmc(QyKhHwxxDomain qyKhHwxxdomain) throws Exception;

}
