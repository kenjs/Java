package com.cy.zygl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyFbsDjxxDomain;

/**
 * The DAO for ��ҵ-�ְ���-�Ǽ���Ϣ.
 * 
 * @author HJH
 */
public interface QyFbsDjxxDao extends ExtendDao {
	
	/**
	 * ��ǰ�ְ��̵ĵǼ���Ų�����ʱ����ѯͬ�������Ƿ�����ͬ���ķְ�������
	 * ��ǰ�ְ��̵ĵǼ���Ŵ���ʱ����ѯ�Ƿ����������ķְ�������
	 * @param domain
	 * @throws Exception
	 */
	public int queryFbsmcCount(QyFbsDjxxDomain domain) throws Exception;


}
