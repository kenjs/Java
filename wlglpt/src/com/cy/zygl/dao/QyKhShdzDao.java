package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhShdzDomain;

/**
 * The DAO for ��ҵ-�ͻ�-�ջ���ַ.
 * 
 * @author ylp
 * @since 2013-1-16 ����10:31:00
 * @version
 */
public interface QyKhShdzDao extends ExtendDao {

	// ����ʱ,У����ϸ��ַ�Ƿ��ظ�
	public int checkShXxdzRe(QyKhShdzDomain domain) throws Exception;
}
