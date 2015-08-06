package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;

/**
 * The DAO for 财务-资金调拨管理.
 * 
 * @author HJH
 */
public interface CwZjdbglDao extends ExtendDao {

	public void callPCwglZjdbHxcl(String cwDjxh, String ssJgbm,String czyDjxh) throws Exception;
}
