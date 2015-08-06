package com.cy.cwgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.cwgl.domain.CwHbzcCshDomain;
import com.cy.xtgl.domain.QyZzjgDomain;

/**
 * The DAO for 财务-货币资产初始化.
 * 
 * @author HJH
 */
public interface CwHbzcCshDao extends ExtendDao {

	/**
	 * 停用
	 * @return
	 * @throws Exception
	 */
	public void stopUser(CwHbzcCshDomain domain)throws Exception;
	/**
	 * 启用
	 * @return
	 * @throws Exception
	 */
	public void startUser(CwHbzcCshDomain domain)throws Exception;
	public void checkYhMc(CwHbzcCshDomain domain)throws Exception;
	public void deleteHxcl(CwHbzcCshDomain domain)throws Exception;
	public String getYhCshDjxhWhenXj(String ssJgbm) throws Exception;
}
