package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;
/**
 * The DAO for 财务-货币资产流水记录
 * 
 * @author HCM
 */
public interface CwKpdjDao  extends ExtendDao {

	/**
	 * 财务开票作废后续处理
	 * @param businessDomain
	 * @throws Exception
	 */
	public void cwKpdjZfhxcl(String kpDjxh)throws Exception; 
}
