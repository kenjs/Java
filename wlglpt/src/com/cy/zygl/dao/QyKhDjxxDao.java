package com.cy.zygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhDjxxDomain;

/**
 * The DAO for ��ҵ-�ͻ�-�Ǽ���Ϣ.
 * 
 * @author HJH
 */
public interface QyKhDjxxDao extends ExtendDao {
	
	/**
	 * ��ǰ�ͻ��ĵǼ���Ų�����ʱ����ѯͬ�������Ƿ�����ͬ���Ŀͻ�����
	 * ��ǰ�ͻ��ĵǼ���Ŵ���ʱ����ѯ�Ƿ����������Ŀͻ�����
	 * @param domain
	 * @throws Exception
	 */
	public int queryKhmcCount(QyKhDjxxDomain domain) throws Exception;
	
	public List<QyKhDjxxDomain> queryXzqhList(QyKhDjxxDomain domain) throws Exception;
	


}
