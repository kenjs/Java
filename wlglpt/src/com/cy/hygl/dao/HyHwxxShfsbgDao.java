package com.cy.hygl.dao;

import com.cy.common.dao.ExtendDao;

/**
 * The DAO for ����-������Ϣ-�ͻ���ʽ���.
 * 
 * @author HJH
 */
public interface HyHwxxShfsbgDao extends ExtendDao {

	/**
	 * ����ͻ���ʽ�Ƿ�ɱ��   ����0���ɱ�
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public int checkShfs(String pcDjxh) throws Exception;
	/**
	 * ����ӱ��¼��
	 * @param ddDjxh
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public int checkShfsbgZb(String ddDjxh,String xh) throws Exception;
}
