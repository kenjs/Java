package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyXxzjDjxxDomain;

/**
 * The DAO for ��ҵ-��Ϣ�н�-�Ǽ���Ϣ
 * 
 * @author yw
 * @since 2013-2-20 ����8:31:00
 * @version
 */
public interface QyXxzjDjxxDao extends ExtendDao  {
	
	//����ʱ������֤�Ƿ��ظ�
	public int checkQyXxZjMc(QyXxzjDjxxDomain domain) throws Exception;
}
