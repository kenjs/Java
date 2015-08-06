package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.xtgl.domain.QyJsDomain;

/**
 * The DAO for ��ҵ��ɫ.
 * 
 * @author ylp
 * @since 2013-1-10 ����9:00:00
 * @version
 * 
 */
public interface QyJsDao extends ExtendDao {

	/**
	 * ����ɫ�����Ƿ��ظ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkJsMcRe(QyJsDomain qyjsdomain) throws Exception;

	/**
	 * ɾ����ɫǰ���жϸý�ɫ�Ƿ�����ʹ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkJsUsed(QyJsDomain qyjsdomain) throws Exception;

	/**
	 * ͣ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void stopJs(QyJsDomain domain) throws Exception;

	/**
	 * ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public void startJs(QyJsDomain domain) throws Exception;

}
