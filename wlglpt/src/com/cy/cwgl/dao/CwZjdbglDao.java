package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;

/**
 * The DAO for ����-�ʽ��������.
 * 
 * @author HJH
 */
public interface CwZjdbglDao extends ExtendDao {

	public void callPCwglZjdbHxcl(String cwDjxh, String ssJgbm,String czyDjxh) throws Exception;
}
