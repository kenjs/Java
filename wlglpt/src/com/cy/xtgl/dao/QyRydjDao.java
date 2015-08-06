package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.xtgl.domain.QyRydjDomain;


/**
 * The DAO for �û�ά��. 
 * @author yu huan
 * Date 2013-1-9
 */
public interface QyRydjDao extends ExtendDao {
	/**
	 * ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public void startUse(QyRydjDomain domain) throws Exception;

	/**
	 * ͣ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void stopUse(QyRydjDomain domain) throws Exception;
	/**
	 * �����¼�ʺ��Ƿ��ظ�
	 * 
	 * @return
	 * @throws Exception
	 */
	public int checkQyzzYhwhMc(QyRydjDomain domain) throws Exception;




}
