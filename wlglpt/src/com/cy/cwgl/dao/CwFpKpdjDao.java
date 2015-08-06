package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;
/**
 * The DAO for 财务-开票登记
 * 
 * @author LYY
 */
public interface CwFpKpdjDao  extends ExtendDao {
	public void callPHyglCwKpdjZfHxcl(String kpdjxh,String kpsqDjxh) throws Exception;
}
