package com.cy.zygl.dao;



import com.cy.common.dao.ExtendDao;
import com.cy.zygl.domain.QyKhJsjgDomain;

/**
 * The DAO for ��ҵ-�ͻ�-����۸�.
 * 
 * @author HJH
 */
public interface QyKhJsjgDao extends ExtendDao {

	public int saveCheck(QyKhJsjgDomain domain) throws Exception;
}
