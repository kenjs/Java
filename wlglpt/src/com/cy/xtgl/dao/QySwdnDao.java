package com.cy.xtgl.dao;

import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The DAO for 上网电脑管理.
 * 
 * @author HaoY
 */
public interface QySwdnDao extends ExtendDao{
	/**
	 * 停用
	 */
	public void stopUse(BaseBusinessDomain domain) throws Exception;
	
	/**
	 * 启用
	 */
	public void startUse(BaseBusinessDomain domain) throws Exception;
}
