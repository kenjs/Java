package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;

/**
 * The DAO for ����-��������.
 * 
 * @author HJH
 */
public interface CwQtsrDao extends ExtendDao {

	public void deleteByKey(String cwDjxh) throws Exception;
	
	public void callPCwglQtsrHxcl(String cwDjxh, String ssJgbm,String czyDjxh) throws Exception;
}
