package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhZhdzDomain;

/**
 * The DAO for ��ҵ-�ͻ�-װ����ַ.
 * 
 * @author ylp
 * @since 2013-1-15 ����8:31:00
 * @version
 */
public interface QyKhZhdzDao extends ExtendDao {

	// ����ʱ,У����ϸ��ַ�Ƿ��ظ�
	public int checkXxdzRe(QyKhZhdzDomain domain) throws Exception;

}
